package people;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import equipment.Armour;
import equipment.EquipmentList;
import equipment.Weapon;

/**
 * The player controlled fighter.
 * 
 * @author Ryan
 *
 */
public class Player extends Fighter {

	private List<Weapon> ownedWeapons = new ArrayList<Weapon>(); // all owned weapons
	private List<Armour> ownedArmour = new ArrayList<Armour>(); // all owned armours
	private int wins = 0; // amount of fights the player wins
	private int losses = 0; // amount of fights the player loses
	
	public Player(String name, ImageIcon icon, int curMaxHp, int attSkill, int defSkill){
		// set basic stats and info from parameter list
		this.name = name;
		this.icon = icon;
		this.hpSkill = curMaxHp;
		this.attSkill = attSkill;
		this.defSkill = defSkill;
		
		// declare other stuff
		ownedWeapons.add(EquipmentList.getWeapon(0));
		curWeapon = ownedWeapons.get(0); // give default weapon
		ownedArmour.add(EquipmentList.getArmour(0));
		curArmour = ownedArmour.get(0); // give default armour
		
		coins = 30; // start with enough coins to buy weapon or armour
	}

	public List<Weapon> getOwnedWeapons() {
		return ownedWeapons;
	}

	public List<Armour> getOwnedArmour() {
		return ownedArmour;
	}

	public void givePlayerCoins(int amount) {
		coins += amount;
	}

	public void removePlayerCoins(int amount) {
		coins -= amount;
		if (coins < 0){
			coins = 0;
		}
	}

	public void addWeapon(Weapon weapon) {
		ownedWeapons.add(weapon);
	}

	public void addArmour(Armour armour) {
		ownedArmour.add(armour);
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	@Override
	public void finishFight(boolean isWinner) {
		if(isWinner){
			wins++; // winner, add to wins
		}else{
			losses++; // loser, add to losses
		}
	}

}
