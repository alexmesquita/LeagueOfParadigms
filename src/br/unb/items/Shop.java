package br.unb.items;

import java.util.ArrayList;

public class Shop {
	ArrayList<Item> items = new ArrayList<Item>();
	
	public Shop(){
		Item giantsBelt = new Item();
		giantsBelt.name = "Giant's Belt";
		giantsBelt.cust = 1000;
		giantsBelt.properties.put("health", 380.0);
		
		Item rubyCrystal = new Item();
		rubyCrystal.name = "Ruby Crysta";
		rubyCrystal.cust = 400;
		rubyCrystal.properties.put("health", 150.0);

		Item rejuvenationBead = new Item();
		rejuvenationBead.name = "Rejuvenation Bead";
		rejuvenationBead.cust = 180;
		rejuvenationBead.properties.put("healthRegen", 1.0);

		Item warmogsArmor = new Item();
		warmogsArmor.name = "Warmog's Armor";
		warmogsArmor.cust = 1810;
		warmogsArmor.properties.put("healthRegen", 2.5);
		warmogsArmor.properties.put("health", 1000.0);
		warmogsArmor.subItens.add(giantsBelt);
		warmogsArmor.subItens.add(rejuvenationBead);
		warmogsArmor.subItens.add(rejuvenationBead);
		warmogsArmor.subItens.add(rejuvenationBead);

		Item blastingWand = new Item();
		blastingWand.name = "Blasting Wand";
		blastingWand.cust = 860;
		blastingWand.properties.put("abilityPower", 40.0);

		Item needlesslyLargeRod = new Item();
		needlesslyLargeRod.name = "Needlessly Large Rod";
		needlesslyLargeRod.cust = 1600;
		needlesslyLargeRod.properties.put("abilityPower", 80.0);

		Item rabadonsDeathcap = new Item();
		rabadonsDeathcap.name = "Rabadon's Deathcap";
		rabadonsDeathcap.cust = 1810;
		rabadonsDeathcap.properties.put("abilityPower", 1300.0);
		rabadonsDeathcap.subItens.add(needlesslyLargeRod);
		rabadonsDeathcap.subItens.add(blastingWand);
		
		Item bfSword = new Item();
		bfSword.name = "B. F. Sword";
		bfSword.cust = 1550;
		bfSword.properties.put("attackDamage", 50.0);

		Item pickaxe = new Item();
		pickaxe.name = "Pickaxe";
		pickaxe.cust = 875;
		pickaxe.properties.put("attackDamage", 25.0);

		Item infinityEdge = new Item();
		infinityEdge.name = "Infinity Edge";
		infinityEdge.cust = 2400;
		infinityEdge.properties.put("attackDamage", 80.0);		
		infinityEdge.subItens.add(bfSword);
		infinityEdge.subItens.add(pickaxe);


		items.add(giantsBelt);
		items.add(rubyCrystal);
		items.add(rejuvenationBead);
		items.add(warmogsArmor);
		items.add(needlesslyLargeRod);
		items.add(blastingWand);
		items.add(rabadonsDeathcap);
		items.add(infinityEdge);
		items.add(bfSword);
		items.add(pickaxe);
	}
	
	public ArrayList<Item> listItem(Double value, String type){
		ArrayList<Item> result = new ArrayList<Item>();
		for (Item it : items) {
			if(it.cust <= value && it.properties.containsKey(type)){
				result.add(it);
			}
		}
		return result;
	}
}
