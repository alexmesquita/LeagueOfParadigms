package br.unb.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
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
import java.util.Scanner;

import br.unb.champions.Ahri;
import br.unb.champions.Champion;
import br.unb.champions.ChampionDamage;
import br.unb.champions.Draven;
import br.unb.champions.Jax;
import br.unb.champions.Voli;
import br.unb.items.Item;
import br.unb.items.Shop;
import br.unb.skill.Skill;

public class Player extends Agent{
	
	private DFAgentDescription[] agentComputer=null;
	private AID agentComputerAID=null;
	private Double gold = 475.0;
	Champion champ;
	ChampionDamage championDamage = new ChampionDamage();
	ArrayList<Item> build = new ArrayList<Item>();
	Shop shop = new Shop();
	
	@Override
	protected void setup() {
		System.out.println("Seja Bem-vindo ao League of Paradigms");
		
		try
		{
			DFAgentDescription agentDescription= new DFAgentDescription();
			ServiceDescription service = new ServiceDescription();
			service.setName("Player");
			service.setType("Play");
			agentDescription.addServices(service);
			
			DFService.register(this, agentDescription);
			
			DFAgentDescription computer= new DFAgentDescription();
			ServiceDescription serviceComputer = new ServiceDescription();
			
			serviceComputer.setName("Computer");
			serviceComputer.setType("Play");
			
			computer.addServices(serviceComputer);
			
			agentComputer = DFService.search(this, computer);
			while(agentComputer == null){
				Thread.sleep(1000);
				System.out.println(agentComputer);
				System.out.println("Não tenho com quem lutar, estou entediado! =/");
			}
			
			agentComputerAID = agentComputer[0].getName();
			System.out.println("Adversário encontrado. Vamos lá!!");
			addBehaviour(new ChooseChampion(this));
			addBehaviour(new Attack(this));
			
		}catch(FIPAException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private class ChooseChampion extends OneShotBehaviour{
		public ChooseChampion(Agent agent){
			super(agent);
		}

		@Override
		public void action() {
			System.out.println("Escolha o seu Campeão:");
			System.out.println("1) Volibear");
			System.out.println("2) Ahri");
			System.out.println("3) Draven");
			System.out.println("4) Jax");
			
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				champ = new Voli();
				System.out.println("Voce escolhei o Voli, boa sorte!");
				break;
			case 2:
				champ = new Ahri();
				System.out.println("Voce escolhei a Ahri, boa sorte!");
				break;
			case 3:
				champ = new Draven();
				System.out.println("Voce escolhei o Draven, boa sorte!");
				break;
			case 4:
				champ = new Jax();
				System.out.println("Voce escolhei o Jax, boa sorte!");
				break;
			default:
				System.out.println("Animal, escolha uma campeão");
				break;
			}

			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			try {
				championDamage.champ = champ;
				championDamage.damage = attackAdversary();
				message.setContentObject(championDamage);
			} catch (IOException e) {
				System.out.println("erro ao enviar o objeto");
				e.printStackTrace();
			}
			message.addReceiver(agentComputerAID);
			myAgent.send(message);
		}
	}
	
	private class Attack extends CyclicBehaviour{
		public Attack(Agent agent){
			super(agent);
		}
		int level = 0;
		public void action() {
			ACLMessage message = myAgent.receive();
			
			if(message != null){
				try {
					championDamage = (ChampionDamage) message.getContentObject();
				} catch (UnreadableException e1) {
					e1.printStackTrace();
				}
				champ.setHealth(champ.getHealth() - championDamage.damage);
				System.out.println("Vida do Player: " + champ.getHealth());
				if(champ.getHealth() <= 0){
					System.out.println("Voce perdeu! :(");
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
				BuyItems();
				
				ACLMessage replay = message.createReply();
				
				replay.setPerformative(ACLMessage.INFORM);
				try {
					championDamage.champ = champ;
					championDamage.damage = attackAdversary();
					System.out.println("Player causou : " + championDamage.damage + " de dano");
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
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Esta na hora de ficar mais forte, construa sua build:");
		
		do{
			System.out.println("Voce tem " + this.gold + " de gold.");
			option = 0;
			items = shop.listItem(gold, champ.getType());
			for (Item item : items) {
				System.out.println(option + ") Item: " + item.getName() + " - Custo: " +
						item.getCust());
				Map<String, Double> it1 = item.getProperties();
				for(String key : it1.keySet()){
					System.out.println("Propriedade: " + key + " - Adicional: "+
							it1.get(key));
				}
				option++;
			}
			
			System.out.println(option + ") Atacar");
			
			option = scanner.nextInt();
			
			if(option != items.size()){
				Item buy = items.get(option);
				build.add(buy);
				champ.updateAbilitiesItems(buy);
				champ.updateSkills();
				gold -= buy.getCust();
			}
			
		} while(option != items.size());
	}
	
	private Double attackAdversary(){
		System.out.println("Escolha a skill para atacar o adversario:");
		System.out.println("Mana atual: " + champ.getMana());
		Double manaCust = 0.0;
		ArrayList<Skill> skills = champ.getSkills();
		int option = 0;
		for (Skill skill : skills) {
			if(champ.getMana() < skill.getManaCust()){
				option++;
				continue;
			}
			System.out.println(option + ") Nome: " + skill.getName() + " - Dano: " + 
					skill.getDamage() + " - Custo de mana: " + skill.getManaCust());
			option++;
		}
		Scanner scanner = new Scanner(System.in);
		option = scanner.nextInt();

		Skill skill = skills.get(option);
		
		champ.setMana(champ.getMana() - skill.getManaCust());
		return skill.getDamage();
	}
}
