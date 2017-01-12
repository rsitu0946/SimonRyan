package simonRyan;

import java.awt.Color;

import gui.Components.Action;
import gui.Components.Clickable;

public interface ButtonInterfaceRyan extends Clickable {
	
	void setAction(Action a);

	void setColor(Color color);

	void highlight();

	void dim();

	void setName(String string);

	void setX(int i);

	void setY(int i);

}
