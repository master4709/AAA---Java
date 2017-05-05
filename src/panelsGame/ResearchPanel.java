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

	private JLabel lblTitle;
	
	private JButton btnBack;
	
	private List<List<JButton>> btnList = new ArrayList<>();
	
	public ResearchPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("Research");
		displayNorth();
		displaySouth();
	}
	
	private void displayNorth(){
		lblTitle = new MyLabel("Research",nationFontSize);
		north.add(lblTitle,"cell 0 0,alignx center");
	}
	
	public void displayCenter(List<Nation> nations, List<Research> research){
		center.setLayout(new MigLayout("insets 0,gapy 0","[50]","[50]"));
		List<JButton> list;
		String name;
		for(Nation n: nations){
			list = new ArrayList<>();
			if(!n.getName().contains("Pacific")){
				if(n.getName().length()>3){
					name = n.getName().substring(0,4);
				}else if(n.getName().length()>2){
					name = n.getName().substring(0,3);
				}else if(n.getName().length()>1){
					name = n.getName().substring(0,2);
				}else{
					name = n.getName().substring(0,1);
				}
				for(int y=0;y<12;y++){
					JButton btn = new MyButton(packageListener,name,unitFontSize,ColorUtil.white);
					center.add(btn,"cell "+n.getPosition()+" "+y+",growx,growy");
					btn.setName("research_"+y);
					if(n.getResearch().get(y)){
						btn.setBackground(n.getColor());
					}
					list.add(btn);
				}
				JButton btnReset = new MyButton(packageListener,"Reset",unitFontSize,n.getColor());
				center.add(btnReset,"cell "+n.getPosition()+" 12,growx,growy");
				btnReset.setName("reset_"+n.getPosition());
			}
			btnList.add(list);
		}
		
		Color c  = ColorUtil.white;
		Color l = ColorUtil.light_grey;
		for(Research r: research){
			JLabel lblResearch = new MyLabel(r.getResearch(),btnFontSize*4/3,c);
			center.add(lblResearch,"cell "+nations.size()+" "+r.getPosition()+",alignx left");
			lblResearch.setBorder(emptyBorder);
			JLabel lblInfo = new MyLabel(" - " + r.getInfo(),unitFontSize,l);
			center.add(lblInfo,"cell "+nations.size()+" "+r.getPosition()+",alignx left,aligny center");
			lblInfo.setBorder(emptyBorder);
		}
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back",nationInfoFontSize);
		south.add(btnBack,"cell 0 0, aligny bottom");
		btnBack.setName("Back");
	}
	
	public void setButtonPressed(int nation, int position, Color c){
		btnList.get(nation).get(position).setBackground(c);
	}
	
	public void resetButtons(int position){
		for(JButton btn: btnList.get(position)){
			btn.setBackground(ColorUtil.white);
		}
	}
}
