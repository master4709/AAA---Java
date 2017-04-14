package panelsHome;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;

public class CreateGamePanel extends MyPanel{
	
	private JLabel lblTitle;
	
	private JLabel lblAdd;
	private JLabel lblName;
	private JLabel lblIncome;
	private JLabel lblColor;
	private JLabel lblObjective;
	
	private JButton btnRemove;
	private JButton btnAdd;
	
	private JButton btnReset;
	private JButton btnBack;
	
	
	private JTextField txtName;
	private JTextField txtIncome;
	private JComboBox<Color> colorBox;
	private JButton btnObjective;
	
	private List<JTextField> names = new ArrayList<>();
	private List<JTextField> income = new ArrayList<>();
	private List<JComboBox<Color>> colors = new ArrayList<>();
	private List<JButton> objectives = new ArrayList<>();
	
	private List<Color> possibleColors = new ArrayList<>();
	private List<String> possibleColorsString = new ArrayList<>();
	
	private Map<Color, String> colorMap = new HashMap<Color, String>();
	
	
	public CreateGamePanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("AboutPanel");
		
		findColors();
		
		displayNorth();
		displayCenter();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("Create Game",nationFontSize);
		//lblTitle.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
		lblTitle.setBorder(new EmptyBorder(new Insets(-5,-5,-5,-5)));
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
		
		ImageIcon icon = new ImageIcon(source+"images/cross.png");
		btnRemove = new MyButton(icon);
		center.add(btnRemove,"cell 0 1,");
		btnRemove.addActionListener(packageListener);
		btnRemove.setName("Remove");
		
		icon = new ImageIcon(source+"images/plus.png");
		btnAdd = new MyButton(icon);
		center.add(btnAdd,"cell 0 2");
		btnAdd.addActionListener(packageListener);
		btnAdd.setName("Add");
	}
	
	private void displaySouth(){
		btnBack = new MyButton("Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
		btnBack.addActionListener(packageListener);
		
		btnReset = new MyButton("Reset", btnFontSize);
		south.add(btnReset,"cell 1 0");
		btnReset.setName("Reset");
		btnReset.addActionListener(packageListener);
	}
	
	public void createNation(int position){
		
		int yPosition = position + 1;
		
		center.add(btnRemove,"cell 0 "+yPosition);
		
		txtName = new MyTextField("Nation", unitFontSize);
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
		
		btnObjective = new MyButton("National Objectives",unitFontSize);
		center.add(btnObjective,"cell 4 "+yPosition);
		btnObjective.setName("Objective_"+position);
		btnObjective.addActionListener(packageListener);
		objectives.add(btnObjective);
		
		center.add(btnAdd,"cell 0 "+yPosition+1);
	}
	
	public void reset(){
		center.removeAll();
		displayCenter();
	}
	public void removeNation(){
		center.remove(names.get(names.size()-1));
		center.remove(income.get(income.size()-1));
		center.remove(colors.get(colors.size()-1));
		center.remove(objectives.get(objectives.size()-1));
		
		names.remove(names.size()-1);
		income.remove(income.size()-1);
		colors.remove(colors.size()-1);
		objectives.remove(objectives.size()-1);
		
		center.remove(btnRemove);
		center.remove(btnAdd);
		
		addBtnsBack();
	}
	
	public void addBtnsBack(){
		ImageIcon icon = new ImageIcon(source+"images/cross.png");
		JButton btnRemove = new MyButton(icon);
		center.add(btnRemove,"cell 0 "+names.size());
		btnRemove.addActionListener(packageListener);
		btnRemove.setName("Remove");
		
		icon = new ImageIcon(source+"images/plus.png");
		JButton btnAdd = new MyButton(icon);
		center.add(btnAdd,"cell 0 "+(names.size()+1));
		btnAdd.addActionListener(packageListener);
		btnAdd.setName("Add");
	}
	
	private void findColors(){
		try {
			Scanner scan = new Scanner(new File(source+"colors.txt"));
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				possibleColors.add(getColor(line));
				possibleColorsString.add(line);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Color getColor(String color){
		Color c;
		try{
			Field field = Colors.class.getField(color);
			c = (Color)field.get(null);
		} catch (Exception e){
			System.out.println("No Color found with name: "+color);
			c = null;
		}
		return c;
	}
	
	private Color getColorLight(String color){
		Color c;
		try{
			Field field = Colors.class.getField("light_"+color);
			c = (Color)field.get(null);
		} catch (Exception e){
			c = null;
		}
		return c;
	}
	
	public class MyCellRenderer extends JButton implements ListCellRenderer<Object> {  
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public MyCellRenderer() {
			setOpaque(true); 
			colorMap.put(Colors.red, "Red");
			colorMap.put(Colors.orange, "Orange");
			colorMap.put(Colors.yellow, "Yellow");
			colorMap.put(Colors.pink, "Pink");
			colorMap.put(Colors.purple, "Purple");
			colorMap.put(Colors.blue, "Blue");
			colorMap.put(Colors.blue_light, "Blue_L");
			colorMap.put(Colors.green, "Green");
			colorMap.put(Colors.green_light, "Green_L");
			colorMap.put(Colors.brown, "Brown");
			colorMap.put(Colors.tan_dark, "Tan_D");
			colorMap.put(Colors.tan, "Tan");
			colorMap.put(Colors.black, "Black");
			colorMap.put(Colors.grey, "Grey");
			colorMap.put(Colors.white, "White");
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
	    	setText(colorMap.get(value));
	    	setBackground((Color)value);        
	    	b=false;
	    	return this;  
	    }
	}
}

