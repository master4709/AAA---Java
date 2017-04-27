package util;

import storage.*;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
public class LoadGameUtil{
	//private String folder;
	private String folder;
	private File nationFolder;
	private File unitFile;
	private File researchFile;
	private File currentFile;
	
	//Stores String values for every word (Defined as a space between two characters) on the current line
	private List<String> line = new ArrayList<>();
	private List<String> nationNames = new ArrayList<>();

	private List<Nation> nations = new ArrayList<>();
	private List<Unit> units = new ArrayList<>();
	private List<Research> research = new ArrayList<>();
	
	private int nation = 0;
	private int round = 1;
	private boolean firstUnit = true;
	
	
	public LoadGameUtil(String folder){
		this.folder = folder;
		this.nationFolder = new File(folder+"/nations/");
		this.unitFile = new File(folder+"/units.txt");
		this.researchFile = new File(folder+"/research.txt");
		this.currentFile = new File(folder+"/current nation.txt");
		
		loadInfo();
	}
	
	public void copyFolder(String newFolder){
		File source = new File(folder);
		File dest = new File(newFolder);
		try {
		    FileUtils.copyDirectory(source, dest);
		    System.out.println("COPYING: game data to - "+dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void loadInfo(){
		scanNationFolder();
		scanNations();
		scanUnits();
		scanResearch();
		scanCurrent();
	}
	/**
	 * Finds all of the nation files in the 
	 */
	public void scanNationFolder() {
		String text="";
		System.out.println("READING: nation data from - "+nationFolder);
		for(File file: nationFolder.listFiles()) {
			if(file.getName().contains(".txt")){
				text = file.getName();
				text = text.substring(0,text.length()-4);
				nationNames.add(text);
				nations.add(null);
			}
		}
	}
	
	public void scanNations(){
		File nat;
		List<List<String>> nationData;
		int position = 0;
		for(String name: nationNames){
			nationData = new ArrayList<>();
			nat = new File(nationFolder+"/"+name+".txt");
			try (Scanner scan = new Scanner(nat);){
				while(scan.hasNextLine()){
					String currentLine = scan.nextLine();
					Scanner lineS = new Scanner(currentLine);
					line = new ArrayList<>();
					while(lineS.hasNext()){
						String str = lineS.next();
						line.add(str);
					}
					lineS.close();
					line.remove(0);
					nationData.add(line);
				}
				scan.close();
				createNation(nationData,position);
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: could not load/find file - "+nat);
			}
			
			position++;
		}
	}
	
	private void createNation(List<List<String>> nationData, int place){
		Nation n;
		int position = Integer.parseInt(nationData.get(0).get(0))-1;
		Color color = ColorUtil.getColor(nationData.get(1).get(0));
		Color light = ColorUtil.getColorLight(nationData.get(1).get(0));
		int bank = Integer.parseInt(nationData.get(2).get(0));
		int income = Integer.parseInt(nationData.get(3).get(0));
		String name = nationNames.get(place);
		List<Boolean> research = getResearch(nationData.get(4));
		List<Objective> objectives = getObjectives(nationData);
		n = new Nation(name,color,light,position,bank,income,objectives,research);
		nations.set(position, n);
	}
	
	private void scanUnits(){
		List<String> unitData;
		int position = 0;
		try(Scanner scan = new Scanner(unitFile);) {
			System.out.println("READING: unit data from - "+unitFile);
			while (scan.hasNextLine()){
				String currentLine = scan.nextLine();
				Scanner lineScanner = new Scanner(currentLine);
				unitData = new ArrayList<>();
				while(lineScanner.hasNext()){
					String str = lineScanner.next();
					unitData.add(str);
				}
				if(firstUnit){//Skips the first line of the unit file
					firstUnit = false;
				}else{
					createUnit(unitData,position);
					position++;
				}
				
				lineScanner.close();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: could not load/find file - "+unitFile);
			e.printStackTrace();
		}
	}
	
	private void createUnit(List<String> unitData, int position){
		int attack = Integer.parseInt(unitData.get(0));
		int defense = Integer.parseInt(unitData.get(1));
		int movement = Integer.parseInt(unitData.get(2));
		int cost = Integer.parseInt(unitData.get(3));
		String name = unitData.get(4);
		Unit u = new Unit(attack,defense,movement,cost,position,name);
		units.add(u);
	}
	
	private void scanResearch(){
		int position = 0;
		try(Scanner scan = new Scanner(researchFile);) {
			System.out.println("READING: research data from - "+researchFile);
			while (scan.hasNextLine()){
				String line = scan.nextLine();
				String[] split = line.split("\\|");
				research.add(new Research(split[0],split[1],position));
				position++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Could not load/find - "+researchFile);
		}
	}
	
	private void scanCurrent(){
		List<String> currentData = new ArrayList<>();
		try(Scanner scan = new Scanner(currentFile);) {
			System.out.println("READING: current nations data from - "+currentFile);
			while (scan.hasNextLine()){
				String currentLine = scan.nextLine();
				Scanner lineScanner = new Scanner(currentLine);
				while(lineScanner.hasNext()){
					String str = lineScanner.next();
					currentData.add(str);
				}
				lineScanner.close();
			}
			nation = Integer.parseInt(currentData.get(1))-1;
			round = Integer.parseInt(currentData.get(3));
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: could not load/find file - "+currentFile);
			nation = 0;
			round = 1;
		}
	}
	
	private List<Objective> getObjectives(List<List<String>> nationData){
		List<Objective> objectives = new ArrayList<>();
		int position = 0;
		//Loops through the remaining lines in the nation txt file and reads them as objectives
		for(int i=5;i<nationData.size();i++){
			boolean enabled = Boolean.valueOf(nationData.get(i).get(0));
			int amount = Integer.parseInt(nationData.get(i).get(1));
			String s="";
			for(int k=2;k<nationData.get(i).size();k++){
				s += nationData.get(i).get(k) + " ";
			}
			Objective o = new Objective(amount,position,s,enabled);
			objectives.add(o);
			position++;
		}
		return  objectives;
	}
	
	private List<Boolean> getResearch(List<String> chart){
		List<Boolean> research = new ArrayList<>();
		for(String s: chart){
			research.add(Boolean.valueOf(s));
		}
		return research;
	}
	

	public int getNation(){
		return nation;
	}
	public int getRound(){
		return round;
	}
	public List<Nation> getNations(){
		return nations;
	}
	public List<Unit> getUnits(){
		return units;
	}
	public List<Research> getResearch(){
		return research;
	}
}
