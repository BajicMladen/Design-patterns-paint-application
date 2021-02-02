package command.positions;


import command.Command;
import geometry.Shape;
import mvc.DrawingModel;
//Command for moving shape all way to front
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
			model.remove(shape);
			model.add(shape);		

	}

	@Override
	public void unexecute() {//Undo 
		model.remove(shape);
		model.getShapes().add(index,shape);

	}


	@Override
	public String toString() {//For log
		return "BringToFront:" + shape.toString();
	}
	
	

}
