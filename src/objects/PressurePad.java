package objects;


import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import static framework.Draw.*;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.ObjectID;

public class PressurePad extends GameObject{
	
	private boolean test = false;
	private boolean pressed = false;
	
	public PressurePad(float x, float y){
		super(x,y,200,200,ObjectID.Pad);
		hitbox = new Rectangle((int)(x),(int)(y+height*0.81),(int)(width),(int)(height*0.19));
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		if (pressed)
			setTexture(quickLoad(ObjectID.Pad.texture));
		else
			setTexture(quickLoad(ObjectID.PressedPad.texture));
		Draw.drawQuad(x, y, width, height,texture);
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}	
	}	
	
	public void tick() {
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			test = true;
			Game.DEBUG = true;
		}else{
			test = false;	
			Game.DEBUG = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_X)){ //press X to test press functionality
			pressed = !pressed;
		}
		
		x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		updateHitbox();
	
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y+height*0.81));
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}
