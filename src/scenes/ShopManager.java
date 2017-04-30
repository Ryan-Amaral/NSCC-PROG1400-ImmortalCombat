package scenes;

import java.util.ArrayList;
import java.util.List;

import equipment.Armour;
import equipment.EquipmentList;
import equipment.Weapon;

/**
 * This class has functions to help with the shop.
 * 
 * @author Ryan
 *
 */
public class ShopManager {

	private List<Weapon> shopWeapons = new ArrayList<Weapon>();
	private List<Armour> shopArmours = new ArrayList<Armour>();
	
	public ShopManager(){
		// populate the weapons and armours lists
		shopWeapons = EquipmentList.getShopWeapons();
		shopArmours = EquipmentList.getShopArmours();
	}
	
	public List<Weapon> getShopWeapons() {
		return shopWeapons;
	}
	
	public void removeWeapon(int index){
		shopWeapons.remove(index);
	}

	public List<Armour> getShopArmours() {
		return shopArmours;
	}

	public void removeArmour(int index) {
		shopArmours.remove(index);
	}

}
