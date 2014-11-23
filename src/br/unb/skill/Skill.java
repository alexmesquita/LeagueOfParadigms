package br.unb.skill;

import jade.util.leap.Serializable;

public class Skill implements Serializable{
	private String name = "";
	private Double manaCust = 0.0;
	private Double damage = 0.0;
	
	public Skill(String name, Double manaCust, Double damage){
		this.name = name;
		this.manaCust = manaCust;
		this.damage = damage;
	}

	public Double getManaCust() {
		return manaCust;
	}

	public void setManaCust(Double manaCust) {
		this.manaCust = manaCust;
	}

	public Double getDamage() {
		return damage;
	}

	public void setDamage(Double damage) {
		this.damage = damage;
	}
	
	public String getName(){
		return name;
	}

}
