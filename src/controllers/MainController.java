package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class MainController implements ActionListener{
	//Create the frame
	private JFrame frame;
	//Create Instance of all Controller Classes
	private HomeController mc;
	private GameController gc;
	
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
		mc = new HomeController(frame,this);
		gc = new GameController(frame,this);
		
		startHome();
	}
	
	private void startHome(){
		mc.start();
	}
	
	private void startGame(){
		gc.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		case"Continue_New":
			startGame();
			break;
		case"Continue_Load":
			startGame();
			break;
		case"Home_Game":
			break;
		}
	}
}
