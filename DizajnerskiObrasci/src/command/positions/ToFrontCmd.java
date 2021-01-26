package command.positions;

import java.util.Collections;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public ToFrontCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		this.index = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), index, index+1);

	}

	@Override
	public void unexecute() {
		this.index=model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), index, index-1);

	}

	@Override
	public String toString() {
		return "ToBack : " + shape.toString();
	}
	
	

}
