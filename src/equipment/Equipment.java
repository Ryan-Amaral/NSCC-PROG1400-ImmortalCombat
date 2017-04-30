package equipment;

import javax.swing.ImageIcon;

/**
 * Abstract class that Armour and Weapon extend.
 * @author Ryan
 *
 */
public abstract class Equipment {

	protected String name;
	protected ImageIcon icon;
	protected int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
