package panelsGame;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;
import storage.*;
import util.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NatObjPanel extends MyPanel{
	
	private JLabel lblNation;
	private JLabel lblNationBonusIncome;
	
	private JButton btnBack;
	private JButton btnNatNext;
	private JButton btnNatBackward;
	
	private List<JButton> objectiveButton = new ArrayList<>();
	
	public NatObjPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("NatObjPanel");
		
		displayNorth();
		displaySouth();
		displayCenter();
		displayEast();
		displayWest();
	}
	
	private void displayNorth(){
		north.setLayout(new MigLayout("","[350][grow][grow][350]",""));
		btnNatBackward = new MyButton(packageListener,"Previous Nation",nationFontSize);
		north.add(btnNatBackward,"cell 0 0,growx,growy");
		btnNatBackward.setName("Previous");
		
		lblNation = new MyLabel("",nationFontSize*2);
		north.add(lblNation,"cell 1 0,alignx right");
		
		lblNationBonusIncome = new MyLabel("0",nationFontSize*2);
		north.add(lblNationBonusIncome,"cell 2 0,alignx left");
		
		btnNatNext = new MyButton(packageListener,"Next Nation",nationFontSize);
		north.add(btnNatNext,"cell 3 0, growx,growy");
		btnNatNext.setName("Next");
	}
	
	public void displayCenter(){
		for(int i=0;i<12;i++){
			JButton btn = new MyButton(packageListener,"");
			center.add(btn,"cell 0 "+i+",growx");
			btn.setHorizontalAlignment(SwingConstants.LEFT);
			btn.setVisible(false);
			objectiveButton.add(btn);
		}
		
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back",nationFontSize);
		south.add(btnBack,"cell 0 0,alignx left, aligny bottom");
		btnBack.setName("Back");
		
	}
	
	private void displayEast(){
		JLabel lbl = new MyLabel("   ",nationFontSize);
		east.add(lbl,"cell 0 0");
	}
	
	private void displayWest(){
		JLabel lbl = new MyLabel("   ",nationFontSize);
		west.add(lbl,"cell 0 0");
	}
	
	public void setObjectiveButtonColor(int i, Color c){
		objectiveButton.get(i).setBackground(c);
	}
	
	public void setNation(Nation n, Nation previous, Nation next){
		lblNation.setText(n.getName()+" | ");
		lblNation.setForeground(n.getColor());
		lblNationBonusIncome.setForeground(n.getColor());
		btnNatNext.setText(next.getName());
		btnNatBackward.setText(previous.getName());
		btnNatNext.setBackground(next.getColorLight());
		btnNatBackward.setBackground(previous.getColorLight());
		btnBack.setBackground(n.getColorLight());
		for(int i=0;i<objectiveButton.size();i++){
			if(i<n.getObjectives().size()){
				objectiveButton.get(i).setVisible(true);
				objectiveButton.get(i).setName("objective_"+i);
				objectiveButton.get(i).setText(n.getObjectives().get(i).toString());
				objectiveButton.get(i).setFont(new MyFont(objectiveButton.get(i).getText(), btnFontSize, screenWidth-100));
				if(n.getObjectives().get(i).isEnabled()){
					objectiveButton.get(i).setBackground(n.getColorLight());
				}else{
					objectiveButton.get(i).setBackground(ColorUtil.white);
				}
			}else{
				objectiveButton.get(i).setVisible(false);
				objectiveButton.get(i).setName("0");
				objectiveButton.get(i).setText("");
			}
		}
		setNationBonusIncome(n.getNatObjIncome());
	}
	
	public void setNationBonusIncome(int i){
		lblNationBonusIncome.setText(Integer.toString(i));
	}
}
