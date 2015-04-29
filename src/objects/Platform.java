package objects;

import static framework.Game.GAME_TIME;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;

import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.Handler;
import framework.ObjectID;

public class Platform extends GameObject {
	
	private PressurePad button;
	private float originalx,originaly;
	private int plat_id;
	private int direction;
	
	public Platform(float x, float y, int direction, int id) {
		super(x,y,0,0,ObjectID.Platform,new Rectangle());
		if(direction == 1)			//horiziontl
		{
			width = 256;
			height = 32;
			hitbox = new Rectangle((int)x,(int)y,(int)width,(int)height);

		} else if(direction == 0) //vritcal
		{
			width = 32;
			height = 256;
			hitbox = new Rectangle((int)x,(int)y,(int)width,(int)height);
		}
		originalx = x;
		originaly = y;
		plat_id = id;
		this.direction = direction;
	}

	public int getPlatID()
	{
		return plat_id;
	}
	
	public void setButton(PressurePad b)
	{
		button = b;
	}
	
	@Override
	public void tick() {
		if(button != null)
			if(button.getPressed())
				if(direction == 1)
					x += -1;
				else
					y += 1;
			else
				moveTo(originalx,originaly);

		updateHitbox();
	
	}

	public void moveTo(float x, float y){

		xSpeed = (this.x - x)/Math.abs(this.x - x)*-1;
		ySpeed = (this.y-y)/Math.abs(this.y - y)*-1;
		//System.out.println("test");
		if(this.x != x)
			this.x += xSpeed;
		if(this.y != y)
			this.y += ySpeed;
	}
	public void collision()
	{
		//nothing
	}
	
	@Override
	public void render() {
		Draw.startTrans();
		Draw.drawQuad(x, y, width, height);
		Draw.endTrans();

	}

	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y));
	}
}
