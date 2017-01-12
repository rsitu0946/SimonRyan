package simonRyan;

import gui.Components.Visible;

public interface ProgressInterfaceRyan extends Visible {

	static void gameOver() {
		
	}
	
	void setRound(int roundNumber);
	
	void setSequenceSize(int length);
}
