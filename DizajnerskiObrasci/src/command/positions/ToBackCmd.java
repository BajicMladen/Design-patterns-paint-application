package command.positions;

import java.util.Collections;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	private DrawingModel model;
	private int index;
	private Shape shape;
	
	public ToBackCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	

	@Override
	public void execute() {
		this.index = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), index, index-1);

	}

	@Override
	public void unexecute() {
		this.index = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), index, index+1);

	}

}
