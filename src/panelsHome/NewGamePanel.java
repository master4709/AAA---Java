package panelsHome;
import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewGamePanel extends MyPanel{

	private ActionListener globalListener;
	
	private JLabel lblTitle;
	
	private JTextField fldInput;
	
	private JButton btnBack;
	private JButton btnContinue;
	
	private List<JButton> saveFolders = new ArrayList<>();
	
	
	public NewGamePanel(ActionListener packageListener, ActionListener globalListener){
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		contentPane.setName("New Game");
		
		displayNorth();
		displaySouth();
	}
	
	
	private void displayNorth(){
		lblTitle = new MyLabel("New Game",nationFontSize*3/2);
		north.add(lblTitle,"cell 0 0");
	}
	
	public void displayCenter(List<String> folders){
		int buttonSize = getScreenWidth();
		center.setLayout(new MigLayout("", "["+buttonSize+"]", "[]"));
		int y =0;
		int position=0;
		int x = 0;
		for(String s: folders){
			JButton btn = new MyButton(s,btnFontSize*3/5);
			center.add(btn,"cell "+x+" "+y+",growx");
			btn.setName("NewGame_"+position);
			btn.addActionListener(packageListener);
			saveFolders.add(btn);
			x++;
			if(x==3){
				x=0;
				y++;
			}
			position++;
		}
	}
	
	private void displaySouth(){
		btnBack = new MyButton("Back", btnFontSize);
		south.add(btnBack,"cell 0 0, alignx left");
		btnBack.setName("Back");
		btnBack.addActionListener(packageListener);
		
		fldInput = new MyTextField(8, btnFontSize);
		south.add(fldInput,"cell 1 0, alignx center");
		
		btnContinue = new MyButton("Continue", btnFontSize);
		south.add(btnContinue, "cell 2 0,alignx right");
		btnContinue.setName("Continue_New");
		btnContinue.addActionListener(globalListener);
	}
	
	public void clear(){
		for(JButton btn:saveFolders){
			center.remove(btn);
		}
		saveFolders = new ArrayList<>();
	}
	
	public String getInput(){
		String text = fldInput.getText();
		fldInput.setText("");
		return text;
	}
	
	public void setBtnSelected(int target){
		for(JButton button: saveFolders){
			if(button.getName().contains(Integer.toString(target))){
				button.setBackground(Colors.btnSelectColor);
			}else{
				button.setBackground(Colors.btnBackgroundColor);
			}
		}
	}
}
