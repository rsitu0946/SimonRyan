package simonRyan;

import gui.GUIApplication;

public class SimonGameRyan extends GUIApplication {

	public SimonGameRyan(int width, int height) {
		super(width, height);
		
	}

	public void initScreen() {
		SimonScreenRyan ssr = 
				new SimonScreenRyan(getWidth(),getHeight());
		setScreen(ssr);
	}
	
	public static void main(String[] args) {
		SimonGameRyan sgr = new SimonGameRyan(800,500);
		Thread game = new Thread(sgr);
		game.start();
	}

}
