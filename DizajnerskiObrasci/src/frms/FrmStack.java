package frms;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dlg.DlgStack;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmStack extends JFrame {

	private JPanel contentPane;

	private boolean adding = true;
	private static DefaultListModel<String> Stack = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Bajic Mladen, IT46-2018");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAddStack = new JButton("ADD RECTANGLE");
		btnAddStack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlg1 = new DlgStack();
				dlg1.setLocationRelativeTo(null);
				adding = true;
				dlg1.getBtnRemove().setVisible(false);
				dlg1.getBtnInsert().setVisible(true);
				dlg1.setVisible(true);
				if (dlg1.isFlag()) {
					Stack.add(0, dlg1.getRectangle().toStringStack());
				}
			}
		});

		JButton btnRemoveStack = new JButton("REMOVE RECTANGLE");
		btnRemoveStack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemoveStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Stack.isEmpty()) {
					JOptionPane.showMessageDialog(null, "STACK is empty, insert please!", "ERROR",
							JOptionPane.ERROR_MESSAGE, null);
				} else {
					adding = false;
					String[] split = Stack.getElementAt(0).toString().split("[:|,|(|)]");
					DlgStack dlg2 = new DlgStack();
					dlg2.setTitle("Remove from STACK");
					dlg2.setLocationRelativeTo(null);
					dlg2.getTextFieldHeight().setEditable(false);
					dlg2.getTextFieldX().setEditable(false);
					dlg2.getTextFieldY().setEditable(false);
					dlg2.getTextFieldWidth().setEditable(false);
					dlg2.getTextFieldX().setText(split[3]);
					dlg2.getTextFieldY().setText(split[4]);
					dlg2.getTextFieldWidth().setText(split[7]);
					dlg2.getTextFieldHeight().setText(split[9]);
					dlg2.getBtnRemove().setVisible(true);
					dlg2.getBtnInsert().setVisible(false);
					dlg2.setVisible(true);
					if (dlg2.isFlgRemove()) {
						Stack.remove(0);
					}
					adding = true;
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnAddStack, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnRemoveStack, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddStack, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
								.addComponent(btnRemoveStack, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
						.addContainerGap()));

		JList<String> listStack = new JList<String>();
		scrollPane.setViewportView(listStack);
		contentPane.setLayout(gl_contentPane);
		listStack.setModel(Stack);

	}

	public boolean isAdding() {
		return adding;
	}

	public void setAdding(boolean adding) {
		this.adding = adding;
	}

	public static DefaultListModel<String> getStack() {
		return Stack;
	}

	public static void setStack(DefaultListModel<String> stack) {
		Stack = stack;
	}
}
