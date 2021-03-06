package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

import myJStuff.MyPanel;
import panelsHome.*;
/**
 * This class controls the different 
 * @author pierc
 *
 */
public class HomeController implements ActionListener{
	
	private JFrame frame;
	
	private ActionListener globalListener;
	
	private HomePanel hp;
	private LoadGamePanel lgp;
	private NewGamePanel ngp;
	private EditGamePanel egp;
	private CreateObjectivesPanel cop;
	private AboutPanel ap;
	
	private JPanel panelHome;
	private JPanel panelLoadGame;
	private JPanel panelNewGame;
	private JPanel panelEditGame;
	private JPanel panelCreateObjectives;
	private JPanel panelAbout;
	
	private int selection;
	
	private int nationTotal = 0;//This means there is 1 nation. (-1 is no nations)
	
	//String value of the location of the folder being opened ( Both Save and Load game )
	private String gameFolder;
	

	private final static String newFolder = "data/newGame/";
	private final static String loadFolder = "data/loadGame/";
	
	public HomeController(JFrame frame, ActionListener globalListener){
		this.frame = frame;
		this.globalListener = globalListener;
	}
	
	public void start(){
		setFrameBounds();
		
		hp = new HomePanel(this);
		lgp = new LoadGamePanel(this,this.globalListener);
		ngp = new NewGamePanel(this,this.globalListener);
		egp = new EditGamePanel(this);
		cop = new CreateObjectivesPanel(this);
		ap = new AboutPanel(this);
		
		panelHome = hp.getContentPane();
		panelNewGame = ngp.getContentPane();
		panelLoadGame = lgp.getContentPane();
		panelEditGame = egp.getContentPane();
		panelCreateObjectives = cop.getContentPane();
		panelAbout = ap.getContentPane();
		
		nationTotal = 0;
		selection = -1;
		
		egp.createNation(nationTotal);
		cop.addNObjective();
		switchPanel(panelHome);
		
	}
	/**
	 * Sets the bounds of the frame every time the MainController switches to the HomeControlle.
	 */
	private void setFrameBounds(){
		frame.setBounds(MyPanel.getScreenWidth()/3, MyPanel.getScreenHeight()/4, MyPanel.getScreenWidth()/3, 600);
		frame.setResizable(false);
	}
	
	private void switchNewPanel(){
		gameFolder = "";
		ngp.clear();
		ngp.displayCenter(folders(newFolder));
		switchPanel(panelNewGame);
	}
	
	private void switchLoadPanel(){
		gameFolder = "";
		lgp.clear();
		lgp.displayCenter(folders(loadFolder));
		switchPanel(panelLoadGame);
	}
	
	private void switchPanel(JPanel panel){
		System.out.println("SWITCHING: "+panel.getName()+" Panel");
		frame.setTitle("M - Axis and Allies | "+panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	
	public String getNewGameInput(){
		return ngp.getInput();
	}
	
	public String getGameFolder(){
		return gameFolder;
	}
	/**
	 * Finds all of the files (folders) in the specific direcotry
	 * Used for newPanel and loadPanel
	 * @param directory - location to search 
	 * @return - List<String> folderNames
	 */
	private List<String> folders(String directory) {
		List<String> saveFolders = new ArrayList<>();
		File dir = new File(directory);
		for (File file : dir.listFiles()) {
				String text = file.getName();
				saveFolders.add(text);
		}
		return saveFolders;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		switch(name){
		case"NewGame":
			switchNewPanel();
			break;
		case"ContinueGame":
			switchLoadPanel();
			break;
		case"EditGame":
			switchPanel(panelEditGame);
			break;
		case"About":
			switchPanel(panelAbout);
			break;
		case"Back":
			selection=-1;
			switchPanel(panelHome);
			break;
		case"Delete_NewGame":
			if(selection!=-1){
				File dir = new File(newFolder+folders(newFolder).get(selection));
				try {
					System.out.println("DELETE: "+dir);
					FileUtils.deleteDirectory(dir);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				switchNewPanel();
			}
			break;
		case"Delete_LoadGame":
			if(selection!=-1){
				File dir = new File(loadFolder+folders(loadFolder).get(selection));
				try {
					System.out.println("DELETE: "+dir);
					FileUtils.deleteDirectory(dir);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				switchLoadPanel();
			}
			break;
		case"Add_CreateGame":
			nationTotal++;
			egp.createNation(nationTotal);
			cop.addNObjective();
			frame.repaint();
			break;
		case"Reset_CreateGame":
			nationTotal = 0;
			egp.reset();
			frame.repaint();
			break;
		case"Add_CreateObjectives":
			break;
		case"Back_CreateObjectives":
			switchPanel(panelEditGame);
			break;
		case"Reset_ObjectiveCreate":
			break;
		default:
			if(name.contains("NewGame_")){
				selection = Integer.parseInt(name.substring(8, name.length()));
				ngp.setBtnSelected(selection);
				gameFolder = source.getText();
			}else if(name.contains("LoadGame_")){
				selection = Integer.parseInt(name.substring(9, name.length()));
				lgp.setBtnSelected(selection);
				gameFolder = source.getText();
			}else if(name.contains("Objective_")){
				selection = Integer.parseInt(name.substring(10, name.length()));
				cop.setNation(egp.getNName(selection), egp.getNColor(selection), selection);
				switchPanel(panelCreateObjectives);
			}else if(name.contains("Remove_CreateGame_")){
				nationTotal--;
				if (nationTotal == -1){
					nationTotal = 0;
				}else{
					selection = Integer.parseInt(name.substring(18, name.length()));
					System.out.println("Nation Removed: " +(selection));
					System.out.println("Nation Total: " +(nationTotal));
					egp.removeNation(selection);
					cop.removeNObjective(selection);
					frame.validate();
					frame.repaint();
				}
			}else if(name.contains("Remove_CreateObjectives")){
				
			}
			break;
		}
	}
}
