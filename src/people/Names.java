package people;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Names {
	
	private static List<String> names = Arrays.asList(
			"Bob", "Joe", "Ryan", "Bart", "Bert",
			"Zoe", "Sue", "Pam", "Jill","Janice",
			"Paul", "Jeff", "Bob Loblaw", "Finn");
	
	public final static String getRandomName() {
		Random random = new Random();
		int randInt;
		String selectedName;

		randInt = random.nextInt(names.size());
		selectedName = names.get(randInt);
		return selectedName;
	}

}
