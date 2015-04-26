package objects;

import org.lwjgl.util.Rectangle;

import framework.Draw;
import framework.GameObject;
import framework.ObjectID;

public class Block extends GameObject {
	/**
	 * makes a block at given x and y with size 64x64 with a rectangle hitbox
	 * @param x x location of block
	 * @param y y location of block
	 */
	public Block(float x, float y) {
		super(x,y,64,64, ObjectID.Block, new Rectangle((int)x,(int)y,(int)64,(int)64));
	}

	@Override
	public void tick() {
		//does not serve a purpose for the block, it has no relation to time
	}

	@Override
	/**
	 * draws a block
	 */
	public void render() {
		Draw.drawQuad(x, y, width, height,texture);
		
	}

}
