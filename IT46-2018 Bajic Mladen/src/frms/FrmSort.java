package frms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dlg.DlgStack;
import geometry.Rectangle;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmSort extends JFrame {

	private JPanel contentPane;
	private static DefaultListModel<String> Sort = new DefaultListModel<String>();
	private ArrayList<Rectangle> aList = new ArrayList<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Comparator<Rectangle> compare=new Comparator<Rectangle>() {

		
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			int recArea1=o1.area();
			int recArea2=o2.area();
			return  Integer.compare(recArea1, recArea2);
		}
		
	};
	

	/**
	 * Create the frame.
	 */
	public FrmSort() {
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
		
		JButton btnAddRectangle = new JButton("Add Rectangle");
		btnAddRectangle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlg1=new DlgStack();
				dlg1.setLocationRelativeTo(null);
				dlg1.getBtnRemove().setVisible(false);
				dlg1.setVisible(true);
				if(dlg1.isFlag()) {
					Sort.addElement(dlg1.getRectangle().toStringSort());
					aList.add(dlg1.getRectangle());
					
				}
			}
		});
		
		JButton btnSortRectangles = new JButton("Sort Rectangles");
		btnSortRectangles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSortRectangles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty! Please add a rectangles!", "Error", JOptionPane.ERROR_MESSAGE, null);
				}else {
					Sort.clear();
					Collections.sort(aList,compare);
					for(Rectangle r : aList) {
						Sort.addElement(r.toStringSort());
					}
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddRectangle, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addComponent(btnSortRectangles, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddRectangle, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSortRectangles, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JList<String> listSort = new JList<String>();
		scrollPane.setViewportView(listSort);
		contentPane.setLayout(gl_contentPane);
		listSort.setModel(Sort);
	}

}
