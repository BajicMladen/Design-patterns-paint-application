package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;

	}
	
	public Point(int x, int y,Color color) {
		this.x = x;
		this.y = y;
		super.setColor(color);

	}

	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	
	
	

	@Override
	public boolean contains(int x, int y) {

		return this.distance(x, y) <= 3;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		if (isSelected()) {
			selected(g);
		}

	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);

	}
	
	

	@Override
	public boolean compareTo(Object o) {
		if(o instanceof Point) {
			
			return (getX()==((Point) o).getX() && getY()==((Point) o).getY());	// Comparing two points  based of point cordinates								
		}
		else
			return false;
	}
	

	@Override
	public String toString() {
		return "Point"+getCordinatesText()+getColorText();
	}
	
	public String getCordinatesText() {
		return "(" + x + "," + y + ")";
	}
	
		

	@Override
	public Point clone(){
	Point pointClone = new Point();
	pointClone.setX(getX());
	pointClone.setY(getY());
	pointClone.setColor(getColor());
		return pointClone;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
