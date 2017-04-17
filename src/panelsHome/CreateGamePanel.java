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
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;
import storage.Objective;

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
	private List<String> possibleColorsString = new ArrayList<>();
	private List<List<Objective>> objectives = new ArrayList<>();
	
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
		
		ImageIcon icon = new ImageIcon(source+"images/plus.png");
		btnAdd = new MyButton(icon);
		center.add(btnAdd,"cell 0 2");
		btnAdd.addActionListener(packageListener);
		btnAdd.setName("Add_CreateGame");
	}
	
	private void displaySouth(){
		btnBack = new MyButton("Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
		btnBack.addActionListener(packageListener);
		
		btnReset = new MyButton("Reset", btnFontSize);
		south.add(btnReset,"cell 1 0");
		btnReset.setName("Reset_CreateGame");
		btnReset.addActionListener(packageListener);
	}
	
	public void createNation(int position){
		
		int yPosition = position + 1;
		ImageIcon icon = new ImageIcon(source+"images/cross.png");
		JButton btnRemove = new MyButton(icon);
		center.add(btnRemove,"cell 0 "+yPosition);
		btnRemove.setName("Remove_CreateGame_"+position);
		btnRemove.addActionListener(packageListener);
		remove.add(btnRemove);
		
		txtName = new MyTextField("Nation "+position, unitFontSize);
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
		objectivesBtns.add(btnObjective);
		
		List<Objective> o = new ArrayList<>();
		objectives.add(o);
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
		objectives.remove(position);
		
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
	
	public List<Objective> getNObjective(int nation){
		return objectives.get(nation);
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

