package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;


public class HexagonAdapter extends SurfaceShape implements Cloneable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	 public HexagonAdapter() {
			
		}
	
	 public HexagonAdapter(Hexagon hexagon) {
		this.hexagon=hexagon;
	}
	 
	 public HexagonAdapter(Hexagon hexagon,Color color,Color innerColor) {
			this.hexagon=hexagon;
			this.setColor(color);
			this.setInnerColor(innerColor);
		}
	 

	 	

	@Override
	public boolean compareTo(Object o) {
		if(o instanceof HexagonAdapter)
		{
			return (getX()==((HexagonAdapter)o).getX() && getY()==((HexagonAdapter)o).getY() && getR()==((HexagonAdapter)o).getR()); //Compare center and radius
		}
		else 
			return false;
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
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	
	
	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "hexagon: Centar"+center.getCordinatesText()+ "radius(" + hexagon.getR() + ")" + this.getColorText()+ this.getInnerColorText();
		
	}
	
	

	@Override
	public String getInnerColorText() {
		return "Fill:(" + this.hexagon.getAreaColor().getRed() + "." + this.hexagon.getAreaColor().getGreen() + "."
				+ this.hexagon.getAreaColor().getBlue() + ")";
	}

	@Override
	public String getColorText() {
		return "Border:(" + this.hexagon.getBorderColor().getRed() + "." + this.hexagon.getBorderColor().getGreen() + "."
				+ this.hexagon.getBorderColor().getBlue() + ")";
	}

	@Override
	public HexagonAdapter clone(){
		Hexagon hexagon = new Hexagon(this.getX(), this.getY(), this.getR());
		HexagonAdapter hexClone = new HexagonAdapter(hexagon);
		hexClone.setColor(this.getColor());
		hexClone.setInnerColor(this.getInnerColor());
		
		return hexClone;
		
	}
	
	@Override
	public void selected(Graphics g) {		
	}
	
	@Override
	public void fill(Graphics g) {				
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
