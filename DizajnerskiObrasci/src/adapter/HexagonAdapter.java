package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape{
	
	private Hexagon hexagon;
	
	 public HexagonAdapter(Hexagon h) {
		this.hexagon=h;
	}
	 
	 public HexagonAdapter(Hexagon h,Color innerColor,Color color) {
			this.hexagon=h;
			this.setColor(color);
			this.setInnerColor(innerColor);
		}
	 
	 
	

	@Override
	public void fill(Graphics g) {
		
		
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
	}

	@Override
	public void selected(Graphics g) {
		
	}
		
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}

	
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public void setY(int y) {
		hexagon.setY(y);
	}
	

}
