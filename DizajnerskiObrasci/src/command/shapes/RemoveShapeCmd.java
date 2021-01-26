package command.shapes;



import java.util.ArrayList;
import java.util.Hashtable;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;
import observer.ShapeObserver;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Hashtable<Integer,Shape> shapes;
	
	
	
	
	public RemoveShapeCmd(DrawingModel model,ArrayList<Shape> shapes) {
		this.model=model;
		this.shapes=new Hashtable<>();
		
		for(Shape shape : shapes) {
			this.shapes.put(model.getShapes().indexOf(shape), shape);
		}
		
	}

	@Override
	public void execute() {
		
		for(Shape shape : shapes.values()) {
			shape.setSelected(false);
			model.getShapes().remove(shape);
		}
				
	}
	
	

	@Override
	public void unexecute() {
		for(int key : shapes.keySet()) {
			Shape shape = shapes.get(key);
			if(key <= model.getShapes().size()) {
				model.getShapes().add(key,shape);
				shape.setSelected(true);
			}else {
				model.add(shape);
				shape.setSelected(true);
			}
		} 
	}
			
}
