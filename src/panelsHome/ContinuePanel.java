package panelsHome;
import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ContinuePanel extends MyPanel{
	
	private ActionListener globalListener;
	
	private JLabel lblTitle;
	private JButton btnBack;
	private JButton btnContinue;
	private List<JButton> loadFolders = new ArrayList<>();
	
	public ContinuePanel(ActionListener packageListener, ActionListener globalListener){
		this.globalListener = globalListener;
		this.packageListener = packageListener;
		contentPane.setName("LoadPanel");
		displayNorth();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("Load Game",nationFontSize*3/2);
		north.add(lblTitle,"cell 0 0");
	}
	
	public void displayCenter(List<String> folders){
		center.setLayout(new MigLayout("", "[250][250][250]", "[]"));
		int y =0;
		int position=0;
		int x = 0;
		for(String s: folders){
			JButton btn = new MyButton(s,btnFontSize*3/4);
			center.add(btn,"cell "+x+" "+y+",growx");
			btn.setName("Continue_"+position);
			btn.addActionListener(packageListener);
			loadFolders.add(btn);
			x++;
			if(x==3){
				x=0;
				y++;
			}
			position++;
		}
	}
	
	public void clear(){
		for(JButton btn:loadFolders){
			center.remove(btn);
		}
		loadFolders = new ArrayList<>();
	}
	
	private void displaySouth(){
		btnBack = new MyButton("Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
		btnBack.addActionListener(packageListener);
		
		btnContinue = new MyButton("Continue", btnFontSize);
		south.add(btnContinue, "cell 1 0,alignx right");
		btnContinue.setName("Continue_Load");
		btnContinue.addActionListener(globalListener);
		
	}
	
	public void setBtnSelected(int target){
		for(JButton button: loadFolders){
			if(button.getName().contains(Integer.toString(target))){
				button.setBackground(Colors.btnSelectColor);
			}else{
				button.setBackground(Colors.btnBackgroundColor);
			}
		}
	}
}
