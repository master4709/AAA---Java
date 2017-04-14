package storage;

import java.io.Serializable;

public class Objective implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int amount;
	
	private String text;
	
	private boolean enabled;
	
	private int position;
	
	public Objective(int amount, int position, String text, Boolean enabled){
		this.amount = amount;
		this.position = position;
		this.text = text;
		this.enabled = enabled;
	}
	
	public Objective(Objective o){
		amount = o.amount;
		text = o.text;
		enabled = o.enabled;
	}
	
	public Objective copy(){
		return new Objective(this);
	}
	
	public void changeEnabled(){
		enabled = !enabled;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public int getPosition(){
		return position;
	}
	
	public String getText(){
		return text;
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public String toString(){
		return " "+amount+" IPCs |"+text;
	}
	
	public String save(){
		return "obj: "+String.valueOf(enabled)+" "+amount+" "+text;
	}

}
