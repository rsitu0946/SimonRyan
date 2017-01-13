package simonRyan;

import gui.Components.Visible;

public interface ProgressInterfaceRyan extends Visible{

	void setRound(int roundNumber);

	void setSequenceLength(int size);

	/**
	 * changes display for when game has ended
	 */
	void gameOver();

}
