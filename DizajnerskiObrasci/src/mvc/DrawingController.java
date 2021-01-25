package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.Stack;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.Command;
import command.positions.BringToBackCmd;
import command.positions.BringToFrontCmd;
import command.positions.ToBackCmd;
import command.positions.ToFrontCmd;
import command.select.DeselectShapeCmd;
import command.select.SelectShapeCmd;
import command.shapes.AddShapeCmd;
import command.shapes.RemoveShapeCmd;
import command.shapes.UpdateCircleCmd;
import command.shapes.UpdateDonutCmd;
import command.shapes.UpdateHexagonCmd;
import command.shapes.UpdateLineCmd;
import command.shapes.UpdatePointCmd;
import command.shapes.UpdateRectangleCmd;
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
import observer.ShapeObserver;

public class DrawingController {
	
	private Stack<Command> undoRedoStack = new Stack<>();
	private int undoRedoStackPointer=-1;
	
	private DrawingModel model;
	 private FrmDrawing frame;
	 
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
			 
			 addToCommandStack(new AddShapeCmd(model, point));
			 
			 point.addObserver(new ShapeObserver(frame,model));
			
			 
			
		 }
		 else if(frame.getTgbtnLine().isSelected()) {
			 if(pointOne == null) {
				pointOne = new Point(e.getX(),e.getY());
			 }else {
				 Point pointTwo = new Point(e.getX(),e.getY());
				 Line line = new Line(pointOne,pointTwo,shapeColor);
				 pointOne=null;
				 
				addToCommandStack(new AddShapeCmd(model, line));
				
				line.addObserver(new ShapeObserver(frame,model));
				
			 }
		 }else if(frame.getTgbtnRectangle().isSelected()) {
			 DlgDrawRec drawRec = new DlgDrawRec();
			 drawRec.setVisible(true);
			 
			if(drawRec.isFlag()) { 	 
			 Rectangle rc=new Rectangle(mouseClick,Integer.parseInt(drawRec.getTextWidth().getText()),
			Integer.parseInt(drawRec.getTextHeight().getText()),shapeColor,innerColor);		
			 
			 addToCommandStack(new AddShapeCmd(model,rc));
			 
			rc.addObserver(new ShapeObserver(frame,model));
			 
			
			}
			 
		 }else if(frame.getTgbtnCircle().isSelected()) {
			 DlgDrawCircle drawCircle=new DlgDrawCircle();
			 drawCircle.setVisible(true);
			 
			 if(drawCircle.isFlag()) {
				 Circle c=new Circle(mouseClick,Integer.parseInt(drawCircle.getTextRadius().getText()),shapeColor,innerColor);
				 
				 addToCommandStack(new AddShapeCmd(model,c));
				 
				 c.addObserver(new ShapeObserver(frame,model));
				 
				
			 }
			 
		 }else if(frame.getTgbtnDonut().isSelected()) {
			 DlgDrawDonut drawDonut = new DlgDrawDonut();
			 drawDonut.setVisible(true);
			 
			 if(drawDonut.isFlag1()) {
				 Donut d = new Donut(mouseClick,Integer.parseInt(drawDonut.getTextRadius().getText()),Integer.parseInt(drawDonut.getTextInnerRadius().getText()),shapeColor,innerColor);
				
				 addToCommandStack(new AddShapeCmd(model,d));
				 
				 d.addObserver(new ShapeObserver(frame,model));
				 
			 }
			 
		 }else if(frame.getTgbtnHexagon().isSelected()) {
			 DlgDrawHex drawHex = new DlgDrawHex();
			 drawHex.setVisible(true);
			 
			 if(drawHex.isFlag()) {
				 Hexagon hexagon = new Hexagon(mouseClick.getX(),mouseClick.getY(),Integer.parseInt(drawHex.getTextRadius().getText()));
				 SurfaceShape hexagonAdapter=new HexagonAdapter(hexagon,shapeColor,innerColor);
				
				 addToCommandStack(new AddShapeCmd(model,hexagonAdapter));
				 
				 hexagonAdapter.addObserver(new ShapeObserver(frame,model));
				 
				 
			 }
		 }
		 
		
		 frame.repaint();
		 
		 
	 }
	 

	 
	 
	 public void deleteShape() {
		ArrayList<Shape> shapes = new ArrayList<>();
		
		if(frame.getBtnDelete().isEnabled()) {
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to remove selected shape?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {
		
				for(Shape shape :model.getShapes()) {
					if(shape.isSelected()) {
						shapes.add(shape);
					}
				}
				
				
					
					addToCommandStack(new RemoveShapeCmd(model, shapes));
				
											
				frame.repaint();
			}
		}
		 
	 }
	 
	 public void undo() {
		 
		 undoRedoStack.get(undoRedoStackPointer).unexecute();
		 undoRedoStackPointer--;
		 frame.repaint();
		 frame.getBtnRedo().setEnabled(true);
		 if (undoRedoStackPointer==-1) frame.getBtnUndo().setEnabled(false);
	 }
	 
	 public void redo() {
		 undoRedoStackPointer++;
		 undoRedoStack.get(undoRedoStackPointer).execute();
		 frame.repaint();
		 frame.getBtnUndo().setEnabled(true);
		 
		 if(undoRedoStackPointer+1>=undoRedoStack.size()) {
			 frame.getBtnRedo().setEnabled(false);
		 }
		 
		 
	 }
	 
	
	 
	 
	 
	 public void deleteAll() {
		 model.getShapes().clear();
		 frame.repaint();
	 }
	 
	
	 
	 
	 
	 public void selectShape(MouseEvent e) {
		
		 for(int i=model.getShapes().size()-1;i>=0;i--) {
			 
			 if(model.get(i).contains(e.getX(), e.getY())) {
				 
				if(model.get(i).isSelected() == false) {
				
					 addToCommandStack(new SelectShapeCmd(model.get(i)));
					 frame.repaint();
					 break;
				}else{
					addToCommandStack(new DeselectShapeCmd(model.get(i)));
					frame.repaint();
					break;
				}
			 }
		 }
		
	 }
	 
	 
		
		
		
	 
	 public void modifyShape() {
		 for(Shape shapeToModify: model.getShapes()) {
		 
		if(shapeToModify.isSelected())
		{
		 
		 if(shapeToModify instanceof Point) {
			 DlgPointMod modPoint = new DlgPointMod();
			 modPoint.fillAll((Point)shapeToModify);
			 modPoint.setVisible(true);
			 if(modPoint.isFlag()==true) {
				 Point point = new Point(modPoint.getX(),modPoint.getY(),modPoint.getColor());
				 addToCommandStack(new UpdatePointCmd((Point)shapeToModify,point));
			 }
		 }else if(shapeToModify instanceof Line) {
			 DlgLineMod modLine = new DlgLineMod();
			 modLine.fillAll((Line)shapeToModify);
			 modLine.setVisible(true);
			 if(modLine.isFlag()==true) {
			 Line line =new Line(new Point(modLine.getStartX(),modLine.getStartY()),new Point(modLine.getEndX(),modLine.getEndY()),modLine.getColor());
			 addToCommandStack(new UpdateLineCmd((Line)shapeToModify, line));
			 }
			 
		 }else if(shapeToModify instanceof Rectangle) {
			 DlgRectMod modRec=new DlgRectMod();
			modRec.fillAll((Rectangle)shapeToModify);
			modRec.setVisible(true);
			if(modRec.isFlag()==true) {
				Rectangle rectangle = new Rectangle(new Point(modRec.getX(),modRec.getY()),modRec.getHeightRec(),modRec.getWidthRec(),modRec.getColor(),modRec.getInnerColor());
				addToCommandStack(new UpdateRectangleCmd((Rectangle)shapeToModify, rectangle));
			}
			
			
		 }else if(shapeToModify instanceof Donut) {
			 DlgDonutMod modDon=new DlgDonutMod();
				modDon.fillAll((Donut)shapeToModify);
				modDon.setVisible(true);
				if(modDon.isFlag()==true) {
					Donut donut = new Donut(new Point(modDon.getX(),modDon.getY()),modDon.getRadius(),modDon.getInnerRadius(),modDon.getColor(),modDon.getInnerColor());
					addToCommandStack(new UpdateDonutCmd((Donut)shapeToModify,donut));
				}
				
				
		 }else if(shapeToModify instanceof Circle) {
			 DlgCircleMod modCircle=new DlgCircleMod();
				modCircle.fillAll((Circle)shapeToModify);
				modCircle.setVisible(true);
				if(modCircle.isFlag()==true) {
					Circle circle = new Circle(new Point(modCircle.getX(),modCircle.getY()),modCircle.getRadius(),modCircle.getColor(),modCircle.getInnerColor());
					addToCommandStack(new UpdateCircleCmd((Circle)shapeToModify,circle));
				 }
				
				
		 } else if(shapeToModify instanceof HexagonAdapter) {
			 DlgHexMod modHex = new DlgHexMod();
			 modHex.fillAll((HexagonAdapter)shapeToModify);
			 modHex.setVisible(true);
			 if(modHex.isFlag()==true) {
				 Hexagon hexagon = new Hexagon(modHex.getX(), modHex.getY(), modHex.getRadius());
				 HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon,modHex.getColor(),modHex.getInnerColor());
				 addToCommandStack(new UpdateHexagonCmd((HexagonAdapter)shapeToModify, hexagonAdapter));
			 }
		 }
		 frame.repaint();
			}
		 }
	 }
	 
	 
	 
	 
	 public void addToCommandStack(Command c) {
		 clearCommandStack(undoRedoStackPointer);
		 undoRedoStack.push(c);
		 c.execute();
		 undoRedoStackPointer++;
		 
		 frame.getBtnUndo().setEnabled(true);
		 frame.getBtnRedo().setEnabled(false);
		 frame.getTglbtnSelect().setEnabled(true);
	 }
	 
	 
	private void clearCommandStack(int undoRedoStackPointer)
		{
		    if(undoRedoStack.size()<1)return;
		    for(int i = undoRedoStack.size()-1; i > undoRedoStackPointer; i--)
		    {
		        undoRedoStack.remove(i);
		    }
		}
		
		
	
	public void toFront() {
		Iterator<Shape> iterator= model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			if(shape.isSelected()) {
				shape.setSelected(false);
				try {
					addToCommandStack(new ToFrontCmd(model, shape));
					frame.repaint();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Element is alrady in front!");
					shape.setSelected(false);
					frame.repaint();
				}
			}
		}
		
		
	}
	
	
	public void toBack() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {
				shape.setSelected(false);
				
				try {
					addToCommandStack(new ToBackCmd(model, shape));
					frame.repaint();
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Element is already in back");
					shape.setSelected(false);
					frame.repaint();
				}
			}
		}
	}
	
	
	
	public void bringToBack() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {
				shape.setSelected(false);
				try {
					addToCommandStack(new BringToBackCmd(model, shape));
					frame.repaint();
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Element is already on the back");
					shape.setSelected(false);
				}
				break;
			}
		}
	}
	
	
	
	public void bringToFront() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {
				shape.setSelected(false);
				
				try {
					addToCommandStack(new BringToFrontCmd(model, shape));
					frame.repaint();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Element is already on top");
					shape.setSelected(false);
				}
				break;
			}
		}
	}
	
	
	
	
	 
	
	
	 
	
}



















