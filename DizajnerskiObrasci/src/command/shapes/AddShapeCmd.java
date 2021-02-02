package command.shapes;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	
	public AddShapeCmd(DrawingModel model,Shape shape) {
		this.shape = shape;
		this.model=model;
	}

	@Override
	public void execute() {
		model.add(shape);

	}

	@Override
	public void unexecute() {
		model.remove(shape);

	}
	

	@Override
	public String toString() {
		
		return "Added:"+ shape.toString();
	}
	
	

}
