package br.unb.champions;

import jade.util.leap.Serializable;
import br.unb.skill.Skill;

public class Draven extends Champion implements Serializable{

	public Draven() {
		health = 420.0;
		mana = 240.0;
		attackDamage = 46.5;
		attackSpeed = 0.679;
		abilityPower = 0.0;
		healthRegen = 5.0;
		manaRegen = 6.95;
		armor = 20.0;
		magicResist = 30.0;
		type = "attackDamage";
		
		Skill skill1 = new Skill("Spinning Axe", 45.0, (45.0+15*level)+0.85*attackDamage);
		Skill skill2 = new Skill("Stand Aside", 70.0, 70.0 + 35*level + 0.5*attackDamage);
		Skill skill3 = new Skill("Whirling Death", 120.0, 175+100*level + 1.1*attackDamage);
		Skill skill4 = new Skill("Basic Attack", 0.0, attackDamage*((int)2*attackSpeed)+3.5*level);
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
	}
	public void updateSkills(){
		skills.get(0).setDamage((45.0+15*level)+0.85*attackDamage);
		skills.get(1).setDamage(70.0 + 35*level + 0.5*attackDamage);
		skills.get(2).setDamage(175+100*level + 1.1*attackDamage);
		skills.get(3).setDamage(attackDamage*((int)2*(Math.pow(attackSpeed, level)))+3.5*level);	
	}
	
	public void updateAbilities(){
		upLevel();
		health += 82.0;
		mana += 42.0;
		attackDamage += 3.5;
		attackSpeed *= 1.27;
		armor += 3.3;
		magicResist += 0;
	}
	public void regen(){
		healthRegen += 0.7;
		manaRegen += 0.65;
	}
}
