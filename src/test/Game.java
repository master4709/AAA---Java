package test;

import storage.*;

import java.util.List;



/**
 * 
 * @author Pierce de Jong 30006609
 */
public class Game {
	
	private List<Nation> nations;
	private List<Unit> units;
	
	//Current nation of play
	private Nation n;
	private Nation ecoN;
	
	private int round;
	//Index Value of current nation
	
	private int nation;
	private int ecoNation;
	
	private int oldBank;
	/*
	private void createFolder(){
		File source = new File(copyFolder);
		File dest = new File(saveFolder);
		try {
		    FileUtils.copyDirectory(source, dest);
			System.out.println("Copying folder from "+source+" to "+ dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void loadFolder(String folder){
		lf = new LoadFile(folder);
		sf = new SaveFile(saveFolder);
		nations = lf.getNations();
		units = lf.getUnits();
		nation = lf.getNation();
		ecoNation = lf.getNation();
		round = lf.getRound();
		this.n = nations.get(nation);
		this.ecoN = nations.get(nation);
		oldBank = lf.getNations().get(nation).getBank();
		printNations();
	}
	*/
	@SuppressWarnings("unused")
	private void printNations(){
		System.out.print("Nations: ");
		for(Nation n:nations){
			System.out.print(n.toString());
		}
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	private void printObjectives(){
		for(Nation x:nations){
			System.out.println(x.toString());
			for(Objective o:x.getObjectives()){
				System.out.println("	"+o.toString());
	}}}
	/**
	 * Ends the turn of the current nation and goes to the next one
	 * Called when the end Turn button is pressed
	 */
	public void endTurn(){
		n.endTurn();//Adds the income to the bank of the nation that just ended its turn of play
		save(n.getPosition());
		resetUnitCounter();
	}
	/**
	 * Moves the counter to the next nation
	 * Sets the instance variables for the current nation to the new nation
	 */
	public void updateNation(){
		nation++;
		if(nation==nations.size()){
			nation=0;
			round++;
		//Skips the Pacific player if the British player ends turn with out buying any pacific units
		}else if(nations.get(nation).getName().contains("Pacific")){
			nations.get(nation).endTurn();
			save(nations.get(nation).getPosition());
			nation++;
		}
		
		n = nations.get(nation);
		oldBank = n.getBank();
	}
	/**
	 * Updates the current ecoNation
	 * @param change - moves the nation by this amount
	 */
	public void updateEcoNation(int change){
		ecoNation +=change;
		if(ecoNation>=nations.size()){
			ecoNation = 0;
		}else if(ecoNation < 0){
			ecoNation = nations.size()-1;
		}
		ecoN = nations.get(ecoNation);
	}
	/**
	 * Sets the ecoNation to the current nation
	 */
	public void changeEcoNationToNation(){
		ecoNation = nation;
		ecoN = nations.get(ecoNation);
	}
	/**
	 * Changes the bank of the current ecoNation
	 * @param change - how much to change it by
	 */
	public void changeEcoNationBank(int change){
		ecoN.changeBank(change);
	}
	/**
	 * Changes the income of the current ecoNation
	 * @param change - how much to change it by
	 */
	public void changeEcoNationIncome(int change){
		ecoN.changeIncome(change);
	}
	/**
	 * Saves the current game to the save file location
	 * called every time the end turn button is pressed
	 */
	private void save(int position){
		position+=2;
		if(position>nations.size()){
			position=1;
		}
	}
	/**
	 * Resets the count of every unit to zero
	 * Used when the end turn or reset unit buy buttons are pressed
	 */
	private void resetUnitCounter(){
		for(Unit u: units){
			u.resetCount();
		}
	}
	/**
	 * Buys and adds one to the unit count if a unit is bought
	 * Only buys a unit if the bank of the nation does not go below zero
	 * @param position - position in the list of units
	 */
	public void buyUnit(int position){
		if(n.getBank()-units.get(position).getCost()>=0){
			n.changeBank(-units.get(position).getCost());
			units.get(position).addOne();
		}
		if(ecoNation != nation){
			
		}
	}
	
	public void resetBuy(){
		resetUnitCounter();
		n.setBank(oldBank);
	}
	
	public void buyPacificUnits(){
		n.endTurn();
		nation++;
		n = nations.get(nation);
		changeEcoNationToNation();
	}
	
	public void natObjButtonPressed(int position){
		ecoN.getObjectives().get(position).changeEnabled();
	}
	
	public int getRound(){
		return round;
	}
	
	public int getNation(){
		return nation;
	}
	
	public int getEcoNation(){
		return ecoNation;
	}
	
	public List<Nation> getNations(){
		return nations;
	}
	
	public List<Unit> getUnits(){
		return units;
	}
	
	public List<Objective> getObjectives(){
		return ecoN.getObjectives();
	}
	
	public Nation getN(){
		return n.copy();
	}
	
	public Nation getEcoN(){
		return ecoN.copy();
	}
	
	public Nation getN(int position){
		return nations.get(position).copy();
	}
	
	public Nation getPreviousN(int position){
		int old = position -1;
		if (old == -1){
			old = nations.size()-1;
		}
		return nations.get(old).copy();
	}
	
	public Nation getNextN(int position){
		int newN = position +1;
		if(newN == nations.size()){
			newN = 0;
		}
		return nations.get(newN).copy();
	}
	
	public Unit getUnit(int position){
		return units.get(position).copy();
	}
	
	public Objective getObjective(int position){
		return ecoN.getObjectives().get(position).copy();
	}
}
