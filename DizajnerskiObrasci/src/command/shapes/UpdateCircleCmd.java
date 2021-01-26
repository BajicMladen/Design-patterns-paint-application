package command.shapes;

import command.Command;
import geometry.Circle;

public class UpdateCircleCmd implements Command {
	private Circle oldState;
	private Circle newState;
	
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldState,Circle newState) {
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		original = (Circle)oldState.clone();

		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		
		oldState.setRadius(newState.getRadius());
		
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		
		oldState.setRadius(original.getRadius());
		
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		

	}

	@Override
	public String toString() {
		return "Updated: "+ original.toString()+" --> "+newState.toString();
	}
	
	

}
