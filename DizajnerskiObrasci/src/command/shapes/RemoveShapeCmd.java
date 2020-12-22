package command.shapes;


import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	DrawingModel model;
	Shape shape;
	
	public RemoveShapeCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.add(shape);

	}

}
