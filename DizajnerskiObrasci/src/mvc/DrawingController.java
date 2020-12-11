package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import dlg.DlgDrawCircle;
import dlg.DlgDrawDonut;
import dlg.DlgDrawRec;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import modifyDlg.DlgRectMod;

public class DrawingController {
	
	private DrawingModel model;
	 private FrmDrawing frame;
	 
	 private int selectedLast = -1;
	 private Point pointOne;


	 public DrawingController(DrawingModel model,FrmDrawing frame) {
			this.model=model;
			this.frame=frame;
		}

	 //Logika iscrtavanja oblika!
	 public void addShape(MouseEvent e,Color shapeColor, Color innerColor) {
		 Point mouseClick = new Point(e.getX(),e.getY());
		 if(frame.getTgbtnPoint().isSelected()) {
			 Point point = new Point(e.getX(),e.getY(),shapeColor);
			 
			 model.add(point);
		 }
		 else if(frame.tgbtnLine.isSelected()) {
			 if(pointOne == null) {
				pointOne = new Point(e.getX(),e.getY());
			 }else {
				 Point pointTwo = new Point(e.getX(),e.getY());
				 Line line = new Line(pointOne,pointTwo,shapeColor);
				 pointOne=null;
				 model.add(line);
			 }
		 }else if(frame.getTgbtnRectangle().isSelected()) {
			 DlgDrawRec drawRec = new DlgDrawRec();
			 drawRec.setVisible(true);
			 
			if(drawRec.isFlag()) { 	 
			 Rectangle rc=new Rectangle(mouseClick,Integer.parseInt(drawRec.getTextWidth().getText()),
			Integer.parseInt(drawRec.getTextHeight().getText()),shapeColor,innerColor);		 
			 model.add(rc);
			}
			 
		 }else if(frame.tgbtnCircle.isSelected()) {
			 DlgDrawCircle drawCircle=new DlgDrawCircle();
			 drawCircle.setVisible(true);
			 
			 if(drawCircle.isFlag()) {
				 Circle c=new Circle(mouseClick,Integer.parseInt(drawCircle.getTextRadius().getText()),shapeColor,innerColor);
				 model.add(c);
			 }
			 
		 }else if(frame.getTgbtnDonut().isSelected()) {
			 DlgDrawDonut drawDonut = new DlgDrawDonut();
			 drawDonut.setVisible(true);
			 
			 if(drawDonut.isFlag1()) {
				 Donut d = new Donut(mouseClick,Integer.parseInt(drawDonut.getTextRadius().getText()),Integer.parseInt(drawDonut.getTextInnerRadius().getText()),shapeColor,innerColor);
				 model.add(d);
			 }
			 
		 }
		 
		 
		 
	 }
	 
	 
	 public void deleteShape() {
		 if(selectedLast==-1) {
			 JOptionPane.showMessageDialog(null, "No figure is selected!","Error!", JOptionPane.ERROR_MESSAGE, null);
			 return;
		 }
		 if(model.getShapes().size()>selectedLast) {
		 int option = JOptionPane.showConfirmDialog(null,
				 "Are you sure you want to delete selected object/s?", "Delete", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
		 	model.getShapes().remove(selectedLast);
			frame.repaint();
			selectedLast=-1;
			}
		 }
	 }
	 
	 public void deleteAll() {
		 model.getShapes().clear();
		 frame.repaint();
	 }
	 
	 
	 
	 public void selectShape(MouseEvent e) {
		 for(int i=0;i<model.getShapes().size();i++) {
				if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
					selectedLast=i;
				}
			}
			if(selectedLast!=-1) {
				model.getShapes().get(selectedLast).setSelected(true);
			}
	 }
	 
	 
	 
	 
}



















