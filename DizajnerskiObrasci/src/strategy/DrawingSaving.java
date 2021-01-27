package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import geometry.Shape;

public class DrawingSaving implements Saving{
	
	private ArrayList<Shape> shapes;
	
	public DrawingSaving(ArrayList<Shape> shapes) {
		this.shapes=shapes;
	}

	@Override
	public void save() {

		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Korisnik\\Desktop");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		jFileChooser.setDialogTitle("Choose file location");
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			File drawing = new File(jFileChooser.getSelectedFile().getAbsolutePath()+".ser");
			
			if(drawing.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				FileOutputStream fileOutputStream;
				ObjectOutputStream objectOutputStream;
				try {
					fileOutputStream = new FileOutputStream(drawing);
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
					
					objectOutputStream.writeObject(shapes);
					objectOutputStream.close();
					fileOutputStream.close();
					
					JOptionPane.showMessageDialog(null, "Successfully saved", "Saving complete",
							JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e) {
					e.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error while saving the file.", "Error!",JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		}
	}

}
