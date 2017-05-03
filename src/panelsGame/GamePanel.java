package panelsGame;

import storage.*;
import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePanel extends MyPanel{
	
	private ActionListener globalListener;
	
	private List<JLabel> bankLabel = new ArrayList<>();
	private List<JLabel> incomeLabel = new ArrayList<>();
	
	private JLabel lblCount;
	private JLabel lblBuy;
	private JLabel lblName;
	private JLabel lblA;
	private JLabel lblD;
	private JLabel lblM;
	private JLabel lblC;
	
	private List<JLabel> countLabel = new ArrayList<>();
	private List<JButton> buyButton = new ArrayList<>();
	private List<List<JLabel>> statsLabel = new ArrayList<>();
	private JButton btnReset;
	private JButton btnPacific;

	private JButton btnEcoChange;
	private JButton btnNatObj;
	private JButton btnResearch;
	private List<Integer> ecoNumbers = new ArrayList<>(Arrays.asList(1,-1,5,-5,10,-10));
	private List<JButton> ecoButton = new ArrayList<>();
	
	private JButton btnHome;
	private JButton btnEndTurn;
	private JLabel lblRoundel;
	private JLabel lblNation;
	private JLabel lblRound;
	
	public GamePanel(ActionListener packageListener, ActionListener globalListener){
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		
		contentPane.setName("Game");
		
		displayEast();
		displayCenter();
		displaySouth();
	}
	
	public void displayNorth(List<Nation> nations){
		int size = getNationFontSize(nations);
		int x=0;
		for(Nation n: nations){
			JLabel nation = new MyLabel(n.getName(),size,n.getColor());
			north.add(nation,"cell "+x+" 0,alignx center");
			nation.setBorder(emptyBorder);
			JLabel bank = new MyLabel(n.getBank(),size*2/3,n.getColor(),Font.BOLD);
			north.add(bank,"cell "+x+" 1, alignx center");
			JLabel divider = new MyLabel("|",btnFontSize,n.getColor());
			north.add(divider,"cell "+x+" 1,aligny center");
			JLabel income = new MyLabel(n.getIncome()+n.getNatObjIncome(),size*2/3,n.getColor(),Font.BOLD);
			north.add(income,"cell "+x+" 1, alignx center");
			x++;
			bankLabel.add(bank);
			incomeLabel.add(income);
		}
	}
	
	public void displayWest(List<Unit> units){
		lblCount = new MyLabel("Count",btnFontSize);
		west.add(lblCount, "cell 0 0,alignx center");
		lblBuy = new MyLabel("Buy",btnFontSize);
		west.add(lblBuy, "cell 1 0,alignx center");
		lblName = new MyLabel("Name",btnFontSize);
		west.add(lblName, "cell 2 0,alignx center");
		lblA = new MyLabel("A",btnFontSize);
		west.add(lblA, "cell 3 0,alignx center");
		lblD = new MyLabel("D",btnFontSize);
		west.add(lblD,"cell 4 0,alignx center");
		lblM = new MyLabel("M",btnFontSize);
		west.add(lblM, "cell 5 0,alignx center");
		lblC = new MyLabel("C",btnFontSize);
		west.add(lblC, "cell 6 0,alignx center");
		List<JLabel> row;
		for(Unit u: units){
			JLabel lblCount = new MyLabel(u.getCount(),unitFontSize,Font.BOLD);
			west.add(lblCount,"cell 0 "+u.getPosition()+1+",alignx center");
			
			JButton btnBuy = new MyButton(packageListener,u.getCost(),unitFontSize);
			west.add(btnBuy,"cell 1 "+u.getPosition()+1+",growx,growy");
			btnBuy.setBorder(emptyBorder);
			btnBuy.setName("buy_"+u.getPosition());

			JLabel lblName = new MyLabel(u.getName(),unitFontSize);
			west.add(lblName,"cell 2 "+u.getPosition()+1+",alignx left");

			JLabel lblAttack = new MyLabel(u.getAttack(),unitFontSize);
			west.add(lblAttack,"cell 3 "+u.getPosition()+1+",alignx center");
			
			JLabel lblDefense = new MyLabel(u.getDefense(),unitFontSize);
			west.add(lblDefense,"cell 4 "+u.getPosition()+1+",alignx center");
			
			JLabel lblMovement = new MyLabel(u.getMovement(),unitFontSize);
			west.add(lblMovement,"cell 5 "+u.getPosition()+1+",alignx center");

			JLabel lblCost = new MyLabel(u.getCost(),unitFontSize);
			west.add(lblCost,"cell 6 "+u.getPosition()+1+",alignx center");
			
			row = new ArrayList<>(Arrays.asList(lblName,lblAttack,lblDefense,lblMovement,lblCost));
			countLabel.add(lblCount);
			buyButton.add(btnBuy);
			statsLabel.add(row);
		}
		
		btnReset = new MyButton(packageListener,"Reset Buy",btnFontSize);
		west.add(btnReset,"cell 0 "+units.size()+1+" 2 1,growx");
		btnReset.setName("Reset_Buy");
		
		btnPacific = new MyButton(packageListener,"Buy Pacific Units",btnFontSize);
		west.add(btnPacific,"cell 2 "+units.size()+1+" 5 1");
		btnPacific.setName("Pacific");
		btnPacific.setVisible(false);
	}
	
	private void displayEast(){
		btnEcoChange = new MyButton(packageListener,"",nationInfoFontSize);
		east.add(btnEcoChange,"cell 0 0,growx");
		btnEcoChange.setName("changeEcoNation");
		
		int k = 1;
		for(Integer i: ecoNumbers){
			String text="";
			if(i>0)text = "+"+i;
			else text = ""+i;
			
			JButton bank = new MyButton(packageListener,text,btnFontSize);
			east.add(bank,"cell 0 "+k+",growx");
			bank.setName("ecoBank_"+text);
			bank.setBorder(emptyBorder);
			
			JButton income = new MyButton(packageListener,text,btnFontSize);
			east.add(income,"cell 0 "+k+",growx");
			income.setName("ecoIncome_"+text);
			income.setBorder(emptyBorder);
			k++;
			ecoButton.add(bank);
			ecoButton.add(income);
		}
		k++;
		btnNatObj = new MyButton(packageListener,"National Objectives", nationInfoFontSize);
		east.add(btnNatObj,"cell 0 "+k+",growx");
		btnNatObj.setName("NatObj");
		k++;
		btnResearch = new MyButton(packageListener,"Research", nationInfoFontSize);
		east.add(btnResearch,"cell 0 "+k+",growx");
		btnResearch.setName("Research");
	}
	
	private void displayCenter(){
		
	}
	
	private void displaySouth(){
		south.setLayout(new MigLayout("gapy 1", "[][][grow][grow][]", "[]0[]"));
		btnHome = new MyButton(globalListener,"Home",nationInfoFontSize);
		south.add(btnHome,"cell 0 0,aligny bottom");
		btnHome.setName("Home_Game");
		
		lblRound = new MyLabel("Round: ",btnFontSize);
		south.add(lblRound,"cell 1 0,aligny bottom");
		
		lblRoundel = new MyLabel();//Displays the current nations image in the corner
		south.add(lblRoundel,"cell 2 0 ,alignx right,aligny bottom");
		
		lblNation = new MyLabel("",nationFontSize*3/2);//Displays the current nation playing
		south.add(lblNation,"cell 3 0,aligny center");
		
		btnEndTurn = new MyButton(packageListener,"End Turn", btnFontSize*3/2);//Creates the buton that ends the current players turn and goes to the next
		south.add(btnEndTurn,"cell 4 0 ,alignx right,growy");
		btnEndTurn.setName("EndTurn");
	}
	
	public void setNation(Nation n){
		lblRoundel.setIcon(n.getRoundel());
		lblNation.setText(n.getName());
		lblNation.setForeground(n.getColor());
		btnEndTurn.setBackground(n.getColorLight());
		btnReset.setBackground(n.getColor());
		btnPacific.setBackground(n.getColor());
		updateEcoNationButton(n);
		updateEcoNationColor(n.getColor(),n.getColorLight());
		resetUnitCount();
		
		updateUnitColor(n.getColor(),n.getColorLight());
	}
	
	private void updateUnitColor(Color c, Color l){
		lblCount.setForeground(c);
		lblBuy.setForeground(c);
		lblName.setForeground(c);
		lblA.setForeground(c);
		lblD.setForeground(c);
		lblM.setForeground(c);
		lblC.setForeground(c);
		Color m;
		for(int y=0;y<countLabel.size();y++){
			if(y%2==0) m=c;
			else m=l;
			countLabel.get(y).setForeground(m);
			buyButton.get(y).setBackground(m);
			for(JLabel lbl:statsLabel.get(y)){
				lbl.setForeground(m);
			}
		}
	}
	
	public void updateRound(int round){
		lblRound.setText("Round: "+ round);
	}
	
	public void updateNInfo(Nation n){
		bankLabel.get(n.getPosition()).setText(n.getBank().toString());
		incomeLabel.get(n.getPosition()).setText(n.getTotalIncome().toString());
	}
	
	/**
	 * Updates the text of the ecoNation button for the next bank and income of the nation
	 * @param n - the current ecoNation
	 */
	public void updateEcoNationButton(Nation n){
		String text = n.getBank()+" "+n.getName()+" "+n.getTotalIncome();
		btnEcoChange.setText(text);
	}
	/**
	 * Updates all of the JLabels and JButtons in the east panel for the current nation to change bank and income
	 * @param c - the main color of the nation
	 * @param l - the light version of the color
	 * Rotates between the two when setting the colors for each line
	 */
	public void updateEcoNationColor(Color c, Color l){
		for(JButton btn: ecoButton){
			if(Integer.parseInt(btn.getText())>0){
				btn.setBackground(c);
			}else{
				btn.setBackground(l);
			}
		}
		btnNatObj.setBackground(c);
		btnEcoChange.setBackground(c);
		btnResearch.setBackground(c);
	}
	
	public void buyUnit(Unit u){
		countLabel.get(u.getPosition()).setText(u.getCount().toString());
	}
	
	public void resetUnitCount(){
		for(JLabel lbl:countLabel) lbl.setText("0");
	}
	
	public void setPacificBtnVisible(boolean visible){
		btnPacific.setVisible(visible);
	}
	
	private Integer getNationFontSize(List<Nation> nations){
		String allNations="";
		for(Nation n: nations) allNations+=n.getName();
		int size = MyFont.setFontSize(allNations,nationFontSize,screenWidth-100);
		return size;
	}
}
