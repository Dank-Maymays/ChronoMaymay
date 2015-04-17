package objects;

import framework.Animation;
import framework.GameObject;
import framework.ObjectID;

public class Part extends GameObject{
	private Animation hover;
	
	public Part(float x, float y, float width, float height,int textureID){
		super(x,y,32,32,ObjectID.Part);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}
