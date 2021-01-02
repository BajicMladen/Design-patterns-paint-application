package command.shapes;


import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	private int i;
	
	public RemoveShapeCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		i=model.getShapes().indexOf(shape);
		model.getShapes().remove(i);

	}

	@Override
	public void unexecute() {
		model.getShapes().add(i,shape);

	}

}
