package framework;

public enum ObjectID {
	
	Player("sprite_1"), Grass("grass");
	
	String texture;
	
	ObjectID(String texture)
	{
		this.texture = texture;
	}
}
