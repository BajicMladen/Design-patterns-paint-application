package command.positions;

import javax.swing.JOptionPane;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public BringToFrontCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
		this.index=model.getShapes().indexOf(shape);
	}
	

	@Override
	public void execute() {
		if(index == model.getShapes().size()-1) {
			
			JOptionPane.showMessageDialog(null, "Element is already on top");
		}
		else {
			model.remove(shape);
			model.add(shape);
		}

	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.getShapes().add(index,shape);

	}


	@Override
	public String toString() {
		return "BringToFront:" + shape.toString();
	}
	
	

}
