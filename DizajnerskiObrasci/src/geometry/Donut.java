package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


import geometry.Line;
import geometry.Point;

public class Donut extends Circle implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, Color color) {
		this(center, radius, innerRadius);
		super.setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius, color);
		super.setInnerColor(innerColor);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2,
				getInnerRadius() * 2);
	}

	@Override
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return dFromCenter > innerRadius && super.contains(x, y);
	}

	@Override
	public void draw(Graphics gr) {
		
		Graphics2D g = (Graphics2D)gr.create();
		
	
        Shape donut= createDonut();
		
        g.setColor(getInnerColor());
        g.fill(donut);
        g.setColor(getColor());
        
        g.draw(donut);
        
        if (isSelected()) {
			selected(g);
        }
        
        g.dispose();
		
	}
	
	public Shape createDonut() {
		
		Shape outer=new Ellipse2D.Double(
	            getCenter().getX() -getRadius(), 
	            getCenter().getY() -getRadius(),
	            getRadius()*2, 
	            getRadius()*2);
		
		Shape inner=new Ellipse2D.Double(
				getCenter().getX() - innerRadius, 
				getCenter().getY() - innerRadius,
	            innerRadius*2, 
	            innerRadius*2);
		
		Area area = new Area(outer);
        area.subtract(new Area(inner));
        return area;
	}


	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		super.selected(g);
		new Line(new Point(super.getCenter().getX() - innerRadius, super.getCenter().getY()),
				new Point(super.getCenter().getX() + innerRadius, super.getCenter().getY())).selected(g);
		new Line(new Point(super.getCenter().getX(), super.getCenter().getY() - innerRadius),
				new Point(super.getCenter().getX(), super.getCenter().getY() + innerRadius)).selected(g);
	}
	
	
	
	
	

	@Override
	public boolean compareTo(Object o) {
		if(o instanceof Donut)
		{
			return (getCenter().compareTo(((Donut) o).getCenter()) && getRadius()==((Donut)o).getRadius() && getInnerRadius()==((Donut)o).getInnerRadius());
		
		}
		else 
			return false;
	}

	@Override
	public String toString() {
		return "Donut: center" + super.getCenter().getCordinatesText()+" radius:"+ getRadius()+" InnerRadius:"+ getInnerRadius()+" "+ getColorText() + getInnerColorText();
	}

	@Override
	public Donut clone() {
		Donut donutClone = new Donut();
		donutClone.setCenter(new Point(getCenter().getX(), getCenter().getY()));
		donutClone.setRadius(getRadius());
		donutClone.setInnerRadius(getInnerRadius());
		donutClone.setColor(getColor());
		donutClone.setInnerColor(getInnerColor());
		
		return donutClone; 
		
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

}
