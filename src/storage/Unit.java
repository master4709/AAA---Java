package storage;

public class Unit {
	
	private int attack;
	private int defense;
	private int movement;
	private int cost;
	private int count;
	private int position;
	private String name;
	private String stats;
	
	
	public Unit(int attack, int defense, int movement, int cost, int position, String name){
		this.attack = attack;
		this.defense = defense;
		this.movement = movement;
		this.cost = cost;
		this.count = 0;
		this.position = position;
		this.name = name;
		this.stats = " "+attack+"   "+defense+"   "+movement+"   "+cost;
	}
	
	private Unit(Unit u){
		attack = u.attack;
		defense = u.defense;
		movement = u.movement;
		cost = u.cost;
		count = u.count;
		position = u.position;
		name = u.name;
		stats = u.stats;
	}
	
	public Unit copy(){
		return new Unit(this);
	}
	
	public void addOne(){
		count++;
	}
	public void resetCount(){
		count = 0;
	}
	
	public String getStats(){
		return stats;
	}
	
	public Integer getAttack(){
		return attack;
	}
	
	public Integer getDefense(){
		return defense;
	}
	
	public Integer getMovement(){
		return movement;
	}
	
	public Integer getCost(){
		return cost;
	}
	
	public Integer getPosition(){
		return position;
	}
	
	public Integer getCount(){
		return count;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return position +". "+ name + " " + attack + " " + defense + " " + movement + " " + cost + " " + count + " | ";
	}
}
