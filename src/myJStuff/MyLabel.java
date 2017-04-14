package myJStuff;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyLabel(String text, int size){
		setText(text);
		setFont(new MyFont(size));
	}
	
	public MyLabel(Integer i, int size){
		setText(Integer.toString(i));
		setFont(new MyFont(size));
	}
		
	public MyLabel(ImageIcon icon){
		setIcon(icon);
	}
}
