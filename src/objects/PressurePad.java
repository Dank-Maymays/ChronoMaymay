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
import framework.Animation;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.Handler;
import framework.ObjectID;

public class PressurePad extends GameObject{
	
	private boolean pressed = false;
	private Animation states;
	private Rectangle hitbox_button;
	
	public PressurePad(float x, float y){
		super(x,y,200,200,ObjectID.Pad,new Rectangle((int)(x),(int)(y+200*.9),(int)(200),(int)(200/10)));
		hitbox_button = new Rectangle((int)(x+200/20-5),(int)(y+200*.81),(int)(200/20*19),(int)(200/10));
		states = new Animation("res/green_button",1);
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		if (!pressed)
			setTexture(states.getFrame(0));
		else
			setTexture(states.getFrame(1));
		Draw.drawQuad(x, y, width, height,texture);
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox_button.getX(), hitbox_button.getY(), hitbox_button.getWidth(), hitbox_button.getHeight());
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}	
	}	
	
	public void setPressed(boolean a)
	{
		pressed = a;
	}
	
	public boolean getPressed()
	{
		return pressed;
	}
	
	public void tick() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
			Game.DEBUG = true;
		else
			Game.DEBUG = false;
		x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		//updateHitbox();
		collision();
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y+height*0.81));
	}
	private void collision()
	{
		for(int i = 0; i < Handler.getPlayers().size(); i++)
		{
			Player p = Handler.getPlayers().get(i);
			if(p.getHitbox_bottom().intersects(hitbox_button))
				pressed = true;
			else
				pressed = false;
		}
	}
	
}
