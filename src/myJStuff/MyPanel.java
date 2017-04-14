package myJStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class MyPanel{

	protected ActionListener packageListener;
	
	protected JPanel contentPane;
	
	protected JPanel north;
	protected JPanel south;
	protected JPanel west;
	protected JPanel east;
	protected JPanel center;
	
	protected static final Border emptyBorder = new EmptyBorder(1,1,1,1);
	protected static final Border defaultBorder = new EmptyBorder(3,3,3,3);
	
	protected int nationFontSize;
	protected int nationInfoFontSize;
	protected int unitFontSize;
	protected int btnFontSize;
	
	protected final String source = "src/resources/";
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int screenWidth = (int) screenSize.getWidth();
	private static final int screenHeight = (int) screenSize.getHeight();

	public MyPanel(){
		
		setFont();
		setTheme();
		
		contentPane = new JPanel();

		contentPane.setBorder(defaultBorder);
		//contentPane.setBorder();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setPreferredSize(new Dimension(750,500));
		//These panels are what all JLabels, buttons etc. are added to
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("gapy 1", "[grow,center]", "[]0[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("gapy 1","",""));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("gapy 1","",""));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("insets 0,gapy 0", "[grow,center]", "[]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("gapy 1", "[][][grow]", "[]0[]"));
		
		//Once panels are set, background color is then set
		setMainBackground(Colors.backgroundColor);
	}
	
	// Default sizes for certain font templates
	private void setFont(){
		nationFontSize = 70;
		nationInfoFontSize = 50;
		unitFontSize = 20;
		btnFontSize = 40;
	}
	
	// Ensure the background color for all panels are black
	public void setMainBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		contentPane.setBackground(c);
	}
	
	private void setTheme(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public static int getScreenWidth(){
		return screenWidth;
	}
	
	public static int getScreenHeight(){
		return screenHeight;
	}
	
	/**
	 * Sets the font size for the current lbl or button
	 * Ensures that the button does not proceed the size of the screen
	 * @param text - String displayed on lbl or button
	 * @param maxSize - Maximum Int size for the lbl or button
	 * @param maxWidth - Int value of space from edge of screen for text
	 * @return int -  value of font size for lbl or button
	 */
	protected static int setFontSize(String text, int maxSize, int maxWidth){
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
