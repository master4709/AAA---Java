package myJStuff;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import util.ColorUtil;

public class MyButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Border defaultBorder = MyPanel.defaultBorder;
	
	public MyButton(ActionListener actionListener){
		addActionListener(actionListener);
		defaultButton();
	}

	public MyButton(ActionListener actionListener,String text){
		addActionListener(actionListener);
		setText(text);
		defaultButton();
	}
	
	public MyButton(ActionListener actionListener,ImageIcon icon){
		addActionListener(actionListener);
		setIcon(icon);
		setBorder(MyPanel.emptyBorder);
		setBorderPainted(false); 
		setContentAreaFilled(false); 
		setFocusPainted(false); 
		setOpaque(false);
	}
	
	public MyButton(ActionListener actionListener,int i){
		addActionListener(actionListener);
		setText(Integer.toString(i));
		defaultButton();
	}
	
	public MyButton(ActionListener actionListener,String text, int size){
		addActionListener(actionListener);
		setText(text);
		setFont(new MyFont(size));
		defaultButton();
		
	}
	
	public MyButton(ActionListener actionListener,int i, int size){
		addActionListener(actionListener);
		setText(Integer.toString(i));
		setFont(new MyFont(size));
		defaultButton();
		
	}
	
	public MyButton(ActionListener actionListener,String text, int size, Color c){
		addActionListener(actionListener);
		setText(text);
		setFont(new MyFont(size));
		setBackground(c);
		
	}
	
	private void defaultButton(){
		setForeground(ColorUtil.btnForegroundColor);
		setBackground(ColorUtil.btnBackgroundColor);
		setBorder(defaultBorder);
	}
}
