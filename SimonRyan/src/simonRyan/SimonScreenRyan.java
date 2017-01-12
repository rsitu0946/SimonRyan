package simonRyan;

import java.awt.Color;
import java.util.ArrayList;

import gui.Components.Action;
import gui.Components.Button;
import gui.Components.ClickableScreen;
import gui.Components.TextLabel;
import gui.Components.Visible;
import partnerCodeInHerePlease.Buttons;
import partnerCodeInHerePlease.Progress;

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
		Buttons b;
		int rand = (int) (Math.random()*button.length);
		while(rand == lastSelectedButton){
			rand = (int) (Math.random()*button.length);
		}
		b = (Buttons) button[rand];
		return getMove(b);
	}

	private MoveInterfaceRyan getMove(Buttons b) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceRyan getProgress() {	
		return new Progress();
	}

	private void addButtons() {
		int numberOfButtons = 6;
		Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green, Color.orange, Color.PINK};
		String[] names = {"red","blue","yellow","green","orange","pink"};
		button = new ButtonInterfaceRyan[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++){
			final ButtonInterfaceRyan b = getAButton(); 
			button[i] = getAButton();
			button[i].setName(names[i]);
			button[i].setColor(colors[i]);
			button[i].setX(300);
			button[i].setY(300);
			b.dim();
			ButtonInterfaceRyan.setAction(new Action(){	
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try{
									Thread.sleep(800);
								}catch(InterruptedException e){
									e.printStackTrace();
								}
								b.dim();		
							}	
						});
						blink.start();
						
						if(b == move.get(sequenceIndex).getButton() && acceptingInput){
							sequenceIndex++;
							if(sequenceIndex == move.size()){
								Thread nextRound = new Thread(SimonScreenRyan.this);
								nextRound.start();
								
							}
						}else{
							ProgressInterfaceRyan.gameOver();
						}
					}
				}
			});
		}
	}

	private ButtonInterfaceRyan getAButton() {
		return new Buttons();
	}

	public void run() {
		label.setText("");
		nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		progress.setRound(roundNumber);
		move.add(randomMove());
		progress.setSequenceSize(move.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");;
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceRyan b = null;
		for(MoveInterfaceRyan m: move){
			if(b != null){
				b.dim();
			}
			b = m.getButton();
			b.highlight();
			int sleepTime = (int) ((long)(2000*(2.0/(roundNumber+2))));
			try{
				Thread.sleep(sleepTime);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void gameOver(){
	//	progress.gameOver();
	}

}
