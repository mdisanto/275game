package model;

public class Trash extends Hazard{
	Tool type;
	public Trash(int xpos, int ypos, int xvel, int yvel, int spawntime, Tool type) {
		super(xpos, ypos, xvel, yvel, spawntime);
		this.type = type;
	}
	public Tool getType() {
		return type;
	}
	public void setType(Tool type) {
		this.type = type;
	}
	
}
