package util;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ColorUtil2 {
	
	public static final Color black = new Color(30,30,30);
	public static final Color light_black = new Color(100,100,100);
	
	public static final Color red = new Color(134,33,33);
	public static final Color light_red = new Color(184,83,83);
	
	public static final Color orange = new Color(181,100,40);
	public static final Color light_orange = new Color(221,150,90);
	
	public static final Color yellow = new Color(200,200,102);
	public static final Color light_yellow = new Color(250,250,150);
	
	public static final Color green = new Color(90,133,50);
	public static final Color light_green = new Color(140,183,100);
	
	public static final Color green_light = new Color(161,183,69);
	public static final Color light_green_light = new Color(211,233,119);
	
	public static final Color purple = new Color(100,50,188);
	public static final Color light_purple = new Color(105,87,238);
	
	public static final Color tan = new Color(212,173,128);
	public static final Color light_tan = new Color(255,223,178);
	
	public static final Color tan_dark = new Color(135,116,109);
	public static final Color light_tan_dark = new Color(185,166,159);
	
	public static final Color pink = new Color(201,82,171);
	public static final Color light_pink = new Color(255,145,235);
	
	public static final Color brown = new Color(117,65,0);
	public static final Color light_brown = new Color(167,115,50);
	
	public static final Color grey = new Color(125,125,125);
	public static final Color light_grey = new Color(175,175,175);
	
	public static final Color blue = new Color(62,109,145);
	public static final Color light_blue = new Color(112,159,195);
	
	public static final Color blue_light = new Color(100,170,200);
	public static final Color light_blue_light = new Color(150,225,250);
	
	public static final Color white = new Color(240,240,240);
	public static final Color light_white = new Color(200,200,200);
	
	public static final Color backgroundColor = new Color(85,85,85);
	public static final Color btnBackgroundColor = new Color(230,200,160);
	public static final Color btnForegroundColor = new Color (10,10,10);
	public static final Color btnSelectColor = new Color(150,190,255);
	
	public static final Map<Color, String> colorMap = new HashMap<Color, String>();
	
	static{
		colorMap.put(ColorUtil2.red, "Red");
		colorMap.put(ColorUtil2.orange, "Orange");
		colorMap.put(ColorUtil2.yellow, "Yellow");
		colorMap.put(ColorUtil2.pink, "Pink");
		colorMap.put(ColorUtil2.purple, "Purple");
		colorMap.put(ColorUtil2.blue, "Blue");
		colorMap.put(ColorUtil2.blue_light, "Blue_L");
		colorMap.put(ColorUtil2.green, "Green");
		colorMap.put(ColorUtil2.green_light, "Green_L");
		colorMap.put(ColorUtil2.brown, "Brown");
		colorMap.put(ColorUtil2.tan_dark, "Tan_D");
		colorMap.put(ColorUtil2.tan, "Tan");
		colorMap.put(ColorUtil2.black, "Black");
		colorMap.put(ColorUtil2.grey, "Grey");
		colorMap.put(ColorUtil2.white, "White");
	}
	
	public static Color getColor(String color){
		Color c;
		try{
			Field field = ColorUtil2.class.getField(color);
			c = (Color)field.get(null);
		} catch (Exception e){
			System.out.println("No Color found with name: "+color);
			c = null;
		}
		return c;
	}
	
	public static Color getColorLight(String color){
		Color c;
		try{
			Field field = ColorUtil2.class.getField("light_"+color);
			c = (Color)field.get(null);
		} catch (Exception e){
			System.out.println("No Color found with name: "+color);
			c = null;
		}
		return c;
	}
}
