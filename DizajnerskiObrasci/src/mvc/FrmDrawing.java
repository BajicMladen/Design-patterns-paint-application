package mvc;

import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmDrawing extends JFrame{

	private JPanel contentPane;
	private JTextField textBcColor;
	private JTextField textFillColor;
	public  Color shapeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private final ButtonGroup btnG = new ButtonGroup();
	private final ButtonGroup btnG1 = new ButtonGroup();
	
	
	private DrawingController controller;
	private PnlDrawing view = new PnlDrawing(); 
	

	
	
	private JToggleButton tgbtnPoint;
	private JToggleButton tgbtnLine;
	private JToggleButton tgbtnCircle;
	private JToggleButton tgbtnRectangle;
	private JToggleButton tgbtnDonut;
	private JToggleButton tgbtnHexagon;
	
	
	private JButton btnSetBcColor;
	private JButton btnSetFillColor;
	
	private JButton btnModify;
	private JButton btnDeleteAll;
	private JButton btnDelete;
	
	private JToggleButton tglbtnDraw;
	private JToggleButton tglbtnSelect;
	
	private JButton btnUndo;
	private JButton btnRedo;
	
	
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;

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
		bColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Border Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
				.addGroup(gl_bColorPanel.createSequentialGroup()
					.addComponent(btnSetBcColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_bColorPanel.setVerticalGroup(
			gl_bColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bColorPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
				.addGroup(gl_fColorPanel.createSequentialGroup()
					.addComponent(btnSetFillColor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_fColorPanel.setVerticalGroup(
			gl_fColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fColorPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		fColorPanel.setLayout(gl_fColorPanel);
		
		JPanel actonPanel = new JPanel();
		actonPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		actonPanel.setBackground(SystemColor.menu);
		
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnDelete.isEnabled()) {
					
						controller.deleteShape();
				}
			}
		});
		
		btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.setEnabled(false);
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
		
		JPanel undoRedoPanel = new JPanel();
		undoRedoPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Undo/Redo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnUndo.isEnabled()) {
				controller.undo();
				}
				
			}
		});
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnRedo.isEnabled()) {
				controller.redo();
				}
			}
		});
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_undoRedoPanel = new GroupLayout(undoRedoPanel);
		gl_undoRedoPanel.setHorizontalGroup(
			gl_undoRedoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_undoRedoPanel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_undoRedoPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRedo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUndo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_undoRedoPanel.setVerticalGroup(
			gl_undoRedoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_undoRedoPanel.createSequentialGroup()
					.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		undoRedoPanel.setLayout(gl_undoRedoPanel);
		
		JPanel frontBackPanel = new JPanel();
		frontBackPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Position", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frontBackPanel.setBackground(SystemColor.menu);
		
		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnToFront.isEnabled()) {
				controller.toFront();
				
				}
				
			}
		});
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnToBack.isEnabled()) {
				controller.toBack();
				
				}
				
			}
		});
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnBringToFront.isEnabled()) {
				controller.bringToFront();
				
				}
			}
		});
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnBringToBack.isEnabled()) {
				controller.bringToBack();
				
				}
			}
		});
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_frontBackPanel = new GroupLayout(frontBackPanel);
		gl_frontBackPanel.setHorizontalGroup(
			gl_frontBackPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_frontBackPanel.createSequentialGroup()
					.addComponent(btnToFront)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBringToFront)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnBringToBack))
		);
		gl_frontBackPanel.setVerticalGroup(
			gl_frontBackPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_frontBackPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_frontBackPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frontBackPanel.setLayout(gl_frontBackPanel);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ShapesBtnPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(actonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bColorPanel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fColorPanel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(frontBackPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(view, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(modePanel, 0, 0, Short.MAX_VALUE)
						.addComponent(undoRedoPanel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(ShapesBtnPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(actonPanel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addGap(33))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(modePanel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(undoRedoPanel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(bColorPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(fColorPanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addComponent(frontBackPanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(view, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)))))
		);
		
		tglbtnDraw = new JToggleButton("Draw");
		tglbtnDraw.setSelected(true);
		
		tglbtnDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setEnabled(false);;
		tglbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
						tglbtnSelect.setSelected(true);		
			}
		});
		tglbtnSelect.setSelected(true);
		
		
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		GroupLayout gl_modePanel = new GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tglbtnSelect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tglbtnDraw, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_modePanel.setVerticalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addComponent(tglbtnDraw, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		
		tgbtnHexagon = new JToggleButton("Hexagon");
		tgbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
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
						.addComponent(tgbtnPoint, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnHexagon, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
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
					.addGap(18)
					.addComponent(tgbtnHexagon, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ShapesBtnPanel.setLayout(gl_ShapesBtnPanel);
		contentPane.setLayout(gl_contentPane);
		btnG.add(tgbtnPoint);
		btnG.add(tgbtnLine);
		btnG.add(tgbtnRectangle);
		btnG.add(tgbtnCircle);
		btnG.add(tgbtnDonut);
		btnG.add(tgbtnHexagon);
		
		btnG1.add(tglbtnDraw);
		btnG1.add(tglbtnSelect);
		
		tgbtnPoint.setSelected(true);
		
		view.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tglbtnSelect.isSelected()) {
					
					controller.selectShape(e);
				}
				
				if(tglbtnDraw.isSelected()) {
					controller.addShape(e,shapeColor,fillColor);
				}
				
				
				
			}
			
			
			
		});
		
	}
	
	
	

	public JToggleButton getTgbtnHexagon() {
		return tgbtnHexagon;
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




	public JButton getBtnModify() {
		return btnModify;
	}




	public JButton getBtnDeleteAll() {
		return btnDeleteAll;
	}




	public JButton getBtnDelete() {
		return btnDelete;
	}




	public JButton getBtnUndo() {
		return btnUndo;
	}




	public JButton getBtnRedo() {
		return btnRedo;
	}




	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}




	public JButton getBtnToFront() {
		return btnToFront;
	}




	public JButton getBtnToBack() {
		return btnToBack;
	}




	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}




	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}




	
	
	
}
