package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.*;
import panelsGame.*;
import storage.*;
import util.*;

public class GameController implements ActionListener{
	
	private JFrame frame;
	
	private ActionListener globalListener;
	
	private GamePanel gp;
	private NatObjPanel nop;
	private ResearchPanel rp;
	
	private JPanel panelGame;
	private JPanel panelNatObj;
	private JPanel panelResearch;
	
	private Game game;
	
	private LoadGameUtil lgu;
	
	/**
	 * Constructor
	 *
	 * @param frame - the frame used for all of the controllers to display all of the content panes 
	 * @param globalListener - used to go between different controllers when specific buttons are pressed
	 */
	public GameController(JFrame frame, ActionListener globalListener){
		this.frame = frame;
		this.globalListener = globalListener;
		this.frame.setVisible(true);
	}
	
	public void start(LoadGameUtil lgu, int nation, int round, List<Nation> nations, List<Unit> units, List<Research> research){
		this.lgu = lgu;
		game = new Game(nation,round,nations,units);
		gp = new GamePanel(this,globalListener);
		nop = new NatObjPanel(this);
		rp = new ResearchPanel(this);
		
		panelGame = gp.getContentPane();
		panelNatObj = nop.getContentPane();
		panelResearch = rp.getContentPane();
		
		gp.displayNorth(game.getNations());
		gp.displayWest(units);
		rp.displayCenter(game.getNations(),research);
		
		updateGamePanel();
		setFrameBounds();
		switchPanel(panelGame);
	}
	/**
	 * Sets the bounds of the frame every time the MainController switches to the HomeControlle.
	 */
	private void setFrameBounds(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
	}
	/**
	 * @param panel - JPanel panel to be displayed on the frame
	 */
	private void switchPanel(JPanel panel){
		System.out.println("SWITCHING: "+panel.getName()+" Panel");
		frame.setTitle("G - Axis and Allies | "+panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	/**
	 * Ends the turn of the current nation
	 *  -Adds income to bank
	 *  -Updates bank label
	 *  -Finds next nation and updates the screen
	 */
	private void endTurn(){
		System.out.println(game.getN().getName()+" is ending their turn");
		game.endTurn();
		gp.updateNInfo(game.getN());
		game.nextNation();
		updateGamePanel();
		updateEcoNation();
		save();
		if(game.getN().getName().contains("Britain")){
			
		}
		if(game.getN().getName().contains("Pacific")){
			endTurn();
		}
	}
	/**
	 * Updates the Game Panel for the current Nation
	 */
	private void updateGamePanel(){
		gp.setNation(game.getN());
		gp.updateRound(game.getRound());
	}
	/**
	 * Updates the NatObjPanel for the current ecoNation
	 */
	private void updateNatObjPanel(){
		nop.setNation(game.getEcoN(), game.getPreEcoN(), game.getNxtEcoN());
	}
	/**
	 * Sets the color of the button pressed to either the nations color or white
	 * Adds the income as bonus income to the nation
	 * @param i - index value if button in the list of objective buttons
	 */
	private void natObjButtonPressed(int i){
		game.objectiveBtnPressed(i);
		if(game.getEcoN().getObjectives().get(i).isEnabled()){
			nop.setObjectiveButtonColor(i, game.getEcoN().getColorLight());
		}else{
			nop.setObjectiveButtonColor(i, ColorUtil.white);
		}
		nop.setNationBonusIncome(game.getEcoN().getNatObjIncome());
	}
	/**
	 * Updates the Game Panel for the correct ecoNation
	 */
	private void updateEcoNation(){
		gp.updateEcoNationButton(game.getEcoN());
		if(game.getEcoN()!=game.getN()){
			gp.updateEcoNationColor(game.getEcoN().getColor(), game.getEcoN().getColorLight());
		}
	}
	/**
	 * Saves the current state of the game
	 * Used at the END of every turn
	 * Anything pressed after end turn will NOT be saved
	 */
	private void save(){
		//Adds one to the position for user readability. (1 for the first position not 0)
		int position = game.getNation()+1;
		if(position==game.getNations().size()+1){
			position=1;
		}
		try {
			lgu.saveFolder(position, game.getRound(), game.getNations());
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Printing save game to the folder");
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		int i;
		switch(name){
		case"EndTurn":
			endTurn();
			break;
		case"changeEcoNation":
			game.nextEcoNation();
			updateEcoNation();
			break;
		case"NatObj":
			updateNatObjPanel();
			switchPanel(panelNatObj);
			break;
		case"Research":
			switchPanel(panelResearch);
			break;
		case"Next":
			game.nextEcoNation();
			updateNatObjPanel();
			break;
		case"Previous":
			game.previousEcoNation();
			updateNatObjPanel();
			break;
		case"Back":
			switchPanel(panelGame);
			for(Nation n: game.getNations()){
				gp.updateNInfo(n);
			}
			updateEcoNation();
			break;
		case"Reset_Buy":
			game.resetBuy();
			gp.resetUnitCount();
			gp.updateNInfo(game.getN());
			break;
		case"Reset_Research":
			game.resetAllResearch();
			rp.resetAllButtons();
			break;
		case"Pacific":
			game.nextNation();
			updateGamePanel();
			break;
		default:
			if(name.contains("ecoBank_")){
				i=Integer.parseInt(name.substring(8, name.length()));
				game.changeEcoNationBank(i);
			}else if(name.contains("ecoIncome_")){
				i=Integer.parseInt(name.substring(10, name.length()));
				game.changeEcoNationIncome(i);
			}else if(name.contains("objective_")){
				i=Integer.parseInt(name.substring(10, name.length()));
				natObjButtonPressed(i);
				break;
			}else if(name.contains("research_")){
				i=Integer.parseInt(name.substring(9, name.length()));
				int natPosition = game.researchButtonPressed(source.getText(),i);
				rp.setButtonPressed(natPosition, i, game.getN(natPosition).getColor());
			}else if(name.contains("buy_")){
				i=Integer.parseInt(name.substring(4, name.length()));
				game.buyUnit(i);
				gp.buyUnit(game.getUnit(i));
			}
			gp.updateNInfo(game.getEcoN());
			updateEcoNation();
			break;
		}
	}
}
