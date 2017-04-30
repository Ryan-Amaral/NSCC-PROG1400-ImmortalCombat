package app;

public class GameMaster {

	private static PvpGameFrame frame;
	
	/**
	 * Start the app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new PvpGameFrame();
		frame.setVisible(true);
	}
	
	/**
	 * everyone can access this to change gui
	 * @return
	 */
	public static PvpGameFrame getGui() {
		return frame;
	}

}
