package panelsHome;
import myJStuff.*;
import net.miginfocom.swing.MigLayout;
import util.ColorUtil;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class LoadGamePanel extends MyPanel{
	
	private ActionListener globalListener;
	
	private JLabel lblTitle;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnContinue;
	private List<JButton> loadFolders = new ArrayList<>();
	
	public LoadGamePanel(ActionListener packageListener, ActionListener globalListener){
		this.globalListener = globalListener;
		this.packageListener = packageListener;
		contentPane.setName("LoadGame");
		displayNorth();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("LoadGame",nationFontSize*3/2);
		north.add(lblTitle,"cell 0 0");
	}
	
	public void displayCenter(List<String> folders){
		int buttonSize = getScreenWidth();
		center.setLayout(new MigLayout("", "["+buttonSize+"]", "[]"));
		int y =0;
		int position=0;
		int x = 0;
		for(String s: folders){
			JButton btn = new MyButton(packageListener,s,btnFontSize*3/5);
			center.add(btn,"cell "+x+" "+y+",growx");
			btn.setName("LoadGame_"+position);
			loadFolders.add(btn);
			x++;
			if(x==2){
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
		btnBack = new MyButton(packageListener,"Back", btnFontSize);
		south.add(btnBack,"cell 0 0");
		btnBack.setName("Back");
		
		btnDelete  = new MyButton(packageListener,"Delete",btnFontSize);
		south.add(btnDelete,"cell 1 0,alignx left");
		btnDelete.setName("Delete_LoadGame");
		
		btnContinue = new MyButton(globalListener,"Continue", btnFontSize);
		south.add(btnContinue, "cell 2 0,alignx right");
		btnContinue.setName("Continue_LoadGame");
		
	}
	
	public void setBtnSelected(int target){
		for(JButton button: loadFolders){
			if(button.getName().contains(Integer.toString(target))){
				button.setBackground(ColorUtil.btnSelectColor);
			}else{
				button.setBackground(ColorUtil.btnBackgroundColor);
			}
		}
	}
}
