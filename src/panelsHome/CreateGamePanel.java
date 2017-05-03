package panelsHome;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;
import util.ColorUtil;

public class CreateGamePanel extends MyPanel{
	
	private JLabel lblTitle;
	
	private JLabel lblName;
	private JLabel lblIncome;
	private JLabel lblColor;
	private JLabel lblObjective;
	
	private JButton btnAdd;
	
	private JButton btnReset;
	private JButton btnBack;
	
	
	private JTextField txtName;
	private JTextField txtIncome;
	private JComboBox<Color> colorBox;
	private JButton btnObjective;
	
	private List<JButton> remove = new ArrayList<>();
	private List<JTextField> names = new ArrayList<>();
	private List<JTextField> income = new ArrayList<>();
	private List<JComboBox<Color>> colors = new ArrayList<>();
	private List<JButton> objectivesBtns = new ArrayList<>();
	
	private List<Color> possibleColors = new ArrayList<>();
	
	public CreateGamePanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("CreateGame");
		
		findColors();
		
		displayNorth();
		displayCenter();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("CreateGame",nationFontSize);
		lblTitle.setBorder(emptyBorder);
		north.add(lblTitle,"cell 0 0");
	}
	
	private void displayCenter(){
		int fontSize = 25;
		
		center.setLayout(new MigLayout("gapy 0,gapx 3", "[]", "[]"));
		
		lblName = new MyLabel("Name", fontSize);
		center.add(lblName, "cell 1 0,alignx center");
		
		lblIncome = new MyLabel("Starting IPC", fontSize);
		center.add(lblIncome, "cell 2 0,alignx center");
		
		lblColor = new MyLabel("Color", fontSize);
		center.add(lblColor, "cell 3 0,alignx center");
		
		lblObjective = new MyLabel("Objectives", fontSize);
		center.add(lblObjective, "cell 4 0,alignx center");
		
		ImageIcon icon = new ImageIcon(source+"images/plus.png");
		btnAdd = new MyButton(packageListener,icon);
		center.add(btnAdd,"cell 0 2");
		btnAdd.setName("Add_CreateGame");
	}
	
	private void displaySouth(){
		btnBack = new MyButton(packageListener,"Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
		
		btnReset = new MyButton(packageListener,"Reset", btnFontSize);
		south.add(btnReset,"cell 1 0");
		btnReset.setName("Reset_CreateGame");
	}
	
	public void createNation(int position){
		
		int yPosition = position + 1;
		ImageIcon icon = new ImageIcon(source+"images/cross.png");
		JButton btnRemove = new MyButton(packageListener,icon);
		center.add(btnRemove,"cell 0 "+yPosition);
		btnRemove.setName("Remove_CreateGame_"+position);
		remove.add(btnRemove);
		
		txtName = new MyTextField("Nation "+yPosition, unitFontSize);
		center.add(txtName,"cell 1 "+yPosition+",");
		txtName.setColumns(7);
		txtName.setName("Name_"+position);
		names.add(txtName);
		
		txtIncome = new MyTextField("0", unitFontSize);
		center.add(txtIncome,"cell 2 "+yPosition+",alignx center");
		txtIncome.setColumns(5);
		txtIncome.setName("Income_"+position);
		income.add(txtIncome);
		
		colorBox = new JComboBox<>(possibleColors.toArray(new Color[0]));
		center.add(colorBox, "cell 3 "+yPosition+",growx");
		colorBox.setMaximumRowCount(5);
		colorBox.setSelectedIndex(position);
		colorBox.setPreferredSize(new Dimension(50,20));
		colorBox.setRenderer(new MyCellRenderer());
		colorBox.setName("Color_"+position);
		colors.add(colorBox);
		
		btnObjective = new MyButton(packageListener,"National Objectives",unitFontSize);
		center.add(btnObjective,"cell 4 "+yPosition);
		btnObjective.setName("Objective_"+position);
		objectivesBtns.add(btnObjective);
		if(position!=9){
			center.add(btnAdd,"cell 0 "+(names.size()+1));
		}else{
			center.remove(btnAdd);
		}
	}
	
	public void reset(){
		center.removeAll();
		remove = new ArrayList<>();
		names = new ArrayList<>();
		income = new ArrayList<>();
		colors = new ArrayList<>();
		objectivesBtns = new ArrayList<>();
		displayCenter();
		createNation(0);
		
	}
	/**
	 * Removes the current nation in the create nation panel
	 * moves every other nation settings up one position to re-fit the screen
	 * re-names all of the components as well
	 * @param position
	 */
	public void removeNation(int position){
		center.remove(remove.get(position));
		center.remove(names.get(position));
		center.remove(income.get(position));
		center.remove(colors.get(position));
		center.remove(objectivesBtns.get(position));
		
		remove.remove(position);
		names.remove(position);
		income.remove(position);
		colors.remove(position);
		objectivesBtns.remove(position);
		
		for(int i=position;i<names.size();i++){
			remove.get(i).setName("Remove_CreateGame_"+i);
			names.get(i).setName("Name"+i);
			income.get(i).setName("Income_"+i);
			colors.get(i).setName("Color_"+i);
			objectivesBtns.get(i).setName("Objective_"+i);
			int y = i +1;
			center.add(remove.get(i),"cell 0 "+y);
			center.add(names.get(i),"cell 1 "+y);
			center.add(income.get(i),"cell 2 "+y+",alignx center");
			center.add(colors.get(i),"cell 3 "+y);
			center.add(objectivesBtns.get(i),"cell 4 "+y);
		}
		
		center.add(btnAdd,"cell 0 "+(names.size()+1));
	}
	
	public void addBtnsBack(){
	}
	
	private void findColors(){
		try(Scanner scan = new Scanner(new File(source+"colors.txt"))) {
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				possibleColors.add(ColorUtil.getColor(line));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getNName(int nation){
		return names.get(nation).getText();
	}
	
	public Color getNColor(int nation){
		return colors.get(nation).getBackground();
	}
	
	private class MyCellRenderer extends JButton implements ListCellRenderer<Object> {  

		private static final long serialVersionUID = 1L;
		
		public MyCellRenderer() {
			setOpaque(true); 
			
		}
		boolean b=false;
		@Override
		public void setBackground(Color bg) {
			if(!b)
			{
				return;
			}
			super.setBackground(bg);
		}
		public Component getListCellRendererComponent(JList<?> list,Object value,int index,boolean isSelected,boolean cellHasFocus){
			b=true;
	    	setText(ColorUtil.colorMap.get(value));
	    	setBackground((Color)value);
	    	b=false;
	    	return this;
	    }
	}
}

