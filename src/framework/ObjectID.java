package framework;

public enum ObjectID {
	
	Player("sprite_1"), Grass("grass"), Platform("platform"), Part(null);
	
	String texture;
	
	ObjectID(String texture)
	{
		this.texture = texture;
	}
}
