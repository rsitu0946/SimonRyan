package simon;

import java.awt.Color;

import gui.Components.Action;
import gui.Components.Clickable;

public interface ButtonInterfaceRyan extends Clickable {

	static Object getAButton() {
		
		return null;
	}
	
	static void setAction(Action a){
		
	}

	void setColor(Color blue);

	void setX(int i);

	void setY(int i);

	void highlight();


}
