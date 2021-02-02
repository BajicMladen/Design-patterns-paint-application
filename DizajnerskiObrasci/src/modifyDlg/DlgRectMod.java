package modifyDlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

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
public class DlgRectMod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textRecX;
	private JTextField textRecY;
	private JTextField textWidth;
	private JTextField textHeight;
	private JTextField textColor;
	private JTextField textInnerColor;
	private Color color;
	private Color innerColor;
	private Rectangle rec;
	private boolean flag=false;
	
	private int x,y,heightRec,widthRec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectMod dialog = new DlgRectMod();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectMod() {
		setBounds(100, 100, 530, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setLocationRelativeTo(null);	
		setTitle("Modify--Rectangle--Modify");
		JLabel lblUpperLeftPointX = new JLabel("Upper Left point X coordinate-->");
		lblUpperLeftPointX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblUpperLeftPoint = new JLabel("Upper Left point Y coordinate-->");
		lblUpperLeftPoint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblNewLabel = new JLabel("Width-->");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblNewLabel_1 = new JLabel("Height-->");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textRecX = new JTextField();
		textRecX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRecX.setColumns(10);
		textRecY = new JTextField();
		textRecY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRecY.setColumns(10);
		textWidth = new JTextField();
		textWidth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textWidth.setColumns(10);
		textHeight = new JTextField();
		textHeight.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textHeight.setColumns(10);
		JButton btnColor = new JButton("Border Color->>");
		btnColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color--Select Rectangle Color--Color", rec.getColor());
				if(color == null) { 
					color = rec.getColor();
					}
				textColor.setBackground(color);
			}
		});
		JButton btnInnerColor = new JButton("Fill Color->>");
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Color--Select Inner Rectangle Color--Color", rec.getInnerColor());
				if(innerColor == null) { 
					innerColor = rec.getInnerColor();
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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUpperLeftPointX)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
									.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addComponent(lblUpperLeftPoint))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(btnInnerColor)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textRecX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textRecY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(15))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpperLeftPointX)
						.addComponent(textRecX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpperLeftPoint)
						.addComponent(textRecY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(98)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textInnerColor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))))
					.addContainerGap(12, Short.MAX_VALUE))
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
							validation(textRecX.getText(),textRecY.getText(),textWidth.getText(),textHeight.getText());
						} catch(NumberFormatException ec) {
							JOptionPane.showMessageDialog(null, "Invalid data, please check and correct data!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							flag = false; 
							return;
							}
						if(textRecX.getText().trim().equals("") || textRecY.getText().trim().equals("") || textWidth.getText().trim().equals("") || textHeight.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Fields can not be blanc!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else if(Integer.parseInt(textWidth.getText()) < 0 || Integer.parseInt(textHeight.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Radius can not be 0 or less!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
							return;
							}
							flag=true;
							 x = Integer.parseInt(textRecX.getText());
							 y = Integer.parseInt(textRecY.getText());
							 widthRec = Integer.parseInt(textWidth.getText());
							 heightRec = Integer.parseInt(textHeight.getText());
							
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
	public void fillAll(Rectangle shapeToModify) {
		this.rec= shapeToModify;
		textRecX.setText(String.valueOf(rec.getUpperLeftPoint().getX()));
		textRecY.setText(String.valueOf(rec.getUpperLeftPoint().getY()));
		textWidth.setText(String.valueOf(rec.getWidth()));
		textHeight.setText(String.valueOf(rec.getHeight()));
		textColor.setBackground(rec.getColor());
		textInnerColor.setBackground(rec.getInnerColor());
		color = rec.getColor();
		innerColor = rec.getInnerColor();
	}
	
	public void validation(String x, String y, String width, String height) {
		String supp1 ="^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
		if(x.matches("") || y.matches("") || width.matches("") || height.matches("") ) {
			
		}else if(!x.matches(supp1) || !y.matches(supp1) || !width.matches(supp1) || !height.matches(supp1)){  
        	throw new NumberFormatException();
        }
	}

	public Color getColor() {
		return color;
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

	public int getHeightRec() {
		return heightRec;
	}

	public int getWidthRec() {
		return widthRec;
	}
	
	
	
		

}
