package geometry;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Line;
import geometry.Point;

public class Donut extends Circle {

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
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2,
				this.innerRadius * 2);
		if (isSelected()) {
			selected(g);
		}
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

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

}
