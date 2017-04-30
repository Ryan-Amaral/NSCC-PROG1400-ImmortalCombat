package people;

import javax.swing.ImageIcon;

import equipment.Armour;
import equipment.Weapon;

/**
 * Abstract class that Player and Opponent extends from.
 * 
 * @author Ryan
 *
 */
public abstract class Fighter {

	protected String name;
	protected int race;
	protected ImageIcon icon;
	protected int hpSkill;
	protected int curHp; // in battle this is the fighters current health
	protected int curMaxHp; // this is the max health the fighter has
	protected int curHpBoost; // boost from armour
	protected int attSkill; // how much damage fighter does
	protected int curAttBoost; // boost from weapon
	protected int defSkill; // how resistant to damage the fighter is
	protected int curDefBoost; // boost from armour
	protected int curCritBoost; // boost from weapon, chance to do extra damage
	protected Weapon curWeapon; // the weapon the fighter is fighting with
	protected Armour curArmour; // the armour the fighter is currently using
	protected int coins; // how many coins the fighter is holding

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getCurHp() {
		return curHp;
	}

	public void setCurHp(int curHp) {
		this.curHp = curHp;
	}

	public int getCurMaxHp() {
		return curMaxHp;
	}

	public void setCurMaxHp(int curMaxHp) {
		this.curMaxHp = curMaxHp;
	}

	public int getCurHpBoost() {
		return curHpBoost;
	}

	public void setCurHpBoost(int curHpBoost) {
		this.curHpBoost = curHpBoost;
	}

	public int getAttSkill() {
		return attSkill;
	}

	public void setAttSkill(int attSkill) {
		this.attSkill = attSkill;
	}

	public int getCurAttBoost() {
		return curAttBoost;
	}

	public void setCurAttBoost(int curAttBoost) {
		this.curAttBoost = curAttBoost;
	}

	public int getDefSkill() {
		return defSkill;
	}

	public void setDefSkill(int defSkill) {
		this.defSkill = defSkill;
	}

	public int getCurDefBoost() {
		return curDefBoost;
	}

	public void setCurDefBoost(int curDefBoost) {
		this.curDefBoost = curDefBoost;
	}

	public int getCurCritBoost() {
		return curCritBoost;
	}

	public void setCurCritBoost(int curCritBoost) {
		this.curCritBoost = curCritBoost;
	}

	public Weapon getCurWeapon() {
		return curWeapon;
	}

	public void setCurWeapon(Weapon curWeapon) {
		this.curWeapon = curWeapon;
		// also change stat boosts
		curAttBoost = curWeapon.getAttBoost();
		curCritBoost = curWeapon.getCritBoost();
	}

	public Armour getCurArmour() {
		return curArmour;
	}

	public void setCurArmour(Armour curArmour) {
		this.curArmour = curArmour;
		// also change stat boost
		curHpBoost = curArmour.getHpBoost();
		curMaxHp = curHpBoost + hpSkill;
		curHp = curMaxHp;
		curDefBoost = curArmour.getDefBoost();
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void recieveDamage(int damage){
		curHp -= damage;
		if(curHp < 0){
			curHp = 0;
		}
	}
	
	public abstract void finishFight(boolean isWinner);

	public int getHpSkill() {
		return hpSkill;
	}

	public void setHpSkill(int hpSkill) {
		this.hpSkill = hpSkill;
	}

}
