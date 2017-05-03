package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import util.*;
/**
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class MainController implements ActionListener{
	//Create the frame
	private JFrame frame;
	//Create Instance of all Controller Classes
	private HomeController hc;
	private GameController gc;
	private LoadGameUtil lgu = new LoadGameUtil();;
	
	/**
	 * Constructor
	 */
	public MainController() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 
	 */
	public void run(){
		hc = new HomeController(frame,this);
		gc = new GameController(frame,this);
		hc.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		case"Continue_NewGame":
			String saveFolder = "data/loadGame/"+hc.getNewGameInput();
			//Checks if a a game folder has been selected
			if(!hc.getGameFolder().equals("")){
				String copyFolder = "data/newGame/"+hc.getGameFolder();
				if(saveFolder.equals("data/loadGame/")){
					saveFolder+=hc.getGameFolder();
				}
				lgu.copyFolder(copyFolder,saveFolder);
				gc.start(lgu,lgu.getNation(),lgu.getRound(),lgu.getNations(),lgu.getUnits(),lgu.getResearch());
			}
			break;
		case"Continue_LoadGame":
			String loadFolder = "data/loadGame/"+hc.getGameFolder();
			if(!hc.getGameFolder().equals("")){
				lgu.loadFolder(loadFolder);
				gc.start(lgu,lgu.getNation(),lgu.getRound(),lgu.getNations(),lgu.getUnits(),lgu.getResearch());
			}
			break;
		case"Home_Game":
			hc.start();
			break;
		}
	}
}
