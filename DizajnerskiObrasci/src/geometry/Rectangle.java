package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends SurfaceShape  implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int height;
	private int width;
	

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	
	public Rectangle(Point upperLeftPoint, int height, int width,Color shapeColor,Color fillColor) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
		this.setColor(shapeColor);
		this.setInnerColor(fillColor);
		
	}


	public int area() {
		return this.height * this.width;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);

	}

	@Override
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x && upperLeftPoint.getY() <= y && x <= upperLeftPoint.getX() + width
				&& y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		fill(g);
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		this.fill(g);
		if (isSelected()) {
			selected(g);
		}

	}
	
	

	@Override
	public boolean compareTo(Object o) {
		if(o instanceof Rectangle)
		{
			return(getUpperLeftPoint().compareTo(((Rectangle) o).getUpperLeftPoint()) && getHeight()==((Rectangle)o).getHeight() && getWidth()==((Rectangle)o).getWidth());
		}
		else 
			return false;
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);

	}
	
	
	

	@Override
	public String toString() {
		return "Rectangle:UperLeftPoint->" + upperLeftPoint.getCordinatesText()+" Height->"+ this.height + " Width->"+" "+ this.width+" "+getColorText() + getInnerColorText();
	}

	
	@Override
	public Rectangle clone(){
		
		Rectangle rectangle = new Rectangle();
		rectangle.setUpperLeftPoint(new Point(getUpperLeftPoint().getX(),getUpperLeftPoint().getY()));
		rectangle.setHeight(getHeight());
		rectangle.setWidth(getWidth());
		rectangle.setColor(getColor());
		rectangle.setInnerColor(getInnerColor());
		
		return rectangle;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
