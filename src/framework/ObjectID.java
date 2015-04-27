package framework;

public enum ObjectID {
	
	Player("sprite_1",false), Platform("platform",true), Part(null,false), Pad(null,true), Door(null,false), 
	Block("block",true), Clone(null,false), Laser("laser_off", false), Beam("beam",false);
	
	public String texture;
	public boolean walkable;
	
	ObjectID(String texture, boolean walkable)
	{
		this.texture = texture;
		this.walkable = walkable;
	}
}
