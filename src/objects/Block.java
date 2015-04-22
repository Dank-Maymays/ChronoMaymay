package objects;

import org.lwjgl.util.Rectangle;

import framework.Draw;
import framework.GameObject;
import framework.ObjectID;

public class Block extends GameObject {

	public Block(float x, float y) {
		super(x,y,64,64, ObjectID.Block, new Rectangle((int)x,(int)y,(int)64,(int)64));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Draw.drawQuad(x, y, width, height,texture);
		
	}

}
