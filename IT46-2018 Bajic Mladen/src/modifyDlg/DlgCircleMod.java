package modifyDlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgCircleMod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCenterX;
	private JTextField textCenterY;
	private JTextField textColor;
	private JTextField textInnerColor;
	private Circle circle;
	
	private Color color;
	private Color innerColor;
	private JTextField textRadius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircleMod dialog = new DlgCircleMod();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircleMod() {
		setBounds(100, 100, 359, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("Modify--Circle--Modify");
		JLabel lblNewLabel = new JLabel("Center X coordinate-->");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblCenterYCoordinate = new JLabel("Center Y coordinate-->");
		lblCenterYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCenterX = new JTextField();
		textCenterX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCenterX.setColumns(10);
		textCenterY = new JTextField();
		textCenterY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCenterY.setColumns(10);
		JButton btnColor = new JButton("Color->>");
		btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color--Select Circle Color--Color", circle.getColor());
				if(color == null) { 
					color = circle.getColor();
					}
				textColor.setBackground(color);
			}
		});
		JButton btnInnerColor = new JButton("Inner color->>");
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Color--Select Inner Circle Color--Color", circle.getInnerColor());
				if(innerColor == null) { 
					innerColor = circle.getInnerColor();
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
		
		
		JLabel lblRadius = new JLabel("Radius-->");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textRadius = new JTextField();
		textRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRadius.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterYCoordinate, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterYCoordinate)
						.addComponent(textCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
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
							validation(textCenterX.getText(),textCenterY.getText(), textRadius.getText());
						} catch(NumberFormatException exep) {
							JOptionPane.showMessageDialog(null, "Invalid data,please check and insert correct data!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						if(textCenterX.getText().trim().equals("") || textCenterY.getText().trim().equals("") || textRadius.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Fields can not be blanc!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else if(Integer.parseInt(textRadius.getText()) < 1) {
							JOptionPane.showMessageDialog(null, "Radius can't be 0 or less!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
							}
						 
							int x = Integer.parseInt(textCenterX.getText());
							int y = Integer.parseInt(textCenterY.getText());
							int radius = Integer.parseInt(textRadius.getText());
							
							circle.getCenter().setX(x);
							circle.getCenter().setY(y);
							circle.setRadius(radius);
							circle.setColor(color);
							circle.setInnerColor(innerColor);
							dispose();
						
						
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
	public void fillAll(Circle circleModifier) {
		this.circle = circleModifier;
		textCenterX.setText(String.valueOf(circle.getCenter().getX()));
		textCenterY.setText(String.valueOf(circle.getCenter().getY()));
		textRadius.setText(String.valueOf(circle.getRadius()));
		textColor.setBackground(circle.getColor());
		textInnerColor.setBackground(circle.getInnerColor());
		color = circle.getColor();
		innerColor = circle.getInnerColor();
	}
	public void validation(String x, String y, String radius) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if(x.matches("") || y.matches("") || radius.matches("")) {
			
		}
		else if(!x.matches(exp2) || !y.matches(exp2) || !radius.matches(exp2)){
        	throw new NumberFormatException();
        }
	}

}
