package equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * List of all weapons and armours.
 * 
 * @author Ryan
 *
 */
public final class EquipmentList {
	
	// classes of weapons/armours for the enemy
	public final static int EASY_WEAPON = 0;
	public final static int MEDIUM_WEAPON = 1;
	public final static int HARD_WEAPON = 2;
	public final static int EASY_ARMOUR = 0;
	public final static int MEDIUM_ARMOUR = 1;
	public final static int HARD_ARMOUR = 2;
	
	// weapons, weakest to strongest
	private final static List<Weapon> allWeapons = Arrays.asList(
		new Weapon("Fists", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/fist.jpg")), 0, 0, "punched", 0),
		new Weapon("Big Stick", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/stick.jpg")), 10, 5, "whacked", 30),
		new Weapon("Metal Rod", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/metalrod.jpg")), 23, 5, "whacked", 75),
		new Weapon("Gauntlets", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/gauntlets.jpg")), 29, 5, "punched", 150),
		new Weapon("Dagger", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/dagger.jpg")), 35, 10, "stabbed", 300),
		new Weapon("Sword", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/weapons/sword.png")), 49, 15, "slashed", 600));
	
	// armours, weakest to strongest
	private final static List<Armour> allArmours = Arrays.asList(
		new Armour("Nothing", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/nothing.png")), 0, 0, 0),
		new Armour("Skimpy Suit", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/skimpy.jpg")), 10, 8, 30),
		new Armour("Civilian Clothes", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/clothes.jpg")), 19, 17, 75),
		new Armour("Leather Clothes", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/leatherclothes.jpg")), 26, 22, 150),
		new Armour("Leather Armour", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/leatherarmour.jpg")), 45, 40, 300),
		new Armour("Light Armour", new ImageIcon(EquipmentList.class.getClassLoader().getResource("images/armours/LightArmor.png")), 60, 48, 600));
	
	/**
	 * Returns the selected weapon.
	 * 
	 * @param index
	 * @return
	 */
	public final static Weapon getWeapon(int index){
		return allWeapons.get(index);
	}
	
	/**
	 * Returns the selected armour.
	 * 
	 * @param index
	 * @return
	 */
	public final static Armour getArmour(int index){
		return allArmours.get(index);
	}
	
	/**
	 * Returns all weapons except for the first ones because the player already has them.
	 * 
	 * @return
	 */
	public final static List<Weapon> getShopWeapons(){
		return new ArrayList<Weapon>(allWeapons.subList(1, allWeapons.size()));
	}
	
	/**
	 * Returns all armours except for the first ones because the player already has them.
	 * 
	 * @return
	 */
	public final static List<Armour> getShopArmours(){
		return new ArrayList<Armour>(allArmours.subList(1, allArmours.size()));
	}
	
	/**
	 * Return a random weapon to the enemy.
	 * 
	 * @param difficulty
	 * @return
	 */
	public final static Weapon getEnemyWeapon(int difficulty){
		int weaponIndex;
		int thirdOfAll = allWeapons.size() / 3; // divide all by 3 to get thirds for difficulty selection
		Random random = new Random();
		
		if(difficulty == EASY_WEAPON){
			weaponIndex = random.nextInt(thirdOfAll); // choose from first third
		}else if(difficulty == MEDIUM_WEAPON){
			weaponIndex = thirdOfAll + random.nextInt(thirdOfAll); // choose from middle third
		}else if(difficulty == HARD_WEAPON){
			weaponIndex = (thirdOfAll * 2) + random.nextInt(thirdOfAll); // choose from last third
		}else{
			weaponIndex = random.nextInt(allWeapons.size()); // innapropriate value get any random weapon to not crash
		}
		
		return allWeapons.get(weaponIndex);
	}
	
	/**
	 * Return a random armour to the enemy.
	 * 
	 * @param difficulty
	 * @return
	 */
	public final static Armour getEnemyArmour(int difficulty){
		int armourIndex;
		int thirdOfAll = allArmours.size() / 3; // divide all by 3 to get thirds for difficulty selection
		Random random = new Random();
		
		if(difficulty == EASY_ARMOUR){
			armourIndex = random.nextInt(thirdOfAll); // choose from first third
		}else if(difficulty == MEDIUM_ARMOUR){
			armourIndex = thirdOfAll + random.nextInt(thirdOfAll); // choose from middle third
		}else if(difficulty == HARD_ARMOUR){
			armourIndex = (thirdOfAll * 2) + random.nextInt(thirdOfAll); // choose from last third
		}else{
			armourIndex = random.nextInt(allArmours.size()); // innapropriate value get any random armour to not crash
		}
		
		return allArmours.get(armourIndex);
	}

}
