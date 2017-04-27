package util;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import myJStuff.MyFont;

public class FontSizeUtil {
	
	
	/**
	 * Sets the font size for the current lbl or button
	 * Ensures that the button does not proceed the size of the screen
	 * @param text - String displayed on lbl or button
	 * @param maxSize - maximum size of the font
	 * @param maxWidth - maximum length in pixels of the string
	 * @return Integer - size of the font
	 */
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
