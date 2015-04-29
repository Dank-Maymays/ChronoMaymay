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
import framework.Animation;
import framework.GameObject;
import framework.ObjectID;

public class Part extends GameObject{
	private Animation hover;
	public boolean collected = false;
	
	public Part(float x, float y, int partNum){
		super(x,y,50,50,ObjectID.Part,new Rectangle((int)(x),(int)(y),(int)(50),(int)(50)));
		hover = new Animation("res/part_"+partNum,6);//animation will choose based on partNum (1-10) in the folders part_1 - part_10
	}
	
	public void render(){
		if(!collected){
			
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,hover.getCurrentFrame()); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}
		}
			
	}	
	
	public void tick() {
		if(!collected)
		{
			if(hover!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
				hover.update(); // Updates the animation so that it goes to the next frame.
			x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			updateHitbox();
		}
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x), (int)(y));
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}
