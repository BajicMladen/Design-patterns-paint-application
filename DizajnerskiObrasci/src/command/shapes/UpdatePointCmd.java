package command.shapes;

import command.Command;
import geometry.Point;

public class UpdatePointCmd implements Command {
	private Point oldState;
	private Point newState;
	
	private Point original=new Point();
	
	public UpdatePointCmd(Point oldState,Point newState) {
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		original=oldState.clone();
		oldState=newState.clone();

	}

	@Override
	public void unexecute() {
		oldState=original.clone();

	}

}
