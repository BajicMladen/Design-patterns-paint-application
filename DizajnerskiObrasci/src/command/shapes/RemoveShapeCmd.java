package command.shapes;




import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private int k;
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	private ArrayList<Integer> index=new ArrayList<Integer>();
	
	
	public RemoveShapeCmd(DrawingModel model,ArrayList<Shape> shapes) {
		this.model=model;
		this.shapes=shapes;
		
	}

	@Override
	public void execute() {
		for(Shape shape : shapes) {
		k=model.getShapes().indexOf(shape);
		index.add(k);
		model.getShapes().remove(k);
		}
		
	}

	@Override
	public void unexecute() {
		Collections.sort(index);
		for(int i=0;i<shapes.size();i++) {
		model.getShapes().add(index.get(i),shapes.get(i));
		
		}	
		index.clear();
	}

}
