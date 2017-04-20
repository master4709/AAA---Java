package myJStuff;

import java.awt.Font;

public class MyFont extends Font{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFont(int size) {
		super("Cambria", Font.PLAIN, size);
	}
	
	public MyFont(int size, int bold) {
		super("Cambria", bold, size);
	}

}
