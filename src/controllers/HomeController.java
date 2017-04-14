package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private ContinuePanel cp;
	private NewPanel np;
	private CreateGamePanel cgp;
	private AboutPanel ap;
	
	private JPanel panelHome;
	private JPanel panelContinue;
	private JPanel panelNew;
	private JPanel panelCreateGame;
	private JPanel panelAbout;
	
	private int selection;
	
	private int nationTotal = 0;//This means there is 1 nation. (-1 is no nations)
	
	//String value of the location of the folder being opened ( Both Save and Load game )
	private String loadFolder;
	
	public HomeController(JFrame frame, ActionListener globalListener){
		this.frame = frame;
		this.globalListener = globalListener;
	}
	
	public void start(){
		setFrameBounds();
		
		hp = new HomePanel(this);
		cp = new ContinuePanel(this,this.globalListener);
		np = new NewPanel(this,this.globalListener);
		cgp = new CreateGamePanel(this);
		ap = new AboutPanel(this);
		
		panelHome = hp.getContentPane();
		panelNew = np.getContentPane();
		panelContinue = cp.getContentPane();
		panelCreateGame = cgp.getContentPane();
		panelAbout = ap.getContentPane();
		
		nationTotal = 0;
		
		cgp.createNation(nationTotal);
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
		np.clear();
		np.displayCenter(folders("data/newGame"));
		switchPanel(panelNew);
	}
	
	private void switchContinuePanel(){
		cp.clear();
		cp.displayCenter(folders("data/saveFiles"));
		switchPanel(panelContinue);
	}
	
	private void switchPanel(JPanel panel){
		System.out.println("SWITCHING: "+panel.getName());
		frame.setTitle("M - Axis and Allies | "+panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	/**
	 * Returns the selection for what button was pressed when loading a save game
	 * @return selection - Integer
	 */
	public Integer getSelection(){
		return selection;
	}
	
	public String getNewGameInput(){
		return np.getInput();
	}
	
	public String getGameFolder(){
		return loadFolder;
	}
	
	public void resetButtons(){
		cp.setBtnSelected(-1);;
	}
	/**
	 * Finds all of the files (folders) in the specific direcotry
	 * Used for newPanel and loadPanel
	 * @param directory - location to search 
	 * @return - List<String> folderNames
	 */
	public List<String> folders(String directory) {
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
			switchContinuePanel();
			break;
		case"CreateGame":
			switchPanel(panelCreateGame);
			break;
		case"About":
			switchPanel(panelAbout);
			break;
		case"Back":
			selection=-1;
			switchPanel(panelHome);
			break;
		case"Add":
			nationTotal++;
			cgp.createNation(nationTotal);
			frame.repaint();
			break;
		case"Remove":
			nationTotal--;
			if(nationTotal<0){
				nationTotal=0;
			}else{
				cgp.removeNation();
			}
			
			frame.repaint();
			break;
		case"Reset":
			nationTotal = 0;
			cgp.reset();
			frame.repaint();
			break;
		default:
			if(name.contains("New")){
				selection = Integer.parseInt(name.substring(4, name.length()));
				np.setBtnSelected(selection);
				loadFolder = source.getText();
			}else if(name.contains("Continue")){
				selection = Integer.parseInt(name.substring(9, name.length()));
				cp.setBtnSelected(selection);
				loadFolder = source.getText();
			}else if(name.contains("Objective")){
				System.out.println(name);
			}
			
			break;
		}
	}
}
