package frms;

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
import pnl.PnlDrawing;

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

@SuppressWarnings("serial")
public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	PnlDrawing pnlDrawing = new PnlDrawing();
	private JTextField textBcColor;
	private JTextField textFillColor;
	public static Color shapeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private final ButtonGroup btnG = new ButtonGroup();
	private final ButtonGroup btnG1 = new ButtonGroup();
	private int selectedLast = -1;
	private Point click1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		pnlDrawing.setLayout(null);
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing,BorderLayout.CENTER);
		
		JPanel ShapesBtnPanel = new JPanel();
		ShapesBtnPanel.setBackground(UIManager.getColor("Button.background"));
		ShapesBtnPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Shapes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel modePanel = new JPanel();
		modePanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Mode", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel bColorPanel = new JPanel();
		bColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Background Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bColorPanel.setBackground(SystemColor.menu);
		
		JButton btnSetBcColor = new JButton("Set Color");
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
		
		JButton btnSetFillColor = new JButton("Set Color");
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
		
		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedLast == -1) {
					JOptionPane.showMessageDialog(null, "Nothing is selected!","INFO!", JOptionPane.ERROR_MESSAGE, null);
					return;
				} 
				Shape shapeToModify = pnlDrawing.getShapes().get(selectedLast);
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
				
				pnlDrawing.repaint();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedLast==-1){
					JOptionPane.showMessageDialog(null, "Nothing is selected!","INFO!", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				int mess = JOptionPane.showConfirmDialog(null, "Sure you want to delete?", "CONFIRM", JOptionPane.YES_NO_OPTION);
				if(mess==0 & pnlDrawing.getShapes().size()>selectedLast) {
					pnlDrawing.getShapes().remove(selectedLast);
					pnlDrawing.repaint();
					selectedLast=-1;
				}
			}
		});
		
		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.getShapes().clear();
				pnlDrawing.repaint();
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
					.addComponent(pnlDrawing, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
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
				.addComponent(pnlDrawing, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
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
		
		JRadioButton rdbtnSelectShape = new JRadioButton("Select Shape");
		rdbtnSelectShape.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnSelectShape.setForeground(Color.BLUE);
		rdbtnSelectShape.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					pnlDrawing.setCursor(new Cursor(HAND_CURSOR));
				} else {
					pnlDrawing.setCursor(new Cursor(DEFAULT_CURSOR));
				}
			}
		});
		
		JRadioButton rdbtnDrawShape = new JRadioButton(" Draw Shape");
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
		
		JToggleButton tgbtnPoint = new JToggleButton("Point");
		tgbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnPoint.setForeground(Color.BLACK);
		tgbtnPoint.setBackground(UIManager.getColor("Button.darkShadow"));
		
		JToggleButton tgbtnLine = new JToggleButton("Line");
		tgbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnLine.setBackground(UIManager.getColor("Button.darkShadow"));
		
		JToggleButton tgbtnRectangle = new JToggleButton("Rectangle");
		tgbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnRectangle.setBackground(UIManager.getColor("Button.darkShadow"));
		
		JToggleButton tgbtnCircle = new JToggleButton("Circle");
		tgbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JToggleButton tgbtnDonut = new JToggleButton("Donut");
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
		
		pnlDrawing.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {				
				super.mouseClicked(e);
				Point mousClick=new Point(e.getX(),e.getY());
				for(int i=0; i<pnlDrawing.getShapes().size();i++) {
					selectedLast=-1;
					pnlDrawing.getShapes().get(i).setSelected(false);;
					pnlDrawing.repaint();
				}
				if(rdbtnSelectShape.isSelected()) {
					for(int i=0;i<pnlDrawing.getShapes().size();i++) {
						if(pnlDrawing.getShapes().get(i).contains(e.getX(), e.getY())) {
							selectedLast=i;
						}
					}
					if(selectedLast!=-1) {
						pnlDrawing.getShapes().get(selectedLast).setSelected(true);
					}
				}else {
					if(tgbtnPoint.isSelected()) {
						Point p=new Point(e.getX(),e.getY());
						p.setColor(shapeColor);
						pnlDrawing.getShapes().add(p);
					}else if(tgbtnLine.isSelected()) {
						if(click1==null) {
							click1=new Point(e.getX(),e.getY());
							return;
						}
						Point mousClick2 = new Point(e.getX(),e.getY());
						Line l=new Line(click1,mousClick2,shapeColor);
						pnlDrawing.getShapes().add(l);
						click1=null;
					}else if(tgbtnRectangle.isSelected()) {
						DlgDrawRec drawRec=new DlgDrawRec();
						drawRec.setVisible(true);
						
						if(drawRec.isFlag()) {						
							Rectangle rc=new Rectangle(mousClick,Integer.parseInt(drawRec.getTextWidth().getText()),Integer.parseInt(drawRec.getTextHeight().getText()));
							rc.setColor(shapeColor);
							rc.setInnerColor(fillColor);
							pnlDrawing.getShapes().add(rc);
						}
					}else if(tgbtnCircle.isSelected()) {
						DlgDrawCircle drawCircle= new DlgDrawCircle();
						drawCircle.setVisible(true);
						
						if(drawCircle.isFlag()) {
							Circle c = new Circle(mousClick,Integer.parseInt(drawCircle.getTextRadius().getText()));
							c.setColor(shapeColor);
							c.setInnerColor(fillColor);
							pnlDrawing.getShapes().add(c);
						}
						
					}else if(tgbtnDonut.isSelected()) {
						DlgDrawDonut drawDonut=new DlgDrawDonut();
						drawDonut.setVisible(true);
						
						if(drawDonut.isFlag1()) {
							Donut d = new Donut(mousClick,Integer.parseInt(drawDonut.getTextRadius().getText()),Integer.parseInt(drawDonut.getTextInnerRadius().getText()));
							d.setColor(shapeColor);
							d.setInnerColor(fillColor);
							pnlDrawing.getShapes().add(d);
						}
					}
				}
				pnlDrawing.repaint();
			}
			
			
			
		});
		
	}
}
