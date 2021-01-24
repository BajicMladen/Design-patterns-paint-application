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
		
		if(count==0) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);
			frame.getBtnDeleteAll().setEnabled(false);
			
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			
		}else if(count>1){
			
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnDeleteAll().setEnabled(true);
			
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
		}else {
			
			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnDeleteAll().setEnabled(true);
			
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
		}

	}
	
	public int getSelectedShapes() {
		int counter=0;
		for(Shape s : model.getShapes()) {
			if(s.isSelected()) {
				counter++;
			}
		}
		return counter;
	}

}
