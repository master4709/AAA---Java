package test;

import myJStuff.*;
import storage.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GamePanel extends MyPanel{
	
	private ActionListener globalListener;
	
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	/**
	 * All of the JObjects needed for the North Panel
	 */
	private List<JLabel> bankLabel = new ArrayList<>();
	private List<JLabel> incomeLabel = new ArrayList<>();
	/**
	 * All of the JObjects needed for the West Panel (buying units)
	 */
	private JLabel lblCount;
	private JLabel lblBuy;
	private JLabel lblName;
	private JLabel lblAttack;
	private JLabel lblDefense;
	private JLabel lblMovement;
	private JLabel lblCost;
	private List<JButton> unitButton = new ArrayList<>();
	private List<JLabel> unitTopLabel = new ArrayList<>();
	private List<List<JLabel>> unitLabel = new ArrayList<>();
	/**
	 * All of the JObjects needed for the East Panel (Changing a nations bank or income)
	 */
	private JButton btnEcoChange;
	private JButton btnNatObj;
	private JLabel lblBankIncome;
	private List<JButton> ecoButton = new ArrayList<>();
	//private List<Integer> ecoNumbers = new ArrayList<>(Arrays.asList(1,-1,5,-5,10,-10));
	/**
	 * All of the JObjects for the SouthPanel
	 */
	private JLabel lblRoundel;//Displays the image of the current nation
	private JLabel lblNation;//Displays the name of the current nation
	private JLabel lblRound;//Displays the text "Round: "
	private JLabel lblRoundCount;//Displays the round number (1,2,3...)
	private JButton btnHome;//When pressed sends the user to the main panel (main screen)
	private JButton btnEndTurn;//Ends the turn of the current nation and goes to the next one
	private JButton btnResetBuy;//Resets the current nations unit purchases
	private JButton btnBuyPacific;//Used for when Britain is split economy (option in new_game.txt) to buy units for the Pacific side

	public GamePanel(ActionListener packageListener, ActionListener globalListener){
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		contentPane.setName("GamePanel");
	}
	/**
	 * Creates 4 labels for each nation
	 * One for the name, bank, income and divider.
	 * @param nations
	 */
	public void displayNorth(List<Nation> nations){
	}
	
	public void displayWest(List<Unit> units){
		
		lblCount = new MyLabel("Count",btnFontSize,Font.BOLD);
		west.add(lblCount,"cell 0 0,alignx center");
		lblBuy = new MyLabel(" Buy ",btnFontSize,Font.BOLD);
		west.add(lblBuy,"cell 1 0,alignx center");
		lblName = new MyLabel("Name",btnFontSize,Font.BOLD);
		west.add(lblName,"cell 2 0,alignx center");
		lblAttack = new MyLabel(" A ",btnFontSize,Font.BOLD);
		west.add(lblAttack,"cell 3 0,alignx center");
		lblDefense = new MyLabel("D ",btnFontSize,Font.BOLD);
		west.add(lblDefense,"cell 4 0,alignx center");
		lblMovement = new MyLabel("M ",btnFontSize,Font.BOLD);
		west.add(lblMovement,"cell 5 0,alignx center");
		lblCost = new MyLabel("C ", btnFontSize,Font.BOLD);
		west.add(lblCost,"cell 6 0,alignx center");
		
		unitTopLabel = new ArrayList<>(Arrays.asList(lblCount,lblBuy,lblName,lblAttack,lblDefense,lblMovement,lblCost));
		
		List<JLabel> unit;
		int y=1;
		for(Unit u:units){
			unit = new ArrayList<>();
			JLabel count = new MyLabel("0",unitFontSize);
			west.add(count,"cell 0 "+y+",alignx center");
			JButton buy = new MyButton(packageListener,u.getCost(),unitFontSize);
			west.add(buy, "cell 1 "+y+",growx");
			buy.setName(Integer.toString(y-1));
			buy.setBorder(emptyBorder);
			buy.addActionListener(packageListener);
			JLabel name = new MyLabel(u.getName(),unitFontSize,Font.BOLD);
			west.add(name,"cell 2 "+y);
			JLabel attack = new MyLabel(u.getAttack(),unitFontSize,Font.BOLD);
			west.add(attack,"cell 3 "+y+",alignx center");
			JLabel defense = new MyLabel(u.getDefense(),unitFontSize,Font.BOLD);
			west.add(defense,"cell 4 "+y+",alignx center");
			JLabel movement = new MyLabel(u.getMovement(),unitFontSize,Font.BOLD);
			west.add(movement,"cell 5 "+y+",alignx center");
			JLabel cost = new MyLabel(u.getCost(),unitFontSize,Font.BOLD);
			west.add(cost,"cell 6 "+y+",alignx center");
			
			unit.add(count);
			unit.add(name);
			unit.add(attack);
			unit.add(defense);
			unit.add(movement);
			unit.add(cost);
			unitLabel.add(unit);
			unitButton.add(buy);
			y++;
		}
		y++;
		btnResetBuy = new MyButton(packageListener,"Reset Buy",btnFontSize);
		west.add(btnResetBuy,"cell 0 "+y+"0 2,growx");
		btnResetBuy.setName("ResetBuy");
		btnResetBuy.addActionListener(packageListener);
		btnResetBuy.setBorder(emptyBorder);
		btnBuyPacific = new MyButton(packageListener,"Buy Pacific Units",btnFontSize);
		west.add(btnBuyPacific,"cell 2 "+y+"0 3,growx");
		btnBuyPacific.setName("BuyPacific");
		btnBuyPacific.addActionListener(packageListener);
		btnBuyPacific.setVisible(false);
		btnBuyPacific.setBorder(emptyBorder);
	}
	
	public void displayEast(){
	}
	/**
	 * Crates the return to home button and end turn button
	 */
	public void displaySouth(int round){
		lblRound = new MyLabel("Round: ",btnFontSize);//Displays the text Round: in the bottom right corner
		south.add(lblRound,"cell 0 0,alignx left,aligny bottom");
		
		lblRoundCount = new MyLabel(round,btnFontSize);//Displays the current round of play
		south.add(lblRoundCount,"cell 0 0,alignx left,aligny bottom");
		//Create the button that sends the User back to the MainPanel
		btnHome = new MyButton(packageListener,"Home", btnFontSize);
		south.add(btnHome,"cell 0 1,aligny bottom");
		btnHome.setName("Home_Game");
		btnHome.addActionListener(globalListener);//Uses the actionListener in Controller
		
		lblRoundel = new MyLabel();//Displays the current nations image in the corner
		south.add(lblRoundel,"cell 1 0 1 2 ,alignx right,aligny bottom");
		
		lblNation = new MyLabel("",nationFontSize*3/2);//Displays the current nation playing
		south.add(lblNation,"cell 2 0 1 2, growx");
		
		btnEndTurn = new MyButton(packageListener,"End Turn", btnFontSize*2);//Creates the buton that ends the current players turn and goes to the next
		south.add(btnEndTurn,"cell 3 0 1 2 ,alignx right,growy");
		btnEndTurn.setName("EndTurn");
		btnEndTurn.addActionListener(packageListener);//Uses the actionListener in GameController
	}
	/**
	 * Updates the screen for the current nation
	 *  -sets color of all of the unit labels and buttons
	 *  -sets the colors of the ecoNation to the new nation
	 *  -sets the text of the change ecoNation button to the new nation
	 *  -sets all of the south panel to display the new nation
	 * @param n - the new nation
	 */
	public void updateCurrentNation(Nation n){
		updateUnitBuyColors(n.getColor(),n.getColorLight());
		updateEcoNationButton(n);
		updateEcoNationColors(n.getColor(),n.getColorLight());
		//setBackground(n.getColorLight());
		lblRoundel.setIcon(n.getRoundel());
		lblNation.setText(n.getName());
		lblNation.setForeground(n.getColor());
		btnEndTurn.setBackground(n.getColor());
		btnResetBuy.setBackground(n.getColor());
	}
	/**
	 * Updates all of the JLabels and JButtons in the west panel for the current nation of play
	 * @param c - the main color of the nation
	 * @param l - the light version of the color
	 * Rotates between the two when setting the colors for each line
	 */
	private void updateUnitBuyColors(Color c, Color l){
		int i=0;
		Color x;
		for(List<JLabel> list: unitLabel){
			if(i%2==0) {x = c;}
			else {x = l;}
			for(JLabel lbl: list) {lbl.setForeground(x);}
			if(i<unitButton.size()) {
				unitButton.get(i).setBackground(x);
				}
			i++;
		}
		
		for(JLabel lbl: unitTopLabel) {lbl.setForeground(c);}
	}
	/**
	 * Updates the text of the ecoNation button for the next bank and income of the nation
	 * @param n - the current ecoNation
	 */
	public void updateEcoNationButton(Nation n){
		String text = n.getBank()+" "+n.getName()+" "+n.getIncome();
		btnEcoChange.setText(text);
	}
	/**
	 * Updates all of the JLabels and JButtons in the east panel for the current nation to change bank and income
	 * @param c - the main color of the nation
	 * @param l - the light version of the color
	 * Rotates between the two when setting the colors for each line
	 */
	public void updateEcoNationColors(Color c, Color l){
		for(JButton btn: ecoButton){
			if(Integer.parseInt(btn.getText())>0){
				btn.setBackground(c);
			}else{
				btn.setBackground(l);
			}
		}
		btnNatObj.setBackground(c);
		btnEcoChange.setBackground(c);
		lblBankIncome.setForeground(c);
	}
	
	public void updateBankLabel(Nation n ){
		bankLabel.get(n.getPosition()).setText(Integer.toString(n.getBank()));
	}
	
	public void updateIncomeLabel(Nation n ){
		incomeLabel.get(n.getPosition()).setText(Integer.toString(n.getIncome()+n.getNatObjIncome()));
	}
	
	public void updateUnitCountLabel(Unit u){
		unitLabel.get(u.getPosition()).get(0).setText(Integer.toString(u.getCount()));
	}
	
	public void setPacificBtnVisible(boolean visible){
		btnBuyPacific.setVisible(visible);
	}
	
	public void updateRound(int round){
		lblRoundCount.setText(Integer.toString(round));
	}
}
