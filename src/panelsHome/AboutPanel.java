package panelsHome;
import myJStuff.*;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AboutPanel extends MyPanel{
	
	private JLabel lblTitle;
	private JButton btnBack;
	
	
	public AboutPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("About");
		
		displayNorth();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("About",nationFontSize*3/2);
		north.add(lblTitle,"cell 0 0");
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
	}
}
