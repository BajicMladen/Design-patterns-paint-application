package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Circle extends SurfaceShape implements Cloneable{

	private int radius;
	private Point center;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color color) {
		this(center, radius);
		super.setColor(color);
	}

	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius, color);
		super.setInnerColor(innerColor);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);

	}

	@Override
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2,
				this.radius * 2);
		fill(g);
		if (isSelected()) {
			selected(g);
		}

	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		center.selected(g);
		Point left = new Point(center.getX() - radius, center.getY());
		Point right = new Point(center.getX() + radius, center.getY());
		Point up = new Point(center.getX(), center.getY() - radius);
		Point down = new Point(center.getX(), center.getY() + radius);

		left.selected(g);
		right.selected(g);
		up.selected(g);
		down.selected(g);

	}
	
	
	
	

	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle)
		{
			return (int) (this.radius-((Circle) o).radius);
		}
		else 
			return 0;
	}

	@Override
	public String toString() {
		return "Circle:center" + center.getCordinatesText()+"radius:("+ this.radius+")"+ getColorText() + getInnerColorText();
	}

	@Override
	 public Circle clone(){
		
		Circle circleClone = new Circle();
		circleClone.setCenter(new Point(this.getCenter().getX(), this.getCenter().getY()));
		circleClone.setRadius(getRadius());
		circleClone.setColor(getColor());
		circleClone.setInnerColor(getInnerColor());
		
		return circleClone;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

}
