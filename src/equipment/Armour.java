package equipment;

import javax.swing.ImageIcon;

/**
 * Helps the fighter survive longer.
 * @author Ryan
 *
 */
public class Armour extends Equipment {

	private int hpBoost; // boost to the hit points stat
	private int defBoost; // boost to the defence stat

	public Armour(String name, ImageIcon icon, int hpBoost, int defBoost, int price){
		this.name = name;
		this.icon = icon;
		this.hpBoost = hpBoost;
		this.defBoost = defBoost;
		this.price = price;
	}
	
	public int getHpBoost() {
		return hpBoost;
	}

	public int getDefBoost() {
		return defBoost;
	}

}
