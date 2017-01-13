package simonRyan;

import java.awt.Color;

import gui.Components.Action;
import gui.Components.Clickable;

public interface ButtonInterfaceRyan extends Clickable{

	void setColor(Color color);

	/**
	 * changes button's displayed color to something brighter
	 */
	void highlight();

	/**
	 * changes button's displayed color to something dimmer
	 */
	void dim();

	void setAction(Action action);

	
	void setName(String name);

	void setX(int i);

	void setY(int i);
}
