package panelsHome;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import myJStuff.*;
import storage.Objective;

public class CreateObjectivesPanel extends MyPanel{
	
	private JLabel lblNation;
	
	private JButton btnBack;
	private JButton btnReset;

	private List<List<Objective>> objectives = new ArrayList<List<Objective>>();
	
	int position;
	
	public CreateObjectivesPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("Create Objectives");
		displayNorth();
		displaySouth();
	}
	
	private void displayNorth(){
		lblNation = new MyLabel("Current Nation",nationFontSize);
		north.add(lblNation,"cell 0 0,alignx center");
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back_CreateObjectives");
		
		btnReset = new MyButton(packageListener,"Reset", btnFontSize);
		south.add(btnReset,"cell 1 0");
		btnReset.setName("Reset");
	}
	
	private void displayObjectives(){
		//List<Objective> obj = objectives.get(position);
	}
	
	
	public void setNation(String name, Color c, int position){
		this.position = position;
		lblNation.setText("Objectives: "+name);
		lblNation.setForeground(c);
		displayObjectives();
	}
	
	public void resetNObjectives(){
	//	objectives.get(position)
	}
	
	public void addNObjective(){
		List<Objective> o = new ArrayList<>();
		objectives.add(o);
	}
	
	public void removeNObjective(int position){
		objectives.remove(position);
	}
	
	public List<List<Objective>> getNObjectives(){
		return objectives;
	}
}
   