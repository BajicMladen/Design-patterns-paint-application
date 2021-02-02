package modifyDlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgDonutMod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textColor;
	private JTextField textInnerColor;
	private JTextField textCenterX;
	private JTextField textCenterY;
	private JTextField textRadius;
	private JTextField textInnerRadius;
	private Color color;
	private Color innerColor;
	private Donut donut;
	
	private boolean flag;
	private int x,y,radius,innerRadius;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonutMod dialog = new DlgDonutMod();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonutMod() {
		setBounds(100, 100, 380, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("Modify--Donut--Modify");
		JLabel lblDonutXCoordinate = new JLabel("Center X coordinate-->");
		lblDonutXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblCenterXcoordinate = new JLabel("Center Y coordinate-->");
		lblCenterXcoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel label_2 = new JLabel("Radius-->");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblInnerRadius = new JLabel("Inner radius-->");
		lblInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JButton btnBorderColor = new JButton("Border Color->>");
		btnBorderColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color--Select Circle Color--Color",  donut.getColor());
				if(color == null) { 
					color = donut.getColor();
					}
				textColor.setBackground(color);
			}
		});
		JButton btnFillColor = new JButton("Fill Color->>");
		btnFillColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Color--Select Inner Donut Color--Color", donut.getInnerColor());
				if(innerColor == null) { 
					innerColor = donut.getInnerColor();
					}
				textInnerColor.setBackground(innerColor);
			}
		});
		textColor = new JTextField();
		textColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textColor.setEditable(false);
		textColor.setColumns(10);
		textInnerColor = new JTextField();
		textInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textInnerColor.setEditable(false);
		textInnerColor.setColumns(10);
		textCenterX = new JTextField();
		textCenterX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCenterX.setColumns(10);
		textCenterY = new JTextField();
		textCenterY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCenterY.setColumns(10);
		textRadius = new JTextField();
		textRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRadius.setColumns(10);
		textInnerRadius = new JTextField();
		textInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textInnerRadius.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInnerRadius, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDonutXCoordinate, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterXcoordinate)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnBorderColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFillColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDonutXCoordinate)
						.addComponent(textCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterXcoordinate)
						.addComponent(textCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerRadius)
						.addComponent(textInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setModal(true);
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModify = new JButton("Modify");
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 try {
								validation(textCenterX.getText(),textCenterY.getText(),textRadius.getText(),textInnerRadius.getText());
							} catch(NumberFormatException exce) {
								JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
							}
						
						 
						 if(textCenterX.getText().trim().equals("") || textCenterY.getText().trim().equals("") || textRadius.getText().trim().equals("") || textInnerRadius.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Fiels can not be empty, please insert data!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						 }else if(Integer.parseInt(textRadius.getText()) < 1 || (Integer.parseInt(textInnerRadius.getText())) < 1) {
								JOptionPane.showMessageDialog(null, "Radius(outer,inner) can not be 0 or lower ", "ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
						 }else if(Integer.parseInt(textRadius.getText()) < (Integer.parseInt(textInnerRadius.getText()))) {
							JOptionPane.showMessageDialog(null, "Inner radius can not be greater than the outer radius!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						 } else {
							 x = Integer.parseInt(textCenterX.getText());
							 y = Integer.parseInt(textCenterY.getText());
							innerRadius = Integer.parseInt(textInnerRadius.getText());
							radius = Integer.parseInt(textRadius.getText());
							flag=true;
						
							dispose();
						}
						
					}
				});
				btnModify.setActionCommand("OK");
				buttonPane.add(btnModify);
				getRootPane().setDefaultButton(btnModify);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	public void fillAll(Donut don) {
		this.donut = don;
		textCenterX.setText(String.valueOf(donut.getCenter().getX()));
		textCenterY.setText(String.valueOf(donut.getCenter().getY()));
		textRadius.setText(String.valueOf(donut.getRadius()));
		textInnerRadius.setText(String.valueOf(donut.getInnerRadius()));
		textColor.setBackground(donut.getColor());
		textInnerColor.setBackground(donut.getInnerColor());
		color = donut.getColor();
		innerColor = donut.getInnerColor();
	}
	public void validation(String x, String y, String radius, String innerRadius) {
		
		String supp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if(x.matches("") || y.matches("") || radius.matches("") || innerRadius.matches("")) {
			
		}
		else if(!x.matches(supp2) || !y.matches(supp2) || !radius.matches(supp2) || !innerRadius.matches(supp2)){
        	throw new NumberFormatException();
        }
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public boolean isFlag() {
		return flag;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public int getInnerRadius() {
		return innerRadius;
	}
	
	

}
