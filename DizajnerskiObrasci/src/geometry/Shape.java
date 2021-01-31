package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;


public abstract class Shape extends Observable implements Serializable,Comparable<Object>{

	private boolean selected;
	private Color color;

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics g);

	public abstract void selected(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
		
		
	}		


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

	
	public String getColorText() {
		return "BorderColor("+ color.getRed()+","+ color.getGreen()+","+ color.getBlue()+")";
	}
	
	

}
