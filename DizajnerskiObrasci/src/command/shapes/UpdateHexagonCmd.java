package command.shapes;

import adapter.HexagonAdapter;
import command.Command;

public class UpdateHexagonCmd implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter oldState,HexagonAdapter newState) {
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		original=oldState.clone();
				
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());

	}

	@Override
	public void unexecute() {
		
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}

	@Override
	public String toString() {
		return "Updated: "+ original.toString()+" --> "+newState.toString();
	}
	
	

}
