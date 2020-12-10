package dlg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class DlgDrawRec extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean flag;
	private JTextField textWidth;
	private JTextField textHeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDrawRec dialog = new DlgDrawRec();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDrawRec() {
		setBounds(100, 100, 300, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setTitle("Draw--Rectangle--Draw");
		setModal(true);
		
		JLabel lblWidth = new JLabel("Width-->");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblHeight = new JLabel("Height-->");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textWidth = new JTextField();
		textWidth.setForeground(Color.RED);
		textWidth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textWidth.setColumns(10);
		
		textHeight = new JTextField();
		textHeight.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textHeight.setForeground(Color.RED);
		textHeight.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWidth)
						.addComponent(lblHeight))
					.addGap(50)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(118, Short.MAX_VALUE))
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
						boolean number=true;
						try {
							validate(getTextWidth().getText(),getTextHeight().getText());
							
						}catch(NumberFormatException n){
							number=false;
						}
					if(getTextWidth().getText().trim().isEmpty() || getTextHeight().getText().trim().isEmpty()) {
						flag=false;
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "Fields can not be empty, please insert data!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
					}else if(number == false) {
						JOptionPane.showMessageDialog(null, "Invalid data type inserted, please insert again!", "ERROR", JOptionPane.ERROR_MESSAGE, null);
						getToolkit().beep();
						flag=false;
					}else if(Float.parseFloat(getTextWidth().getText()) < 0 ||
							Float.parseFloat(textHeight.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Width and height can not be negative", "ERROR", JOptionPane.ERROR_MESSAGE, null);
						getToolkit().beep();
						flag = false;
						
					} else {
						flag = true;
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
	
	public void validate(String width, String height) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
        if(!width.matches(supp) || !height.matches(supp)){  
        	throw new NumberFormatException();
        }
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JTextField getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(JTextField textWidth) {
		this.textWidth = textWidth;
	}

	public JTextField getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(JTextField textHeight) {
		this.textHeight = textHeight;
	}
}
