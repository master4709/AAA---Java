package myJStuff;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class MyFont extends Font{

	/**
	 * 
	 */
	private static final String font = "Cambria";	
	private static final long serialVersionUID = 1L;

	public MyFont(int size) {
		super(font, Font.PLAIN, size);
	}
	
	public MyFont(int size, int bold) {
		super(font, bold, size);
	}
	
	public MyFont(String text, int maxSize, int maxWidth){
		super(font,Font.PLAIN,setFontSize(text,maxSize,maxWidth));
	}
	
	public static int setFontSize(String text, int maxSize, int maxWidth){
		int font = 5;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		
		while(textWidth<maxWidth && font<maxSize){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		}
		return font;
	}

}
