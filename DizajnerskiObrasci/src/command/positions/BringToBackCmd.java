package command.positions;

import javax.swing.JOptionPane;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	 public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.index=model.getShapes().indexOf(shape);
	}
	
	

	@Override
	public void execute() {	
			model.remove(shape);
			model.getShapes().add(0,shape);	

	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.getShapes().add(index,shape);

	}



	@Override
	public String toString() {
		return "BringToFront: " + shape.toString();
	}
	
	

}
