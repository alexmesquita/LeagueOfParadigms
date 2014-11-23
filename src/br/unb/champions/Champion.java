package br.unb.champions;

import jade.util.leap.Serializable;

import java.util.ArrayList;
import java.util.Map;

import br.unb.items.Item;
import br.unb.skill.Skill;

public abstract class Champion implements Serializable{
	protected Double health;
	protected Double mana;
	protected Double attackDamage;
	protected Double attackSpeed;
	protected Double abilityPower;
	protected Double healthRegen;
	protected Double manaRegen;
	protected Double armor;
	protected Double magicResist;
	protected int level;
	protected String type;
	protected ArrayList<Skill> skills = new ArrayList<Skill>();
	
	public Champion(){
		this.level = 1;
	}

	public Double getHealth() {
		return health;
	}

	public void setHealth(Double health) {
		this.health = health;
	}

	public Double getMana() {
		return mana;
	}

	public void setMana(Double mana) {
		this.mana = mana;
	}

	public Double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(Double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public Double getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(Double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Double getAbilityPower() {
		return abilityPower;
	}

	public void setAbilityPower(Double abilityPower) {
		this.abilityPower = abilityPower;
	}

	public Double getHealthRegen() {
		return healthRegen;
	}

	public void setHealthRegen(Double healthRegen) {
		this.healthRegen = healthRegen;
	}

	public Double getManaRegen() {
		return manaRegen;
	}

	public void setManaRegen(Double manaRegen) {
		this.manaRegen = manaRegen;
	}

	public Double getArmor() {
		return armor;
	}

	public void setArmor(Double armor) {
		this.armor = armor;
	}

	public Double getMagicResist() {
		return magicResist;
	}

	public void setMagicResist(Double magicResist) {
		this.magicResist = magicResist;
	}

	public int upLevel() {
		return ++this.level;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}
	
	public void updateAbilitiesItems(Item item){
		Map<String, Double> it1 = item.getProperties();
		
		for(String key : it1.keySet()){
			if(key == "abilityPower"){
				this.abilityPower += it1.get(key);
			}
			else if(key == "health"){
				this.health += it1.get(key);
			}
			else if(key == "mana"){
				this.mana += it1.get(key);
			}
			else if(key == "attackDamage"){
				this.attackDamage += it1.get(key);
			}
			else if(key == "attackSpeed"){
				this.attackSpeed += it1.get(key);
			}
			else if(key == "abilityPower"){
				this.abilityPower += it1.get(key);
			}
			else if(key == "healthRegen"){
				this.healthRegen += it1.get(key);
			}
			else if(key == "manaRegen"){
				this.manaRegen += it1.get(key);
			}
			else if(key == "armor"){
				this.armor += it1.get(key);
			}
			else if(key == "magicResist"){
				this.magicResist += it1.get(key);
			}
		}
	}
	
	public abstract void regen();
	
	public abstract void updateAbilities();
	
	public abstract void updateSkills();
}
