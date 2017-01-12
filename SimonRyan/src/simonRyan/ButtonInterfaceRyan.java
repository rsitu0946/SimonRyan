package simonRyan;

import java.awt.Color;

import gui.Components.Action;
import gui.Components.Clickable;

public interface ButtonInterfaceRyan extends Clickable {
	
	static void setAction(Action a) {
		// TODO Auto-generated method stub
		
	}

	void setColor(Color color);

	void highlight();

	void dim();

	void setName(String string);

	void setX(int i);

	void setY(int i);


}
