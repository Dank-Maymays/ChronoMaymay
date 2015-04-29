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

import framework.Animation;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.ObjectID;

public class Laser extends GameObject{
	private Animation flash;
	public boolean isOn = true;
	
	public Laser(float x, float y){
		super(x,y,200,200,ObjectID.Laser,new Rectangle((int)(x+95),(int)(y),(int)(10),(int)(200)));
		flash = new Animation("res/laser",16);
	}
	
	public void render(){
			
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		if(isOn){
		Draw.drawQuad(x, y, width, height,flash.getCurrentFrame());//Draw the laser based on current position
		}
		else{
			Draw.drawQuad(x, y, width, height, texture);
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}
		}
			
	}	
	
	public void tick() {
		if(isOn)
		{
			if(flash!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
				flash.update(); // Updates the animation so that it goes to the next frame.
			x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			updateHitbox();
		}
		else{
			
		}
	}
	
	public boolean getIsOn(){
		return isOn;
	}
	
	public void setIsOn(boolean on){
		isOn = on;
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x+95), (int)(y));
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}
