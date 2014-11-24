package br.unb.champions;

import jade.util.leap.Serializable;
import br.unb.skill.Skill;

public class Voli extends Champion implements Serializable{

	public Voli() {
		health = 440.0;
		mana = 220.0;
		attackDamage = 54.0;
		attackSpeed = 0.658;
		abilityPower = 0.0;
		healthRegen = 7.0;
		manaRegen = 7.0;
		armor = 20.5;
		magicResist = 30.0;
		type = "health";
		
		Skill skill1 = new Skill("Frenzy", 35.0, (80+45*level)+0.15*health);
		Skill skill2 = new Skill("Rolling Thunder", 40.0, 30.0*level);
		Skill skill3 = new Skill("Magic Roar", 60.0, (60+45*level)+0.6*abilityPower);
		Skill skill4 = new Skill("Basic Attack", 0.0, attackDamage*((int)2*attackSpeed)+3.3*level);
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
	}
	public void updateSkills(){
		skills.get(0).setDamage((80+45*level)+0.15*health);
		skills.get(1).setDamage(30.0*level);
		skills.get(2).setDamage((60+45*level)+0.6*abilityPower);
		skills.get(3).setDamage(attackDamage*((int)2*(Math.pow(attackSpeed, level)))+3.3*level);	
	}
	
	public void updateAbilities(){
		upLevel();
		health += 86.0;
		mana += 30.0;
		attackDamage += 3.3;
		attackSpeed *= 1.267;
		armor += 3.5;
		magicResist += 1.25;
	}
	public void regen(){
		healthRegen += 0.65;
		manaRegen += 0.65;
	}
}
