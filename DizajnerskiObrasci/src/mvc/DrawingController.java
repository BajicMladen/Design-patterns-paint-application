package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;

import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;

import org.omg.CORBA.PUBLIC_MEMBER;

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
import strategy.DrawingSaving;
import strategy.LogSaving;
import strategy.SavingManager;

public class DrawingController implements Serializable{
	
	private Stack<Command> undoRedoStack = new Stack<>();
	private int undoRedoStackPointer=-1;
	
	private DrawingModel model;
	 private FrmDrawing frame;
	 
	 private Point pointOne;
	 
	 private BufferedReader bufferedReader;
	 private String line;


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
		 frame.addToLogList("undo:"+undoRedoStack.get(undoRedoStackPointer).toString());
		 undoRedoStackPointer--;
		 
		 frame.repaint();
		 frame.getBtnRedo().setEnabled(true);
		 if (undoRedoStackPointer==-1) frame.getBtnUndo().setEnabled(false);
	 }
	 
	 public void redo() {
		 undoRedoStackPointer++;
		 undoRedoStack.get(undoRedoStackPointer).execute();
		 frame.addToLogList("redo:"+undoRedoStack.get(undoRedoStackPointer).toString());
		 frame.repaint();
		 frame.getBtnUndo().setEnabled(true);
		 
		 if(undoRedoStackPointer+1>=undoRedoStack.size()) {
			 frame.getBtnRedo().setEnabled(false);
		 }
		 
		 
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
		 
		 frame.addToLogList(c.toString());
		 
		 frame.getBtnUndo().setEnabled(true);
		 frame.getBtnRedo().setEnabled(false);
		 frame.getTglbtnSelect().setEnabled(true);
		 frame.repaint();
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
				
				if(model.getShapes().indexOf(shape)==model.getShapes().size()-1) {
					JOptionPane.showMessageDialog(null, "Element is alrady in front!");
					
					frame.repaint();
					
				}else {
					addToCommandStack(new ToFrontCmd(model, shape));
					
					frame.repaint();
					break;
				}
																													
			}
		}
		
		
	}
	
	
	public void toBack() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {
				
				if(model.getShapes().indexOf(shape)==0) {
					JOptionPane.showMessageDialog(null, "Element is alrady in back!");
					
					frame.repaint();
					
				}else {
					addToCommandStack(new ToBackCmd(model, shape));
					frame.repaint();
					break;
				}
			}
		}
	}
	
	
	
	public void bringToBack() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {
				
				if(model.getShapes().indexOf(shape)==0) {
					JOptionPane.showMessageDialog(null, "Element is alrady in back!");
					
					frame.repaint();
					
				}else {
					addToCommandStack(new BringToBackCmd(model, shape));
					frame.repaint();
					break;
				}
			}
		}
	}
	
	
	
	public void bringToFront() {
		Iterator<Shape> iterator = model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if(shape.isSelected()) {				
				
				if(model.getShapes().indexOf(shape)==model.getShapes().size()-1) {
					JOptionPane.showMessageDialog(null, "Element is alrady in front!");					
					frame.repaint();
					
				}else {
					addToCommandStack(new BringToFrontCmd(model, shape));
					frame.repaint();
					break;
				}
																													
			}
		}
	}
	
	
	
	public void save(int option) {
		if(option == JOptionPane.YES_OPTION) {
			SavingManager logManager = new SavingManager(new LogSaving(frame.getDlm()));
			logManager.save();
		}
		else if(option == JOptionPane.NO_OPTION) {
			SavingManager drawingManager = new SavingManager(new DrawingSaving(model.getShapes()));
			drawingManager.save();
		}
	}
	
	
	 
	
	
	public void open(int selectedValue) {
		if(selectedValue==0)
		{
			JFileChooser jfc=new JFileChooser("C:\\Users\\Korisnik\\Desktop");
			jfc.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
			jfc.setDialogTitle("Open log file");
		        int chosen =jfc.showOpenDialog(null);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	File logFile=new File(jfc.getSelectedFile().getAbsolutePath());
		        	try 
		        	{
		        		
		        		frame.getBtnUndo().setEnabled(false);
		        		undoRedoStack.clear();
						undoRedoStackPointer=-1;
						model.getShapes().clear();
						frame.getDlm().clear();
		        		frame.getBtnLoadNext().setVisible(true);
		        		frame.getBtnLoadNext().setEnabled(true);
		        		frame.getBtnLoadNext().setEnabled(true);
		        		bufferedReader = new BufferedReader(new FileReader(logFile));
		        	} catch (Exception ex) 
		        	{
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!",JOptionPane.ERROR_MESSAGE);
		            }
		
		}
		}
		else if(selectedValue==1)
		{
			JFileChooser jfc=new JFileChooser("C:\\Users\\Korisnik\\Desktop");
			jfc.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
			jfc.setDialogTitle("Open ser file");
		        int chosen =jfc.showOpenDialog(null);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	File serFile=new File(jfc.getSelectedFile().getAbsolutePath());
		        	try 
		        	{
		        		FileInputStream fis = new FileInputStream(serFile);
						ObjectInputStream ois = new ObjectInputStream(fis);
			
						frame.getBtnUndo().setEnabled(false);
						undoRedoStack.clear();
						undoRedoStackPointer=-1;
						model.getShapes().clear();
						frame.getDlm().clear();
						ArrayList<Shape> list = (ArrayList<Shape>) ois.readObject();
						
						for (Shape s : list) {
							model.add(s);
							s.addObserver(new ShapeObserver(frame, model));
							if(s.isSelected()) {
								s.setSelected(true);
							}
						}
						frame.getTglbtnSelect().setEnabled(true);
						ois.close();
						fis.close();
		                JOptionPane.showMessageDialog(null, "Drawing loaded succesifuly", "Succesful!",JOptionPane.INFORMATION_MESSAGE);
		                frame.repaint();
		            } catch (Exception ex) 
		        	{
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!",JOptionPane.ERROR_MESSAGE);
		            }
		
		}
	}
	}
	
	
	public void loadNext() {
		try {
			if((line = bufferedReader.readLine()) != null)  
			{ 
			bufferedReader.mark(1);
			nextLine();
			String[] command = line.split("\\W");
			if(command[1].contentEquals("Point")) {
				String action = command[0];
				Point point = new Point(Integer.parseInt(command[2]),Integer.parseInt(command[3]),new Color(Integer.parseInt(command[5]),Integer.parseInt(command[6]),Integer.parseInt(command[7])));
					switch (action) {
					
					case "added":
						
						addToCommandStack(new AddShapeCmd(model, point));
						point.addObserver(new ShapeObserver(frame, model));
						break;
					
					case "selected":
						
						for(Shape s : model.getShapes()) {
							if(s instanceof Point) {
								if(point.compareTo((Shape)s)==0) {
									addToCommandStack(new SelectShapeCmd(s));
								}
							}
						}
						break;
						
					case "deselected":
						
						for(Shape s : model.getShapes()) {
							if(s instanceof Point) {
								if(point.compareTo((Shape)s)==0) {
									addToCommandStack(new DeselectShapeCmd(s));
								}
							}
						}
						break;
						
					case "Updated":
						Point newPoint=new Point(Integer.parseInt(command[12]),Integer.parseInt(command[13]),new Color(Integer.parseInt(command[15]),Integer.parseInt(command[16]),Integer.parseInt(command[17])));				
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Point) {
								if(point.compareTo((Shape)shape)==0) {
									addToCommandStack(new UpdatePointCmd((Point)shape, newPoint));
								}
							}
						}
						break;	
	
					default:
						break;
					}
					
				}
			else if(command[1].contentEquals("Line")) {
				String action = command[0];
					Line line = new Line(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])),new Point(Integer.parseInt(command[6]),Integer.parseInt(command[7])),new Color(Integer.parseInt(command[9]),Integer.parseInt(command[10]),Integer.parseInt(command[11])));
					switch (action) {
					
					case "added":
						
						addToCommandStack(new AddShapeCmd(model, line));
						line.addObserver(new ShapeObserver(frame, model));
						break;
					
					case "selected":
						
						for(Shape s : model.getShapes()) {
							if(s instanceof Line) {
								if(line.compareTo((Shape)s)==0) {
									addToCommandStack(new SelectShapeCmd(s));
								}
							}
						}
						break;
						
					case "deselected":
						
						for(Shape s : model.getShapes()) {
							if(s instanceof Line) {
								if(line.compareTo((Shape)s)==0) {
									addToCommandStack(new DeselectShapeCmd(s));
								}
							}
						}
						break;
						
						
					case "Updated":
					Line newLine = new Line(new Point(Integer.parseInt(command[17]),Integer.parseInt(command[18])),new Point(Integer.parseInt(command[20]),Integer.parseInt(command[21])),new Color(Integer.parseInt(command[23]),Integer.parseInt(command[24]),Integer.parseInt(command[25])));
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Line) {
								if(line.compareTo((Shape)shape)==0) {
									addToCommandStack(new UpdateLineCmd((Line)shape, newLine));
								}
							}
						}
						break;	
	
					default:
						break;
					}
			}
			else if(command[1].contentEquals("Circle")) {
				
				String action = command[0];
				Circle circle=new Circle(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])), Integer.parseInt(command[7]), new Color(Integer.parseInt(command[9]),Integer.parseInt(command[10]),Integer.parseInt(command[11])), new Color(Integer.parseInt(command[14]),Integer.parseInt(command[15]),Integer.parseInt(command[16])));
				System.out.println(circle.toString());
				switch (action) {
				
				case "added":
					
					addToCommandStack(new AddShapeCmd(model, circle));
					circle.addObserver(new ShapeObserver(frame, model));
					break;
				
				case "selected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Circle) {
							if(circle.compareTo((Shape)s)==0) {
								addToCommandStack(new SelectShapeCmd(s));
							}
						}
					}
					break;
					
				case "deselected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Circle) {
							if(circle.compareTo((Shape)s)==0) {
								addToCommandStack(new DeselectShapeCmd(s));
							}
						}
					}
					break;
					
					
				case "Updated":
					Circle newCircle=new Circle(new Point(Integer.parseInt(command[22]),Integer.parseInt(command[23])), Integer.parseInt(command[26]), new Color(Integer.parseInt(command[28]),Integer.parseInt(command[29]),Integer.parseInt(command[30])), new Color(Integer.parseInt(command[33]),Integer.parseInt(command[34]),Integer.parseInt(command[35])));
					for(Shape shape : model.getShapes()) {
						if(shape instanceof Circle) {
							if(circle.compareTo((Shape)shape)==0) {
								addToCommandStack(new UpdateCircleCmd((Circle)shape, newCircle));
							}
						}
					}
					break;	

				default:
					break;
				}
				
			}else if(command[1].contentEquals("Rectangle")) {
				for (int i=0;i<command.length;i++) {
					System.out.println(i+" "+command[i]);
				}
				String action = command[0];
				Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(command[5]),Integer.parseInt(command[6])), Integer.parseInt(command[10]),Integer.parseInt(command[14]), new Color(Integer.parseInt(command[16]),Integer.parseInt(command[17]),Integer.parseInt(command[18])), new Color(Integer.parseInt(command[21]),Integer.parseInt(command[22]),Integer.parseInt(command[23])));
				System.out.println(rectangle.toString());
				switch (action) {
				
				case "added":
					
					addToCommandStack(new AddShapeCmd(model, rectangle));
					rectangle.addObserver(new ShapeObserver(frame, model));
					break;
				
				case "selected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Rectangle) {
							if(rectangle.compareTo((Shape)s)==0) {
								addToCommandStack(new SelectShapeCmd(s));
							}
						}
					}
					break;
					
				case "deselected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Rectangle) {
							if(rectangle.compareTo((Shape)s)==0) {
								
								addToCommandStack(new DeselectShapeCmd(s));
							}
						}
					}
					break;
					
				case "Updated":
					Rectangle newRectangle=new Rectangle(new Point(Integer.parseInt(command[33]),Integer.parseInt(command[34])), Integer.parseInt(command[38]),Integer.parseInt(command[42]), new Color(Integer.parseInt(command[44]),Integer.parseInt(command[45]),Integer.parseInt(command[46])), new Color(Integer.parseInt(command[49]),Integer.parseInt(command[50]),Integer.parseInt(command[51])));
					for(Shape shape : model.getShapes()) {
						if(shape instanceof Rectangle) {
							if(rectangle.compareTo((Shape)shape)==0) {
								addToCommandStack(new UpdateRectangleCmd((Rectangle)shape, newRectangle));
							}
						}
					}
					break;	

				default:
					break;
				}
				
			}else if(command[1].contentEquals("Donut")) {
				for (int i=0;i<command.length;i++) {
					System.out.println(i+" "+command[i]);
				}
				String action = command[0];
				Donut donut= new Donut(new Point(Integer.parseInt(command[4]),Integer.parseInt(command[5])), Integer.parseInt(command[8]),Integer.parseInt(command[10]), new Color(Integer.parseInt(command[12]),Integer.parseInt(command[13]),Integer.parseInt(command[14])), new Color(Integer.parseInt(command[17]),Integer.parseInt(command[18]),Integer.parseInt(command[19])));
				System.out.println(donut.toString());
				switch (action) {
				
				case "added":
					
					addToCommandStack(new AddShapeCmd(model, donut));
					donut.addObserver(new ShapeObserver(frame, model));
					break;
				
				case "selected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Donut) {
							if(donut.compareTo((Shape)s)==0) {
								addToCommandStack(new SelectShapeCmd(s));
							}
						}
					}
					break;
					
				case "deselected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof Donut) {
							if(donut.compareTo((Shape)s)==0) {
								if(s.isSelected()) {
								addToCommandStack(new DeselectShapeCmd(s));
								}
							}
						}
					}
					break;
					
				case "Updated":
					Donut newDonut=new Donut(new Point(Integer.parseInt(command[28]),Integer.parseInt(command[29])), Integer.parseInt(command[32]),Integer.parseInt(command[34]), new Color(Integer.parseInt(command[36]),Integer.parseInt(command[37]),Integer.parseInt(command[38])), new Color(Integer.parseInt(command[41]),Integer.parseInt(command[42]),Integer.parseInt(command[43])));
					for(Shape shape : model.getShapes()) {
						if(shape instanceof Donut) {
							if(donut.compareTo((Shape)shape)==0) {
								addToCommandStack(new UpdateDonutCmd((Donut)shape, newDonut));
							}
						}
					}
					break;	

				default:
					break;
				}
				
			}else if(command[1].contentEquals("hexagon")) {
				for (int i=0;i<command.length;i++) {
					System.out.println(i+" "+command[i]);
				}
				String action = command[0];
				Hexagon hexagon=new Hexagon(Integer.parseInt(command[4]),Integer.parseInt(command[5]), Integer.parseInt(command[7]));
				HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon, new Color(Integer.parseInt(command[10]),Integer.parseInt(command[11]),Integer.parseInt(command[12])), new Color(Integer.parseInt(command[15]),Integer.parseInt(command[16]),Integer.parseInt(command[17])));
				System.out.println(hexagonAdapter.toString());
				switch (action) {
				
				case "added":
					
					addToCommandStack(new AddShapeCmd(model, hexagonAdapter));
					hexagonAdapter.addObserver(new ShapeObserver(frame, model));
					break;
				
				case "selected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof HexagonAdapter) {
							if(hexagonAdapter.compareTo((Shape)s)==0) {
								addToCommandStack(new SelectShapeCmd(s));
							}
						}
					}
					break;
				case "deselected":
					
					for(Shape s : model.getShapes()) {
						if(s instanceof HexagonAdapter) {
							if(hexagonAdapter.compareTo((Shape)s)==0) {
								if(s.isSelected()) {
								addToCommandStack(new DeselectShapeCmd(s));
								}
							}
						}
					}
					break;
					
				case "Updated":
					Hexagon newHexagon=new Hexagon(Integer.parseInt(command[24]),Integer.parseInt(command[25]), Integer.parseInt(command[27]));
					HexagonAdapter newHexagonAdapter = new HexagonAdapter(newHexagon, new Color(Integer.parseInt(command[30]),Integer.parseInt(command[31]),Integer.parseInt(command[32])), new Color(Integer.parseInt(command[35]),Integer.parseInt(command[36]),Integer.parseInt(command[37])));
					for(Shape shape : model.getShapes()) {
						if(shape instanceof HexagonAdapter) {
							if(hexagonAdapter.compareTo((Shape)shape)==0) {
								addToCommandStack(new UpdateHexagonCmd((HexagonAdapter)shape, newHexagonAdapter));
							}
						}
					}
					break;	

				default:
					break;
				}
				
			}
			
				String action = command[0];
				switch (action) {
				
				case "undo":
					undo();
					break;
					
				case "redo":
					redo();
					break;
					
				case "ToFront":
					toFront();
					break;
					
				case "ToBack":
					toBack();
					break;
				
				case "removed":
					ArrayList<Shape> shapes = new ArrayList<>();
					for(Shape shape : model.getShapes()) {
						if(shape.isSelected()) {
							shapes.add(shape);
						}
					}
					addToCommandStack(new RemoveShapeCmd(model, shapes));
					break;
					
					


				default:
					break;
				}
			
			
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	
private void nextLine() {
		
		try {
			if(bufferedReader.readLine()==null)
			{
				frame.getBtnLoadNext().setEnabled(false);
				bufferedReader.close();
				
			}
			else
			{
				bufferedReader.reset();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	


	
	 
	
}



















