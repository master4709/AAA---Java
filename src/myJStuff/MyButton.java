package myJStuff;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class MyButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Border defaultBorder = MyPanel.defaultBorder;
	
	public MyButton(){
		defaultButton();
	}

	public MyButton(String text){
		setText(text);
		defaultButton();
	}
	
	public MyButton(ImageIcon icon){
		setIcon(icon);
		setBorder(MyPanel.emptyBorder);
	}
	
	public MyButton(int i){
		setText(Integer.toString(i));
		defaultButton();
	}
	
	public MyButton(String text, int size){
		setText(text);
		setFont(new MyFont(size));
		defaultButton();
		
	}
	
	public MyButton(int i, int size){
		setText(Integer.toString(i));
		setFont(new MyFont(size));
		defaultButton();
		
	}
	
	public MyButton(String text, int maxSize, int edgeDistance){
		setText(text);
		setFont(new MyFont(MyPanel.setFontSize(text, maxSize, edgeDistance)));
		defaultButton();
	}
	
	private void defaultButton(){
		setForeground(Colors.btnForegroundColor);
		setBackground(Colors.btnBackgroundColor);
		setBorder(defaultBorder);
	}
}
