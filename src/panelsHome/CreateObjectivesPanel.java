package panelsHome;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import myJStuff.*;

public class CreateObjectivesPanel extends MyPanel{
	
	private JLabel lblNation;
	
	private JButton btnBack;
	private JButton btnReset;
	
	public CreateObjectivesPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("CreateObjectives Panel");
		displayNorth();
		displaySouth();
	}
	
	private void displayNorth(){
		lblNation = new MyLabel("Current Nation",nationFontSize);
		north.add(lblNation,"cell 0 0,alignx center");
	}
	
	private void displaySouth(){
		btnBack = new MyButton("Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back_CreateObjectives");
		btnBack.addActionListener(packageListener);
		
		btnReset = new MyButton("Reset", btnFontSize);
		south.add(btnReset,"cell 1 0");
		btnReset.setName("Reset");
		btnReset.addActionListener(packageListener);
	}
	
	
	public void setNationName(String name, Color c){
		lblNation.setText(name);
		lblNation.setForeground(c);
	}
}
   