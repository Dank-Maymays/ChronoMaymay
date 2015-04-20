package framework;

public enum ObjectID {
	
	Player("sprite_1"), Grass("grass"), Platform("platform"), Part(null), Pad("green_button/sprite_1"), 
			PressedPad("green_button/sprite_2"), Door(null);
	
	public String texture;
	
	ObjectID(String texture)
	{
		this.texture = texture;
	}
}
