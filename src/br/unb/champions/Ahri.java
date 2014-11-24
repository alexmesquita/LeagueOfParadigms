package br.unb.champions;

import jade.util.leap.Serializable;
import br.unb.items.Item;
import br.unb.skill.Skill;

public class Ahri extends Champion implements Serializable{

	public Ahri() {
		health = 380.0;
		mana = 250.0;
		attackDamage = 50.0;
		attackSpeed = 0.668;
		abilityPower = 0.0;
		healthRegen = 5.5;
		manaRegen = 7.0;
		armor = 15.0;
		magicResist = 30.0;
		type = "abilityPower";
		
		Skill skill1 = new Skill("Orb of Deception", 55.0+level*5, 2*(40+15*level+0.35*abilityPower));
		Skill skill2 = new Skill("Fox Fire", 50.0, 40+15*level+0.4*abilityPower);
		Skill skill3 = new Skill("Charm", 85.0, 60+30*level+0.35*abilityPower);
		Skill skill4 = new Skill("Basic Attack", 0.0, attackDamage*((int)2*attackSpeed)+3*level);
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
	}
	public void updateSkills(){
		skills.get(0).setDamage(2*(40+15*level+0.35*abilityPower));
		skills.get(1).setDamage(40+15*level+0.4*abilityPower);
		skills.get(2).setDamage(60+30*level+0.35*abilityPower);
		skills.get(3).setDamage(attackDamage*((int)2*(Math.pow(attackSpeed, level)))+3*level);	
	}
	
	public void updateAbilities(){
		upLevel();
		health += 80.0;
		mana += 50.0;
		attackDamage += 3.0;
		attackSpeed *= 1.2;
		armor += 3.5;
		magicResist += 0.0;
	}
	public void regen(){
		healthRegen += 0.6;
		manaRegen += 0.6;
	}
}

