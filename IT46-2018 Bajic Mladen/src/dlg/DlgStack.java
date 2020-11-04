package dlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import frms.FrmStack;
import geometry.Rectangle;
import geometry.Point;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgStack extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textX;
	private JTextField textY;
	private JTextField textWidth;
	private JTextField textHeight;
	private boolean flgRemove;
	private Rectangle rectangle;
	private Point point;
	private boolean flag;

	JButton btnRemove = new JButton("Remove");
	JButton btnInsert = new JButton("Insert");
	JButton cancelButton = new JButton("Cancel");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgStack dialog = new DlgStack();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			dialog.textX.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgStack() {
		setBounds(100, 100, 331, 340);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("ADD--RECTANGLE--ADD");
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblXCoordinate = new JLabel("X COORDINATE-->");
		lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblYCoordinate = new JLabel("Y COORDINATE-->");
		lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblWidth = new JLabel("WIDTH-->");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblHeight = new JLabel("HEIGHT-->");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textX = new JTextField();
		textX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textX.setForeground(Color.RED);
		textX.setColumns(10);
		textX.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					textY.requestFocus();
				} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
					textHeight.requestFocus();
				}
			}
		});

		textY = new JTextField();
		textY.setForeground(Color.RED);
		textY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textY.setColumns(10);
		textY.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					textWidth.requestFocus();
				} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
					textX.requestFocus();
				}
			}

		});

		textWidth = new JTextField();
		textWidth.setForeground(Color.RED);
		textWidth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textWidth.setColumns(10);
		textWidth.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					textHeight.requestFocus();
				} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
					textY.requestFocus();
				}
			}

		});

		textHeight = new JTextField();
		textHeight.setForeground(Color.RED);
		textHeight.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textHeight.setColumns(10);
		textHeight.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
					textX.requestFocus();
				} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
					textWidth.requestFocus();
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblXCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblYCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
						.addComponent(lblWidth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblHeight, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
						.addGap(28)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(61, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(32)
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblXCoordinate).addComponent(textX,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblYCoordinate).addComponent(textY,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
				.addGap(36)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblHeight).addComponent(
						textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(82, Short.MAX_VALUE)));

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));

			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					flgRemove = true;
					dispose();
				}
			});
			buttonPane.add(btnRemove);

			{
				FrmStack frmadd = new FrmStack();
				if (frmadd.isAdding()) {
					btnInsert.addKeyListener(new KeyAdapter() {

						@Override
						public void keyPressed(KeyEvent ke) {
							if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
								btnInsert.getAction();
							}
						}

					});

					btnInsert.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							

							try {
								validation(getTextX(), getTextY(), getTextWidth(), getTextHeight());

							} catch (NumberFormatException ex) {

								
								JOptionPane.showMessageDialog(null, "Invalid data type inserted, please insert again!",
										"ERROR", JOptionPane.ERROR_MESSAGE, null);
								getToolkit().beep();
								flag = false;
								return;
							
							} 
							if (textX.getText().trim().isEmpty() || textY.getText().trim().isEmpty()
									|| textWidth.getText().trim().isEmpty() || textHeight.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Fields can not be empty! Please insert data!",
										"ERROR", JOptionPane.ERROR_MESSAGE, null);
								getToolkit().beep();
								flag = false;
								
							}else if (Integer.parseInt(getTextWidth()) < 0 || Integer.parseInt(getTextHeight()) < 0) {
								JOptionPane.showMessageDialog(null,"Height and width can not be negative! Please insert data again!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								getToolkit().beep();
								flag = false;
							
							}
							else {

								setPoint(new Point(Integer.parseInt(getTextX()), Integer.parseInt(getTextY())));

								setRectangle(new Rectangle(getPoint(), Integer.parseInt(getTextWidth()),
										Integer.parseInt(getTextHeight())));
								flag = true;
								dispose();
							}

						}

					});
					buttonPane.add(btnInsert);
					getRootPane().setDefaultButton(btnInsert);
				} else {
					btnRemove.getAction();
				}
			}

			{
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						dispose();
					}

				});
				buttonPane.add(cancelButton);

			}

		}
	}

	public void validation(String x, String y, String width, String height) {
		String supp = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
		if(x.matches("") || y.matches("") || width.matches("")  || height.matches("")) {
			//prazan if ali radi
			
		}else if(!x.matches(supp) || !y.matches(supp) || !width.matches(supp) || !height.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public String getTextX() {
		return textX.getText();
	}

	public JTextField getTextFieldX() {
		return textX;
	}

	public void setTextX(JTextField textX) {
		this.textX = textX;
	}

	public String getTextY() {
		return textY.getText();
	}

	public JTextField getTextFieldY() {
		return textY;
	}

	public void setTextY(JTextField textY) {
		this.textY = textY;
	}

	public String getTextWidth() {
		return textWidth.getText();
	}

	public JTextField getTextFieldWidth() {
		return textWidth;
	}

	public void setTextWidth(JTextField textWidth) {
		this.textWidth = textWidth;
	}

	public String getTextHeight() {
		return textHeight.getText();
	}

	public JTextField getTextFieldHeight() {
		return textHeight;
	}

	public void setTextHeight(JTextField textHeight) {
		this.textHeight = textHeight;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public JButton getBtnInsert() {
		return btnInsert;
	}

	public void setBtnInsert(JButton btnInsert) {
		this.btnInsert = btnInsert;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public boolean isFlgRemove() {
		return flgRemove;
	}

	public void setFlgRemove(boolean flgRemove) {
		this.flgRemove = flgRemove;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
