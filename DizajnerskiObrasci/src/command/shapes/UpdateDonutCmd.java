package command.shapes;

import command.Command;
import geometry.Donut;

public class UpdateDonutCmd implements Command {
	
	private Donut oldState;
	private Donut newState;
	
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut oldState,Donut newState) {
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());

	}

	@Override
	public String toString() {
		return "Updated:"+ original.toString()+" --> "+newState.toString();
	}
	
	

}
