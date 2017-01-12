package simonRyan;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.Components.Action;
import gui.Components.Button;
import gui.Components.ClickableScreen;
import gui.Components.TextLabel;
import gui.Components.Visible;
import partnerCodeInHerePlease.Buttons;
import partnerCodeInHerePlease.Move;
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
		Color[] colors = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		String[] names = {"RED", "BLUE", "ORANGE", "GREEN", "YELLOW", "PURPLE"};
		int buttonCount = 6;
		button = new ButtonInterfaceRyan[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			button[i] = getAButton();
			button[i].setName(names[i]);
			button[i].setColor(colors[i]);
			button[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(buttonCount))));
			button[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(buttonCount))));
			final ButtonInterfaceRyan b = button[i];
			System.out.println(b+" has x = "+b.getX()+", y ="+b.getY());
			b.dim();
			button[i].setAction(new Action() {

				public void act() {

						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
								
							}
						});
						buttonPress.start();
						if(acceptingInput && move.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else if(acceptingInput){
							gameOver();
							return;
						}
						if(sequenceIndex == move.size()){
							Thread nextRound = new Thread(SimonScreenRyan.this);
							nextRound.start();
						}
					}
			});
			viewObjects.add(button[i]);
		}
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
		int rand = (int) (Math.random()*button.length);
		while(rand == lastSelectedButton){
			rand = (int) (Math.random()*button.length);
		}
		lastSelectedButton = rand;
		return new Move(button[rand]);
	}

//	private MoveInterfaceRyan getMove(Buttons b) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceRyan getProgress() {	
		return new Progress();
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
		progress.gameOver();
	}

}
