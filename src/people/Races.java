package people;

import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * List of races for the fighters.
 * 
 * @author Ryan
 *
 */
public final class Races {
	
	public static final int HUMAN = 0;
	public static final int GOBLIN = 1;
	public static final int ELF = 2;
	public static final int DWARF = 3;
	public static final int ANGEL = 4;
	public static final int DEMON = 5;
	
	// all of the icons for the races
	private final static List<ImageIcon> raceIcons = Arrays.asList(
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/human.jpg")), // human
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/goblin.jpg")), // goblin
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/elf.jpg")), // elf
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/dwarf.png")), // dwarf
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/angel.jpg")), // angel
		new ImageIcon(Races.class.getClassLoader().getResource("images/races/demon.jpg")) // demon
		);
	
	/**
	 * Get the race icon for the currently selected race.
	 * 
	 * @param race
	 * @return
	 */
	public final static ImageIcon getRaceIcon(int race){
		return raceIcons.get(race);
	}
	
	public final static int amountOfRaces(){
		return raceIcons.size();
	}

}
