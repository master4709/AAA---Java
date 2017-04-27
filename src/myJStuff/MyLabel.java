package myJStuff;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyLabel(){
		
	}
	
	public MyLabel(String text, Integer size){
		setText(text);
		setFont(new MyFont(size));
	}
	
	public MyLabel(Integer i, Integer size){
		setText(Integer.toString(i));
		setFont(new MyFont(size));
	}
	
	public MyLabel(String text, Integer size, Integer font){
		setText(text);
		setFont(new MyFont(size,font));
	}
	
	public MyLabel(Integer i, Integer size, Integer font){
		setText(Integer.toString(i));
		setFont(new MyFont(size,font));
	}
	
	public MyLabel(String text, Integer size, Color c){
		setText(text);
		setFont(new MyFont(size));
		setForeground(c);
	}
	
	public MyLabel(Integer i, Integer size, Color c){
		setText(Integer.toString(i));
		setFont(new MyFont(size));
		setForeground(c);
	}
	
	public MyLabel(String text, Integer size, Color c, Integer font){
		setText(text);
		setFont(new MyFont(size,font));
		setForeground(c);
	}
	public MyLabel(Integer i, Integer size, Color c, Integer font){
		setText(Integer.toString(i));
		setFont(new MyFont(size,font));
		setForeground(c);
	}
		
	public MyLabel(ImageIcon icon){
		setIcon(icon);
	}
}
