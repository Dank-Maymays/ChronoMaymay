package objects;

import org.lwjgl.util.Rectangle;

import framework.Draw;
import framework.GameObject;
import framework.ObjectID;

public class Platform extends GameObject {

	public Platform(float x, float y, float width, float height) {
		super(x, y, width, height, ObjectID.Platform);
		hitbox = new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	@Override
	public void tick() {
		//nothing
	}

	public void collision()
	{
		//nothing
	}
	
	@Override
	public void render() {
//		Draw.startTrans();
//		Draw.drawQuad(x, y, width, height,texture);
//		Draw.endTrans();
		Draw.drawQuad(x, y, width, height);
	}

}
