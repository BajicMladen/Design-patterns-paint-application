package strategy;

import java.io.File;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogSaving implements Saving {
	
	
	private DefaultListModel<String> dlm;
	
	public LogSaving(DefaultListModel<String> defaultListModel) {
		this.dlm=defaultListModel;
	}
	

	@Override
	public void save() {
		JFileChooser jFileChooser=new JFileChooser("C:\\Users\\Korisnik\\Desktop");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		jFileChooser.setDialogTitle("Choose file location");
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File log = new File(jFileChooser.getSelectedFile().getAbsolutePath()+ ".log");
			
			if(log.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					PrintWriter printWriter = new PrintWriter(log);
					for(int i=0; i<dlm.size();i++) {
						printWriter.println(dlm.get(i));
					}
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Successfully saved", "Saving complete",
							JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file.", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

}

