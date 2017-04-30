package people;

import java.util.Random;

/**
 * Stuff that the opponent says on a loss/win.
 * 
 * @author Ryan
 *
 */
public class OpponentSayings {

	// the taunts
	private final static String[] taunts = { "Hahaha!",
									   		 "You suck!",
									   		 "You fight like a sissy!"};
	//the SLSs
	private final static String[] soreLoserSayings = { "You jerk!",
												 	   "I'm telling my mom!",
												 	   "At least I'm not ugly like you!"};

	public static final String getRandomSaying(boolean isSls) {
		Random random = new Random();
		int randInt;
		String selectedSaying;

		if (!isSls) { // choose a taunt
			randInt = random.nextInt(taunts.length);
			selectedSaying = taunts[randInt];
		} else { // choose a sore loser saying
			randInt = random.nextInt(soreLoserSayings.length);
			selectedSaying = soreLoserSayings[randInt];
		}
		return selectedSaying;
	}

}
