package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import geometry.Shape;
import geometry.Circle;
import geometry.Donut;
import dlg.DlgDrawCircle;
import dlg.DlgDrawDonut;
import dlg.DlgDrawRec;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import modifyDlg.DlgCircleMod;
import modifyDlg.DlgDonutMod;
import modifyDlg.DlgLineMod;
import modifyDlg.DlgPointMod;
import modifyDlg.DlgRectMod;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

@SuppressWarnings("serial")
public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private JTextField textBcColor;
	private JTextField textFillColor;
	public  Color shapeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private final ButtonGroup btnG = new ButtonGroup();
	private final ButtonGroup btnG1 = new ButtonGroup();
	private int selectedLast = -1;
	
	
	private DrawingController controller;
	private PnlDrawing view = new PnlDrawing(); 
	
	
	JToggleButton tgbtnPoint;
	JToggleButton tgbtnLine;
	JToggleButton tgbtnCircle;
	JToggleButton tgbtnRectangle;
	JToggleButton tgbtnDonut;
	
	
	JRadioButton rdbtnDrawShape;
	JRadioButton rdbtnSelectShape;
	
	JButton btnSetBcColor;
	JButton btnSetFillColor;
	
	JButton btnModify;
	JButton btnDeleteAll;
	JButton btnDelete;
	
	

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Bajic Mladen, IT46-2018");
		
		view.setLayout(null);
		view.setBackground(Color.WHITE);
		contentPane.add(view,BorderLayout.CENTER);
		
		JPanel ShapesBtnPanel = new JPanel();
		ShapesBtnPanel.setBackground(UIManager.getColor("Button.background"));
		ShapesBtnPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Shapes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel modePanel = new JPanel();
		modePanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Mode", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel bColorPanel = new JPanel();
		bColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Background Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bColorPanel.setBackground(SystemColor.menu);
		
		btnSetBcColor = new JButton("Set Color");
		btnSetBcColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSetBcColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColor=JColorChooser.showDialog(null, "Color--BACKGROUND--Color", shapeColor);
				if(shapeColor==null) {
					shapeColor=Color.BLACK;
					textBcColor.setBackground(Color.BLACK);
				}else {
					textBcColor.setBackground(shapeColor);
				}
				
				
			}
		});
		
		textBcColor = new JTextField();
		textBcColor.setColumns(10);
		textBcColor.setEditable(false);
		textBcColor.setBackground(shapeColor);
		GroupLayout gl_bColorPanel = new GroupLayout(bColorPanel);
		gl_bColorPanel.setHorizontalGroup(
			gl_bColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bColorPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetBcColor, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_bColorPanel.setVerticalGroup(
			gl_bColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bColorPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSetBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		bColorPanel.setLayout(gl_bColorPanel);
		
		JPanel fColorPanel = new JPanel();
		fColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Fill Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fColorPanel.setBackground(SystemColor.menu);
		
		textFillColor = new JTextField();
		textFillColor.setColumns(10);
		textFillColor.setEditable(false);
		textFillColor.setBackground(fillColor);
		
		btnSetFillColor = new JButton("Set Color");
		btnSetFillColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSetFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillColor=JColorChooser.showDialog(null, "Color--FILL--Color", fillColor);
				if(fillColor==null) {
					fillColor=Color.BLACK;
					textFillColor.setBackground(Color.WHITE);
				}else {
					textFillColor.setBackground(fillColor);
				}
				
				
			}
		});
		
		
		GroupLayout gl_fColorPanel = new GroupLayout(fColorPanel);
		gl_fColorPanel.setHorizontalGroup(
			gl_fColorPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 126, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_fColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fColorPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetFillColor, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_fColorPanel.setVerticalGroup(
			gl_fColorPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 141, Short.MAX_VALUE)
				.addGroup(gl_fColorPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSetFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		fColorPanel.setLayout(gl_fColorPanel);
		
		JPanel actonPanel = new JPanel();
		actonPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		actonPanel.setBackground(SystemColor.menu);
		
		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedLast == -1) {
					JOptionPane.showMessageDialog(null, "Nothing is selected!","INFO!", JOptionPane.ERROR_MESSAGE, null);
					return;
				} 
				Shape shapeToModify = view.getModel().get(selectedLast);
				if(shapeToModify instanceof Point) {
					DlgPointMod modPoint = new DlgPointMod();
					modPoint.fillAll((Point)shapeToModify);
					modPoint.setVisible(true);
				
				}else if(shapeToModify instanceof Line) {
					DlgLineMod modLine=new DlgLineMod();
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
					
				}
				
				view.repaint();
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnDelete.isEnabled()) {
					
						controller.deleteShape();
				}
			}
		});
		
		btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteAll();
			}
		});
		
		GroupLayout gl_actonPanel = new GroupLayout(actonPanel);
		gl_actonPanel.setHorizontalGroup(
			gl_actonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actonPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_actonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_actonPanel.setVerticalGroup(
			gl_actonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actonPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		actonPanel.setLayout(gl_actonPanel);
		
		JLabel lblDesignedByMladen = new JLabel("Designed by Mladen");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ShapesBtnPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(actonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(view, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(modePanel, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(bColorPanel, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
									.addComponent(fColorPanel, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDesignedByMladen))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(ShapesBtnPanel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(actonPanel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
				.addComponent(view, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(bColorPanel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(fColorPanel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(modePanel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(lblDesignedByMladen))
		);
		
		rdbtnSelectShape = new JRadioButton("Select Shape");
		rdbtnSelectShape.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnSelectShape.setForeground(Color.BLUE);
		rdbtnSelectShape.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					view.setCursor(new Cursor(HAND_CURSOR));
				} else {
					view.setCursor(new Cursor(DEFAULT_CURSOR));
				}
			}
		});
		
		rdbtnDrawShape = new JRadioButton(" Draw Shape");
		rdbtnDrawShape.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnDrawShape.setForeground(Color.RED);
		GroupLayout gl_modePanel = new GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnSelectShape)
						.addComponent(rdbtnDrawShape))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		btnG1.add(rdbtnDrawShape);
		btnG1.add(rdbtnSelectShape);
		gl_modePanel.setVerticalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnSelectShape, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnDrawShape, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addContainerGap())
		);
		modePanel.setLayout(gl_modePanel);
		
		tgbtnPoint = new JToggleButton("Point");
		tgbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnPoint.setForeground(Color.BLACK);
		tgbtnPoint.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnLine = new JToggleButton("Line");
		tgbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnLine.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnRectangle = new JToggleButton("Rectangle");
		tgbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnRectangle.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnCircle = new JToggleButton("Circle");
		tgbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tgbtnDonut = new JToggleButton("Donut");
		tgbtnDonut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		GroupLayout gl_ShapesBtnPanel = new GroupLayout(ShapesBtnPanel);
		gl_ShapesBtnPanel.setHorizontalGroup(
			gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ShapesBtnPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tgbtnDonut, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnCircle, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnRectangle, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnLine, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnPoint, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_ShapesBtnPanel.setVerticalGroup(
			gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ShapesBtnPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(tgbtnPoint, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnLine, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnRectangle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnCircle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnDonut, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		ShapesBtnPanel.setLayout(gl_ShapesBtnPanel);
		contentPane.setLayout(gl_contentPane);
		btnG.add(tgbtnPoint);
		btnG.add(tgbtnLine);
		btnG.add(tgbtnRectangle);
		btnG.add(tgbtnCircle);
		btnG.add(tgbtnDonut);
		
		tgbtnPoint.setSelected(true);
		rdbtnDrawShape.setSelected(true);
		
		view.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnSelectShape.isSelected()) {
					controller.selectShape(e);
				}
				
				if(rdbtnDrawShape.isSelected()) {
					controller.addShape(e,shapeColor,fillColor);
				}
				
				
				
			}
			
			
			
		});
		
	}
	
	
	

	public JToggleButton getTgbtnPoint() {
		return tgbtnPoint;
	}

	public JToggleButton getTgbtnLine() {
		return tgbtnLine;
	}

	public JToggleButton getTgbtnCircle() {
		return tgbtnCircle;
	}

	public JToggleButton getTgbtnRectangle() {
		return tgbtnRectangle;
	}

	public JToggleButton getTgbtnDonut() {
		return tgbtnDonut;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public PnlDrawing getView() {
		return view;
	}

	public void setView(PnlDrawing view) {
		this.view = view;
	}



	public Color getShapeColor() {
		return shapeColor;
	}




	public void setShapeColor(Color shapeColor) {
		this.shapeColor = shapeColor;
	}




	public Color getFillColor() {
		return fillColor;
	}




	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	
}
