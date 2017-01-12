package partnerCodeInHerePlease;

import simonRyan.ButtonInterfaceRyan;
import simonRyan.MoveInterfaceRyan;

public class Move implements MoveInterfaceRyan {

	private ButtonInterfaceRyan b; 
	
	public Move(ButtonInterfaceRyan b) {
		this.b = b;
	}

	public ButtonInterfaceRyan getButton() {
		return b;
	}

}
