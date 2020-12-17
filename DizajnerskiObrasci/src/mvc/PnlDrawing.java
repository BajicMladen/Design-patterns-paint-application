package mvc;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;
import geometry.SurfaceShape;

@SuppressWarnings("serial")
public class PnlDrawing extends JPanel {

	DrawingModel model = new DrawingModel();
	
	

	public DrawingModel getModel() {
		return model;
	}



	public void setModel(DrawingModel model) {
		this.model = model;
	}



	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
		
	}
	
	

}
