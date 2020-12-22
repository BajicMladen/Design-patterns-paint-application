package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.Command;
import command.shapes.AddShapeCmd;
import command.shapes.RemoveShapeCmd;
import dlg.DlgDrawCircle;
import dlg.DlgDrawDonut;
import dlg.DlgDrawHex;
import dlg.DlgDrawRec;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;
import hexagon.Hexagon;
import modifyDlg.DlgCircleMod;
import modifyDlg.DlgDonutMod;
import modifyDlg.DlgHexMod;
import modifyDlg.DlgLineMod;
import modifyDlg.DlgPointMod;
import modifyDlg.DlgRectMod;

public class DrawingController {
	
	private Stack<Command> undoRedoStack = new Stack<>();
	
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
			 
			 addShapesToStack(new AddShapeCmd(model, point));
			
		 }
		 else if(frame.tgbtnLine.isSelected()) {
			 if(pointOne == null) {
				pointOne = new Point(e.getX(),e.getY());
			 }else {
				 Point pointTwo = new Point(e.getX(),e.getY());
				 Line line = new Line(pointOne,pointTwo,shapeColor);
				 pointOne=null;
				 
				addShapesToStack(new AddShapeCmd(model, line));
			 }
		 }else if(frame.getTgbtnRectangle().isSelected()) {
			 DlgDrawRec drawRec = new DlgDrawRec();
			 drawRec.setVisible(true);
			 
			if(drawRec.isFlag()) { 	 
			 Rectangle rc=new Rectangle(mouseClick,Integer.parseInt(drawRec.getTextWidth().getText()),
			Integer.parseInt(drawRec.getTextHeight().getText()),shapeColor,innerColor);		
			 
			 addShapesToStack(new AddShapeCmd(model,rc));
			}
			 
		 }else if(frame.tgbtnCircle.isSelected()) {
			 DlgDrawCircle drawCircle=new DlgDrawCircle();
			 drawCircle.setVisible(true);
			 
			 if(drawCircle.isFlag()) {
				 Circle c=new Circle(mouseClick,Integer.parseInt(drawCircle.getTextRadius().getText()),shapeColor,innerColor);
				 
				 addShapesToStack(new AddShapeCmd(model,c));
			 }
			 
		 }else if(frame.getTgbtnDonut().isSelected()) {
			 DlgDrawDonut drawDonut = new DlgDrawDonut();
			 drawDonut.setVisible(true);
			 
			 if(drawDonut.isFlag1()) {
				 Donut d = new Donut(mouseClick,Integer.parseInt(drawDonut.getTextRadius().getText()),Integer.parseInt(drawDonut.getTextInnerRadius().getText()),shapeColor,innerColor);
				
				 addShapesToStack(new AddShapeCmd(model,d));
			 }
			 
		 }else if(frame.getTgbtnHexagon().isSelected()) {
			 DlgDrawHex drawHex = new DlgDrawHex();
			 drawHex.setVisible(true);
			 
			 if(drawHex.isFlag()) {
				 Hexagon hexagon = new Hexagon(mouseClick.getX(),mouseClick.getY(),Integer.parseInt(drawHex.getTextRadius().getText()));
				 SurfaceShape hexagonAdapter=new HexagonAdapter(hexagon,shapeColor,innerColor);
				
				 addShapesToStack(new AddShapeCmd(model,hexagonAdapter));
			 }
		 }
		 
		 frame.repaint();
		 
		 
		 
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
		 	
		 	Shape s = model.getShapes().get(selectedLast);
		 	
		 	addShapesToStack(new RemoveShapeCmd(model,s));
		 	
			frame.repaint();
			selectedLast=-1;
			}
		 }
	 }
	 
	 public void deleteAll() {
		 model.getShapes().clear();
		 frame.repaint();
	 }
	 
	 public void selectFix() {
			for(int i =0;i<model.getShapes().size();i++) {
				selectedLast = -1;
				model.getShapes().get(i).setSelected(false);
				frame.repaint();
			}
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
	 
	 
	 public void modifyShape() {
		 if(selectedLast== -1) {
			 JOptionPane.showMessageDialog(null, "No figure is selected!","Error!", JOptionPane.ERROR_MESSAGE, null);
				return;
		 }
		 Shape shapeToModify = model.getShapes().get(selectedLast);
		 if(shapeToModify instanceof Point) {
			 DlgPointMod modPoint = new DlgPointMod();
			 modPoint.fillAll((Point)shapeToModify);
			 modPoint.setVisible(true);
		 }else if(shapeToModify instanceof Line) {
			 DlgLineMod modLine = new DlgLineMod();
			 modLine.fillAll((Line)shapeToModify);
			 modLine.setVisible(true);
		 }else if(shapeToModify instanceof Rectangle) {
			 DlgRectMod modRec=new DlgRectMod();
			modRec.fillAll((Rectangle)shapeToModify);
			modRec.setVisible(true);
		 }else if(shapeToModify instanceof Donut) {
			 DlgDonutMod modDon=new DlgDonutMod();
				modDon.fillAll((Donut)shapeToModify);
				modDon.setVisible(true);
		 }else if(shapeToModify instanceof Circle) {
			 DlgCircleMod modCircle=new DlgCircleMod();
				modCircle.fillAll((Circle)shapeToModify);
				modCircle.setVisible(true);
		 } else if(shapeToModify instanceof HexagonAdapter) {
			 DlgHexMod modHex = new DlgHexMod();
			 modHex.fillAll((HexagonAdapter)shapeToModify);
			 modHex.setVisible(true);
		 }
		 frame.repaint();
	 }
	 
	 
	 public void addShapesToStack(Command c) {
		 c.execute();
	 }
	 
	 
	 
	 
}



















