package br.unb.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import br.unb.champions.Ahri;
import br.unb.champions.Champion;
import br.unb.champions.ChampionDamage;
import br.unb.champions.Voli;
import br.unb.items.Item;
import br.unb.items.Shop;
import br.unb.skill.Skill;

public class Computer extends Agent{
	private Double gold = 475.0;
	Champion champ;
	ArrayList<Item> build = new ArrayList<Item>();
	Shop shop = new Shop();
	ChampionDamage championDamage = new ChampionDamage();
	
	
	protected void setup(){
		DFAgentDescription agentDescription = new DFAgentDescription();
		ServiceDescription service = new ServiceDescription();
		
		service.setName("Computer");
		service.setType("Play");
		
		agentDescription.setName(this.getAID());
		agentDescription.addServices(service);
		
		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		raffleChampion();
		System.out.println("Computer criado");
		addBehaviour(new Attack(this));
	}
	
	private void raffleChampion(){
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(2);
		
		switch (r) {
		case 0:
			champ = new Voli();
			System.out.println("Voce vai lutar contra um Voli, boa sorte, pq vc vai precisar!");
			break;
		case 1:
			champ = new Ahri();
			System.out.println("Voce vai lutar contra uma Ahri, boa sorte!");
		default:
			break;
		}		
	}
	
	private class Attack extends CyclicBehaviour{
		public Attack(Agent agent){
			super(agent);
		}

		public void action() {
			ACLMessage message = myAgent.receive();
			int level = 0;
			if(message != null){
				
				try {
					championDamage = (ChampionDamage) message.getContentObject();
					champ.setHealth(champ.getHealth() - championDamage.damage);
					System.out.println("Vida do computer:" + champ.getHealth());
				} catch (UnreadableException e1) {
					e1.printStackTrace();
				}
				System.out.println("Computador: " + champ.getHealth());
				if(champ.getHealth() <= 0){
					System.out.println("Voce ganhou!");
					return;
				}
				else{
					champ.regen();
					level++;
					if(level == 3){
						champ.updateAbilities();
						champ.updateSkills();
					}
				}
				gold+=400;
				
				Random random = new Random();
				gold += random.nextInt(1001);
				System.out.println("Computer - Ouro total: "+gold);
				
				
				BuyItems();
				
				ACLMessage replay = message.createReply();
				
				replay.setPerformative(ACLMessage.INFORM);
				try {
					championDamage.champ = champ;
					championDamage.damage = attackAdversary();
					System.out.println("O computer causou "+championDamage.damage + " de dano.");
					replay.setContentObject(championDamage);
				} catch (IOException e) {
					System.out.println("Erro ao enviar o objeto");
					e.printStackTrace();
				}
				myAgent.send(replay);
			}
			else{
				block();
			}
		}
	}
	
	void BuyItems(){
		ArrayList<Item> items = null;
		Double max = 0.0;
		Item buy = null;
		do{
			buy = null;
			items = shop.listItem(gold, champ.getType());
			for (Item item : items) {
				Map<String, Double> it1 = item.getProperties();
				for(String key : it1.keySet()){
					if(max < it1.get(key)){
						max = it1.get(key);
						buy = item;
					}
				}
			}
			if(buy != null){
				System.out.println("O computer comprou o item:");
				System.out.println(buy.getName() + " - " + buy.getCust());
				build.add(buy);
				champ.updateAbilitiesItems(buy);
				champ.updateSkills();
				gold -= buy.getCust();
			}
		} while(buy != null);
			
	}
	
	private Double attackAdversary(){
		Double max = 0.0;
		Double manaCust = 0.0;
		ArrayList<Skill> skills = champ.getSkills();
		for (Skill skill : skills) {
			if(champ.getMana() >= skill.getManaCust() && max < skill.getDamage()){
				max = skill.getDamage();
				manaCust = skill.getManaCust();
			}
		}
		champ.setMana(champ.getMana() - manaCust);
		return max;
	}
}
