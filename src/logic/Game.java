package logic;
import storage.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
	

	private int round;
	private int nation;
	private int ecoNation;
	
	private int oldBank;
	
	private Nation n;
	private Nation ecoN;
	
	private List<Nation> nations;
	private List<Unit> units;
	
	public Game(int nation, int round, List<Nation> nations, List<Unit> units){
		this.nation = nation;
		this.round = round;
		this.nations = nations;
		this.units = units;
		
		n = this.nations.get(nation);
		ecoN = this.nations.get(nation);
		
		oldBank = n.copy().getBank();
	}
	/**
	 * Adds the income of the current nation to its bank
	 */
	public void endTurn(){
		n.endTurn();
	}
	/**
	 * Finds the next nation in the list and sets it to the variable n
	 */
	public void nextNation(){
		nation++;
		if(nation==nations.size()){
			nation=0;
			round++;
		}
		ecoNation = nation;
		n = nations.get(nation);
		ecoN = nations.get(ecoNation);
		oldBank = n.copy().getBank();
		resetBuy();
	}
	/**
	 * Moves the ecoNation to the next element in the list
	 */
	public void nextEcoNation(){
		ecoNation++;
		if(ecoNation==nations.size()){
			ecoNation=0;
		}
		ecoN = nations.get(ecoNation);
	}
	/**
	 * Moves the ecoNation bank one nation
	 */
	public void previousEcoNation(){
		ecoNation--;
		if(ecoNation==-1){
			ecoNation = nations.size()-1;
		}
		ecoN = nations.get(ecoNation);
	}
	/**
	 * Change the bank of the current ecoNation
	 * @param amount - Integer for the change
	 */
	public void changeEcoNationBank(int amount){
		ecoN.changeBank(amount);
	}
	/**
	 * Change the income of the current ecoNation
	 * @param amount
	 */
	public void changeEcoNationIncome(int amount){
		ecoN.changeIncome(amount);
	}
	/**
	 * Enables/Disables the objective of the current eco nation
	 * @param position - Index value of objective in the list 
	 */
	public void objectiveBtnPressed(int position){
		ecoN.getObjectives().get(position).changeEnabled();
	}
	/**
	 * Sets the research of the nation to TRUE
	 * @param name - name of the nation
	 * @param position - position in list of research
	 * @return
	 */
	public Integer researchButtonPressed(String name, int position){
		int nation = -1;
		for(Nation n: nations){
			if(n.getName().contains(name)){
				nation = n.getPosition();
			}
		}
		nations.get(nation).getResearch().set(position, true);
		return nation;
	}
	/**
	 * Resets all of the research for all nations
	 * Puts them all to false
	 */
	public void resetResearch(int postition){
		for(int i=0;i<n.getResearch().size();i++){
			nations.get(postition).getResearch().set(i, false);
		}
	}
	/**
	 * Buys a unit for the specific nation
	 *  -adds one to the unit count
	 *  -remove amount from the bank
	 * @param position - index value of what unit to buy in the List<Unit>
	 */
	public void buyUnit(int position){
		int change = -units.get(position).getCost();
		if(n.getBank()+change>=0){
			units.get(position).addOne();
			n.changeBank(change);
		}
	}
	/**
	 * Resets the bank and the count of all unit for the current nation playing
	 */
	public void resetBuy(){
		for(Unit u: units) u.resetCount();
		n.setBank(oldBank);
	}
	/**
	 * Adds the income to the bank of the nation
	 * who is before the Pacific 
	 */
	public void endTurnPacificMain(){
		for(Nation n: nations){
			if(n.getName().contains("Pacific")){
				nations.get(n.getPosition()-1).endTurn();
			}
		}
	}
	
	/**
	 * Get the current round
	 * @return Integer
	 */
	public int getRound(){
		return round;
	}
	/**
	 * @return Integer - current nation
	 */
	public int getNation(){
		return nation;
	}
	/**
	 * @return Nation - current nation
	 */
	public Nation getN(){
		return n.copy();
	}
	/**
	 * @param position - Integer -  position in the list
	 * @return Nation - 
	 */
	public Nation getN(int position){
		return nations.get(position).copy();
	}
	/**
	 * @return Nation - nation before the current ecoNation
	 */
	public Nation getPreN(){
		int nat = nation-1;
		if(nat==-1){
			nat=nations.size()-1;
		}
		return nations.get(nat).copy();
	}
	/**
	 * @return Nation - nation after the current ecoNation
	 */
	public Nation getNxtN(){
		int nat = nation+1;
		if(nat==nations.size()){
			nat=0;
		}
		return nations.get(nat).copy();
	}
	/**
	 * @return Nation - current ecoNation
	 */
	public Nation getEcoN(){
		return ecoN.copy();
	}
	/**
	 * @return Nation - nation before the current ecoNation
	 */
	public Nation getPreEcoN(){
		int nation = ecoNation-1;
		if(nation==-1){
			nation=nations.size()-1;
		}
		return nations.get(nation).copy();
	}
	/**
	 * @return Nation - nation after the current ecoNation
	 */
	public Nation getNxtEcoN(){
		int nation = ecoNation+1;
		if(nation==nations.size()){
			nation=0;
		}
		return nations.get(nation).copy();
	}
	/**
	 * 
	 * @return List<Nation> - list of all of the nations
	 */
	public List<Nation> getNations(){
		List<Nation> clone = new ArrayList<>();
		for(Nation n: nations) clone.add(n.copy());
		return clone;
	}
	
	public Unit getUnit(int position){
		return units.get(position).copy();
	}

}
