package simonRyan;

import gui.GUIApplication;

public class SimonGameRyan extends GUIApplication {

	public SimonGameRyan(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initScreen() {
		SimonScreenRyan s = new SimonScreenRyan(getWidth(), getHeight());
		setScreen(s);
	}

	public static void main(String[] args) {
		SimonGameRyan game = new SimonGameRyan(800, 500);
		Thread app = new Thread(game);
		app.start();
	}

}
