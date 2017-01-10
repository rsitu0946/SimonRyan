package simonRyan;

import java.awt.Color;
import java.util.ArrayList;

import gui.Components.ClickableScreen;
import gui.Components.TextLabel;
import gui.Components.Visible;

public class SimonScreenRyan extends ClickableScreen implements Runnable{
	
	private TextLabel label;
	private ButtonInterfaceRyan[] button;
	private ProgressInterfaceRyan progress;
	private ArrayList<MoveInterfaceRyan> move;
	
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenRyan(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		move = new ArrayList<MoveInterfaceRyan>();
		//add 2 moves to start
		lastSelectedButton = -1;
		move.add(randomMove());
		move.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceRyan randomMove() {
		ButtonInterfaceRyan b;
		//code that randomly selects a ButtonInterfaceRyan
		return getMove(b);
	}

	private MoveInterfaceRyan getMove(ButtonInterfaceRyan b) {
		return null;
	}

	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceRyan getProgress() {	
		return null;
	}

	private void addButtons() {
		int numberOfButtons = 2;
		Color colors = new Color(100,180,255);
		for(int i = 0; i < numberOfButtons; i++){
			ButtonInterfaceRyan = 
		}
	}

	@Override
	public void run() {
		
	}

}
