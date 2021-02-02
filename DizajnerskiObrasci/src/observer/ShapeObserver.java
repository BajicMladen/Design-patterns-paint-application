package observer;

import java.util.Observable;

import geometry.Shape;
import mvc.DrawingModel;
import mvc.FrmDrawing;

public class ShapeObserver implements java.util.Observer { 
	private FrmDrawing frame;
	private DrawingModel model;
	
	 public ShapeObserver(FrmDrawing frame,DrawingModel model) {
		this.frame=frame;
		this.model=model;
	}

	@Override
	public void update(Observable o, Object arg) { 
		int count=getSelectedShapes();
		
		if(count==0) { // Non of shapes in list are selected and modify,delete, position change options are disabled
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
			
			
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			
		}else if(count>1){ // More than one shape is selected, user can delete, but can not change position or modify
			
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			
			
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			
		}else {// one shape is selected and all options are available			
			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
			
			
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
		}

	}
	
	public int getSelectedShapes() { // Method for counting all selected shapes
		int counter=0;
		for(Shape s : model.getShapes()) {
			if(s.isSelected()) {
				counter++;
			}
		}
		return counter;
	}

}
