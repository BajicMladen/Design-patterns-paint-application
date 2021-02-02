package command.select;

import command.Command;
import geometry.Shape;

public class DeselectShapeCmd implements Command {
	
	private  Shape s;
	// Class for deselecting shape 
	public DeselectShapeCmd(Shape s) {
		this.s=s;
	}

	@Override
	public void execute() { // deselect
		s.setSelected(false);

	}

	@Override
	public void unexecute() { //undo 
		s.setSelected(true);

	}

	@Override
	public String toString() { // for log
		return "Deselected:" + s.toString();
	}
	
	

}
