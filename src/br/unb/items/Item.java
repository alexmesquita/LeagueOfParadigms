package br.unb.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Item {
	String name;
	int cust;
	Map<String, Double> properties = new HashMap<String,Double>();
	ArrayList<Item> subItens = new ArrayList<Item>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCust() {
		return cust;
	}
	public void setCust(int cust) {
		this.cust = cust;
	}
	public Map<String, Double> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}
	public ArrayList<Item> getSubItens() {
		return subItens;
	}
	public void setSubItens(ArrayList<Item> subItens) {
		this.subItens = subItens;
	}
	
}
