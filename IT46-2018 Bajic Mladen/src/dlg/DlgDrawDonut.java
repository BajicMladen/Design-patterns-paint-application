package dlg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class DlgDrawDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textInnerRadius;
	private JTextField textRadius;
	private boolean flag1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDrawDonut dialog = new DlgDrawDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDrawDonut() {
		setBounds(100, 100, 300, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("Radius-->");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblNewLabel_1 = new JLabel("Inner radius-->");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textInnerRadius = new JTextField();
		textInnerRadius.setForeground(Color.RED);
		textInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textInnerRadius.setColumns(10);
		textRadius = new JTextField();
		textRadius.setForeground(Color.RED);
		textRadius.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textRadius.setColumns(10);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Draw--Donut--Draw");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDraw = new JButton("Draw");
				btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textInnerRadius.getText().trim().equals("") || textRadius.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "All fields must be filled in!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						try {
							validate(textRadius.getText(),textInnerRadius.getText());
							
						} catch(NumberFormatException exe) {
							JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						}
						if(Integer.parseInt(textInnerRadius.getText()) < 0 || Integer.parseInt(textRadius.getText()) < 0) {
							JOptionPane.showMessageDialog(null, "Both fields must contain numbers greater than 0!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else if(Integer.parseInt(textInnerRadius.getText()) >= Integer.parseInt(textRadius.getText())) {
							JOptionPane.showMessageDialog(null, "Inner radius can't be greater than or equal to the outer radius!", "Error", JOptionPane.ERROR_MESSAGE, null);
							return;
						} else {
							flag1 = true;
							dispose();
						}
					}
				});
				btnDraw.setActionCommand("OK");
				buttonPane.add(btnDraw);
				getRootPane().setDefaultButton(btnDraw);
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
	public void validate(String radius, String innerRadius) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if(!radius.matches(supp)|| !innerRadius.matches(supp)){  
        	throw new NumberFormatException();
        }
	}

	public JTextField getTextInnerRadius() {
		return textInnerRadius;
	}

	public void setTextInnerRadius(JTextField textInnerRadius) {
		this.textInnerRadius = textInnerRadius;
	}

	public JTextField getTextRadius() {
		return textRadius;
	}

	public void setTextradius(JTextField textradius) {
		this.textRadius = textradius;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

}
