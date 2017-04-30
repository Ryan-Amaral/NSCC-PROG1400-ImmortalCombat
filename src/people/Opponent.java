package people;

import java.util.Random;

import equipment.EquipmentList;

/**
 * The npc, new every fight.
 * 
 * @author Ryan
 *
 */
public class Opponent extends Fighter {
	
	private String curSaying;
	
	public Opponent(int difficulty){
		Random random = new Random(); // RNG for stuff
		name = Names.getRandomName(); // get a name
		race = random.nextInt(Races.amountOfRaces());
		icon = Races.getRaceIcon(race);
		rollStats(difficulty); // roll stats for the enemy to have
		// weapon and armour
		setCurWeapon(EquipmentList.getEnemyWeapon(difficulty));
		setCurArmour(EquipmentList.getEnemyArmour(difficulty));
		coins  = 10 + (difficulty * 30) + random.nextInt(50); // get a random amount of coins weighted to difficulty
	}
	
	private void rollStats(int difficulty){
		Random random = new Random(); // RNG for rolling
		// total stats of 280 for easy, 330 for medium, 380 for hard
		int statPool = (50 * (difficulty)) + 280;
		
		hpSkill = (statPool / 2) + random.nextInt(statPool / 3); // hp takes up most of stats
		statPool -= hpSkill;
		
		attSkill = (statPool / 2) + random.nextInt(statPool / 3);
		statPool -= attSkill;
		
		defSkill = statPool;
	}
	
	public String getCurSaying() {
		return curSaying;
	}

	@Override
	public void finishFight(boolean isLooser) {
		curSaying = OpponentSayings.getRandomSaying(isLooser); // get random taunt or SLS
	}

}
