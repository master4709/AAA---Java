package myJStuff;

import javax.swing.JTextField;

public class MyTextField extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTextField(int c, int size){
		setColumns(c);
		setFont(new MyFont(size));
	}
	
	public MyTextField(int size){
		setFont(new MyFont(size));
	}
	
	public MyTextField(String text, int size){
		setText(text);
		setFont(new MyFont(size));
	}

}
