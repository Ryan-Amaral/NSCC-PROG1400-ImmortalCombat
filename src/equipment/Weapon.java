package equipment;

import javax.swing.ImageIcon;

/**
 * What the player attacks with.
 * @author Ryan
 *
 */
public class Weapon extends Equipment {
	
	private int attBoost; // boost to the attack stat
	private int critBoost; // boost to the critical hit chance stat
	private String attVerb; // the verb used in past tense e.g. "slashed"
	
	public Weapon(String name, ImageIcon icon, int attBoost, int critBoost, String attVerb, int price){
		this.name = name;
		this.icon = icon;
		this.attBoost = attBoost;
		this.critBoost = critBoost;
		this.attVerb = attVerb;
		this.price = price;
	}

	public int getAttBoost() {
		return attBoost;
	}

	public int getCritBoost() {
		return critBoost;
	}

	public String getAttVerb() {
		return attVerb;
	}

}
