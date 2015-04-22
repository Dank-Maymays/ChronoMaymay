package objects;

import static framework.Game.GAME_TIME;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;

import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.ObjectID;

public class Platform extends GameObject {
	
	boolean isMoving;
	public Platform(float x, float y, float width, float height, boolean move) {
		super(x, y, width, height, ObjectID.Platform,new Rectangle((int)(x),(int)(y+height/2.45),(int)width,(int)(height/6.1)));
		isMoving = move;
	}

	@Override
	public void tick() {
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			Game.DEBUG = true;
		}else{
			Game.DEBUG = false;
		}

		x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		updateHitbox();
	
	}

	public void movePlatform(float d, float e){

		x += d;
		y += e;
	}
	public void collision()
	{
		//nothing
	}
	
	@Override
	public void render() {
		if(isMoving)
			xSpeed = 0.05f;
		Draw.startTrans();
		Draw.drawQuad(x, y, width, height,texture);
		Draw.endTrans();
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}
	}

	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y+height/2.45));
	}
}
