package geometry;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;

public class Line extends Shape implements Cloneable{

	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		super.setColor(color);
	}

	@Override
	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		if (isSelected()) {
			selected(g);
		}
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		middleOfLine().selected(g);

	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line)
		{
			return (int) (this.length()-((Line) o).length());
		}
		else {
			return 0;
		}
	}
	
	
	

	@Override
	public String toString() {
		return "Line:startpoint" + startPoint.getCordinatesText() + "endpoint" + endPoint.getCordinatesText() + getColorText();
	}

	@Override
	public Line clone(){
		Line lineClone = new Line();
		lineClone.setStartPoint(new Point(startPoint.getX(),startPoint.getY()));
		lineClone.setEndPoint(new Point(endPoint.getX(),endPoint.getY()));
		lineClone.setColor(getColor());
		
		return lineClone;
	}
	

	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	
	
	
	

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	

}
