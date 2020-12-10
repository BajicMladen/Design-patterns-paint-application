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
import geometry.Line;

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
public class DlgLineMod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textStartPointX;
	private JTextField textStartPointY;
	private JTextField textEndPointX;
	private JTextField textEndPointY;
	private JTextField textColor;
	private Color color;
	private Line l;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLineMod dialog = new DlgLineMod();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLineMod() {
		setBounds(100, 100, 390, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("Modify--Line--Modify");
		
		JLabel lblStartPointX = new JLabel("Start point X coordinate-->");
		lblStartPointX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblStartPointY = new JLabel("Start point Y coordinate-->");
		lblStartPointY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblEndPointX = new JLabel("End point X coordinate-->");
		lblEndPointX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblEndPointY = new JLabel("End point Y coordinate-->");
		lblEndPointY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textStartPointX = new JTextField();
		textStartPointX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textStartPointX.setColumns(10);
		
		textStartPointY = new JTextField();
		textStartPointY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textStartPointY.setColumns(10);
		
		textEndPointX = new JTextField();
		textEndPointX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textEndPointX.setColumns(10);
		
		textEndPointY = new JTextField();
		textEndPointY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textEndPointY.setColumns(10);
		
		JButton btnColor = new JButton("Color->>");
		btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color--Slect Line color--Color", l.getColor());
				if(color == null) { 
					color = l.getColor();
					}
				textColor.setBackground(color);
			}
		});
		
		textColor = new JTextField();
		textColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textColor.setEditable(false);
		textColor.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblStartPointX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEndPointX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEndPointY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartPointY))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointX)
						.addComponent(textStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointY)
						.addComponent(textStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointX)
						.addComponent(textEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointY)
						.addComponent(textEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
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
						if(textStartPointX.getText().trim().equals("") || textStartPointY.getText().trim().equals("") || textEndPointX.getText().trim().equals("") || textEndPointY.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Fields can not be empty,please insert data!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						try {
							int startX = Integer.parseInt(textStartPointX.getText());
							int startY = Integer.parseInt(textStartPointY.getText());
							int endX = Integer.parseInt(textEndPointX.getText());
							int endY = Integer.parseInt(textEndPointY.getText());
							l.setStartPoint(new Point(startX,startY));
							l.setEndPoint(new Point(endX,endY));
							l.setColor(color);
							dispose();
						} catch(NumberFormatException ec) {
							JOptionPane.showMessageDialog(null, "Incorrect data type inserted,please insert again!", "Error", JOptionPane.ERROR_MESSAGE, null);
						}
					}
				});
				btnModify.setActionCommand("OK");
				buttonPane.add(btnModify);
				getRootPane().setDefaultButton(btnModify);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void fillAll(Line shapeToModify) {
		this.l = shapeToModify;
		textStartPointX.setText(String.valueOf(l.getStartPoint().getX()));
		textStartPointY.setText(String.valueOf(l.getStartPoint().getY()));
		textEndPointX.setText(String.valueOf(l.getEndPoint().getX()));
		textEndPointY.setText(String.valueOf(l.getEndPoint().getY()));
		color = l.getColor();
		textColor.setBackground(l.getColor());
	}
}
