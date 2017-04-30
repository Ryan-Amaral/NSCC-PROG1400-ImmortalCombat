package scenes;

import java.util.Random;

import javax.swing.ImageIcon;

import people.Races;

/**
 * This class has methods to aide in creating a player.
 * 
 * @author Ryan
 *
 */
public class PlayerCreator {
	
	private int playerRace = 0; // races in the Races class
	private ImageIcon playerIcon;
	private String playerName;
	private int hpStat;
	private int attStat;
	private int defStat;
	
	public PlayerCreator(){
		playerIcon = Races.getRaceIcon(Races.HUMAN); // set default icon
	}

	public int getHpStat() {
		return hpStat;
	}

	public int getAttStat() {
		return attStat;
	}

	public int getDefStat() {
		return defStat;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerRace() {
		return playerRace;
	}

	public void setPlayerRace(int playerRace) {
		this.playerRace = playerRace;
	}

	public ImageIcon getPlayerIcon() {
		return playerIcon;
	}

	public void setPlayerIcon(ImageIcon playerIcon) {
		this.playerIcon = playerIcon;
	}

	/**
	 * Validates the selected name of the player.
	 * 
	 * @return
	 */
	public boolean isValidName(String name){
		boolean isValid = true;
		
		//names must be max of 12 characters, letters, no spaces, ''s, -'s
		if(name.length() < 1 || name.length() > 20){
			isValid = false;
		}else{
			for(int i = 0; i < name.length(); i++){
				//char is good if it is letter, or certain characters
				if(Character.isAlphabetic(name.charAt(i)) || name.charAt(i) == '-' || name.charAt(i) == 39){
					//is valid stays true
					isValid = true;
				}else{
					isValid = false;
					break;
				}
			}
		}
		
		return isValid;
	}
	
	public void rollStats(){
		Random random = new Random(); // RNG for rolling
		// total stats of 300
		int statPool = 300;
		
		hpStat = (statPool / 2) + random.nextInt(statPool / 3); // hp takes up most of stats
		statPool -= hpStat;
		
		attStat = (statPool / 2) + random.nextInt(statPool / 3);
		statPool -= attStat;
		
		defStat = statPool;
	}

}
