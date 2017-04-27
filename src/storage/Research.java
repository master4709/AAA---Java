package storage;

public class Research {
	
	private String research;
	
	private String info;
	
	private int position;
	
	public Research(String research, String info, int position){
		this.research = research;
		this.info = info;
		this.position = position;
	}
	
	private Research(Research r){
		research = r.research;
		info = r.info;
		position = r.position;
	}
	
	public Research copy(){
		return new Research(this);
	}
	
	public String getResearch(){
		return research;
	}
	
	public String getInfo(){
		return info;
	}
	
	public int getPosition(){
		return position;
	}
	
	@Override
	public String toString(){
		return research+"|"+info;
	}

}
