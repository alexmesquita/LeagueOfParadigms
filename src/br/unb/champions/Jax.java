package br.unb.champions;

import jade.util.leap.Serializable;
import br.unb.skill.Skill;

public class Jax extends Champion implements Serializable{

	public Jax() {
		health = 450.0;
		mana = 230.0;
		attackDamage = 56.3;
		attackSpeed = 0.638;
		abilityPower = 0.0;
		healthRegen = 7.45;
		manaRegen = 6.4;
		armor = 22.0;
		magicResist = 30.0;
		type = "attackDamage";
		
		Skill skill1 = new Skill("Leap Strike", 65.0, 70 + 30*level + 1*attackDamage + 0.6 * abilityPower);
		Skill skill2 = new Skill("Empower", 30.0, 45.0 + 35*level + 0.6*abilityPower);
		Skill skill3 = new Skill("Grandmaster's Might", 100.0, 100+60*level + 0.7*abilityPower);
		Skill skill4 = new Skill("Basic Attack", 0.0, attackDamage*((int)2*attackSpeed)+3.375*level);
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
	}
	public void updateSkills(){
		skills.get(0).setDamage(70 + 30*level + 1*attackDamage + 0.6 * abilityPower);
		skills.get(1).setDamage(45.0 + 35*level + 0.6*abilityPower);
		skills.get(2).setDamage(100+60*level + 0.7*abilityPower);
		skills.get(3).setDamage(attackDamage*((int)2*(Math.pow(attackSpeed, level)))+3.5*level);	
	}
	
	public void updateAbilities(){
		upLevel();
		health += 85.0;
		mana += 35.0;
		attackDamage += 3.375;
		attackSpeed *= 1.34;
		armor += 3.0;
		magicResist += 1.25;
	}
	public void regen(){
		healthRegen += 0.7;
		manaRegen += 0.55;
	}
}
