package simonRyan;

import java.awt.Color;
import java.util.ArrayList;

import gui.Components.Action;
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
			final ButtonInterfaceRyan b = getAButton(); 
			b.setColor(Color.blue);
			b.setX(300);
			b.setY(300);
			b.setAction(new Action(){
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try{
									Thread.sleep(800);
									dim();
									blink.start();
								}catch(InterruptedException e){
									e.printStackTrace();
								}
								
								if(b == move.get(sequenceIndex).getButton()){
									sequenceIndex++;
									if(sequenceIndex == move.size()){
										Thread nextRound = new Thread(SimonScreenRyan.this);
										nextRound.start();
										viewObjects.add(b);
									}
								}else{
									ProgressInterfaceRyan.gameOver();
								}
								
								
								
							}

							private void dim() {
								
								
							}
						});
					}
				}
			});
		}
	}

	private ButtonInterfaceRyan getAButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		label.setText("");
		nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		
	}

}
