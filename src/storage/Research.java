package storage;

public class Research {
	
	private String research;
	
	private int position;
	
	public Research(String research, int position){
		this.research = research;
		this.position = position;
	}
	
	public Research(Research r){
		research = r.research;
		position = r.position;
	}
	
	public String getResearch(){
		return research;
	}
	
	public int getPosition(){
		return position;
	}

}
