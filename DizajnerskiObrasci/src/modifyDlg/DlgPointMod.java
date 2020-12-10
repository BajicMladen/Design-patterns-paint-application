package modifyDlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgPointMod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textXCor;
	private JTextField textYCor;
	private Color color;
	private JTextField textColor;
	private Point p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPointMod dialog = new DlgPointMod();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPointMod() {
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblXCoordinate = new JLabel("X coordinate -->");
		lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblYCoordinate = new JLabel("Y coordinate -->");
		lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textXCor = new JTextField();
		textXCor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textXCor.setColumns(10);
		textYCor = new JTextField();
		textYCor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textYCor.setColumns(10);
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("Modify--Point--Modify");
		
		JButton btnColor = new JButton("Color->>");
		btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color--Select Point Color--Color", p.getColor());
				if(color == null) { 
					color = p.getColor();
					}
				textColor.setBackground(color);
				
				
			}
			
			
			
		});
		
		
		
		textColor = new JTextField();
		textColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textColor.setColumns(10);
		textColor.setEditable(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblYCoordinate, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
						.addComponent(lblXCoordinate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textYCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textXCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblXCoordinate)
						.addComponent(textXCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(textYCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
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
						if(textXCor.getText().trim().equals("") || textYCor.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Fields can not be empty!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						try {
							int x = Integer.parseInt(textXCor.getText());
							int y = Integer.parseInt(textYCor.getText());
							p.setX(x);
							p.setY(y);
							p.setColor(color);
							dispose();
						} catch(NumberFormatException ece) {
							JOptionPane.showMessageDialog(null, "Incorrect data type inserted, please insert data again!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
						}
					}
				});
				btnModify.setActionCommand("OK");
				buttonPane.add(btnModify);
				getRootPane().setDefaultButton(btnModify);
			}
			{
				JButton btncancel = new JButton("Cancel");
				btncancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btncancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btncancel.setActionCommand("Cancel");
				buttonPane.add(btncancel);
			}
		}
		
	}
	public void fillAll(Point selectedShape) {
		this.p = selectedShape;
		textXCor.setText(String.valueOf(p.getX()));
		textYCor.setText(String.valueOf(p.getY()));
		color = p.getColor();
		textColor.setBackground(color);
		}
	

}
