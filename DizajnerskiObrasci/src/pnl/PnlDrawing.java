package pnl;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;
import geometry.SurfaceShape;

@SuppressWarnings("serial")
public class PnlDrawing extends JPanel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public PnlDrawing() {

	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> is = shapes.iterator();
		while (is.hasNext()) {
			Shape supp = is.next();
			if (supp instanceof SurfaceShape) {
				((SurfaceShape) supp).fill(g);
			}
			supp.draw(g);
		}

	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

}
