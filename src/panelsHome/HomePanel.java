package panelsHome;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;



public class HomePanel extends MyPanel{

	private JLabel title;
	
	private JButton btnNewGame;
	private JButton btnContinueGame;
	private JButton btnCreateGame;
	private JButton btnAbout;
	
	private ImageIcon mainImage;
	
	private String titlePath = "src/resources/images/mainImage.png";
	
	public HomePanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("MainPanel");
		
		displayNorth();
		displayCenter();
	}
	
	
	private void displayNorth(){
		//Creates the Image and add it to the north panel
		mainImage = new ImageIcon(titlePath);
		if(mainImage==null)System.out.println("ERROR: No Picture found in " + titlePath);
		title = new MyLabel(mainImage);
		north.add(title,"cell 0 0");
	}
	
	private void displayCenter(){
		center.setLayout(new MigLayout("", "[200][grow][200]", "[]"));
		
		btnNewGame = new MyButton("New Game", btnFontSize);
		center.add(btnNewGame,"cell 1 0,growx");
		btnNewGame.setName("NewGame");
		btnNewGame.addActionListener(packageListener);
		
		btnContinueGame = new MyButton("Continue Game", btnFontSize);
		center.add(btnContinueGame,"cell 1 1,growx");
		btnContinueGame.setName("ContinueGame");
		btnContinueGame.addActionListener(packageListener);
		
		btnCreateGame = new MyButton("Create Game", btnFontSize);
		center.add(btnCreateGame,"cell 1 2,growx");
		btnCreateGame.setName("CreateGame");
		btnCreateGame.addActionListener(packageListener);
		
		btnAbout = new MyButton("About", btnFontSize);
		center.add(btnAbout,"cell 1 3,growx");
		btnAbout.setName("About");
		btnAbout.addActionListener(packageListener);
		
	}
}