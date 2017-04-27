package panelsGame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;
import storage.*;
import util.ColorUtil;

public class ResearchPanel extends MyPanel{

	private JButton btnBack;
	
	private JButton btnReset;
	
	private List<List<JButton>> btnList = new ArrayList<>();
	
	public ResearchPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("Research");
		displayNorth();
		displaySouth();
	}
	
	private void displayNorth(){
		
	}
	
	public void displayCenter(List<Nation> nations, List<Research> research){
		center.setLayout(new MigLayout("insets 0,gapy 0","[50]","[50]"));
		List<JButton> list;
		for(Nation n: nations){
			list = new ArrayList<>();
			if(!n.getName().contains("Pacific")){
				String name = n.getName().substring(0,3);
				for(int y=0;y<12;y++){
					JButton btn = new MyButton(packageListener,name,btnFontSize*2/3,ColorUtil.white);
					center.add(btn,"cell "+n.getPosition()+" "+y+",growx,growy");
					btn.setName("research_"+y);
					
					if(n.getResearch().get(y)){
						btn.setBackground(n.getColor());
					}
					list.add(btn);
				}
			}
			btnList.add(list);
		}
		
		Color c  = ColorUtil.white;
		Color l = ColorUtil.light_grey;
		for(Research r: research){
			JLabel lblResearch = new MyLabel(r.getResearch(),nationInfoFontSize,c);
			center.add(lblResearch,"cell "+nations.size()+" "+r.getPosition()+",alignx left");
			lblResearch.setBorder(emptyBorder);
			JLabel lblInfo = new MyLabel(" - " + r.getInfo(),btnFontSize,l);
			center.add(lblInfo,"cell "+nations.size()+" "+r.getPosition()+",alignx left,aligny center");
			lblInfo.setBorder(emptyBorder);
		}
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back",nationInfoFontSize);
		south.add(btnBack,"cell 0 0, aligny bottom");
		btnBack.setName("Back");
		
		btnReset = new MyButton(packageListener,"Reset",nationInfoFontSize);
		south.add(btnReset,"cell 1 0,aligny bottom");
		btnReset.setName("Reset_Research");
	}
	
	public void setButtonPressed(int nation, int position, Color c){
		btnList.get(nation).get(position).setBackground(c);
	}
	
	public void resetAllButtons(){
		for(List<JButton> list:btnList){
			for(JButton btn: list){
				btn.setBackground(ColorUtil.white);
			}
		}
	}
	
	
}
