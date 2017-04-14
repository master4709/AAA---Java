package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import panelsGame.*;
import storage.*;

public class GameController implements ActionListener{
	
	private JFrame frame;
	
	private ActionListener globalListener;
	
	private GamePanel gp;
	
	private JPanel panelGame;
	
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
	
	public void start(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
