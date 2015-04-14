package objects;

import framework.GameObject;
import framework.ObjectID;

import static framework.Draw.*;

public class TestBlock extends GameObject {

	public TestBlock(float x, float y, float width, float height)
	{
		super(x,y,width,height,ObjectID.Player);
		
	}

	@Override
	public void tick() {
		x += 3;
	}

	@Override
	public void render() {
		drawQuad(x,y,width,height,texture);		
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
	
}
