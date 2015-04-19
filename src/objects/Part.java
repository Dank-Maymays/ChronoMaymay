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
	private Rectangle hitbox_left = new Rectangle((int)(x+width/3+width/40-2),(int)(y+height/2+height/10+5),(int)width/40,(int)(height/2 - height/10-10));
	private Rectangle hitbox_right = new Rectangle((int)(x+width*2/3-width/20-3),(int)(y+height/2+height/10+5),(int)width/40,(int)(height/2 - height/10-10));
	private Rectangle hitbox_bottom = new Rectangle((int)(x+width/3+width/25),(int)(y+height*19/20),(int)(width/3 - width/10),(int)(height/20));
	private Rectangle hitbox_top = new Rectangle((int)(x+width/3+width/25),(int)(y+height/2+height/10),(int)(width/3 - width/10),(int)(height/20));
	private boolean test = false;
	
	public Part(float x, float y, int partNum){
		super(x,y,50,50,ObjectID.Part);
		hitbox = new Rectangle((int)x,(int)(y+height/10),(int)(width*3/10-5),(int)(height/2 - height/10));
		hover = new Animation("res/part_"+partNum,6);//animation will choose based on partNum (1-10) in the folders part_1 - part_10
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,hover.getCurrentFrame()); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		
		if(Game.DEBUG)
		{
			Draw.drawQuad(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		}
		if(test)
		{
			Draw.drawQuad(hitbox_left.getX(), hitbox_left.getY(), hitbox_left.getWidth(), hitbox_left.getHeight(),1,0,0);
			Draw.drawQuad(hitbox_right.getX(), hitbox_right.getY(), hitbox_right.getWidth(), hitbox_right.getHeight(),0,1,0);
			Draw.drawQuad(hitbox_top.getX(), hitbox_top.getY(), hitbox_top.getWidth(), hitbox_top.getHeight(),0,0,1);
			Draw.drawQuad(hitbox_bottom.getX(), hitbox_bottom.getY(), hitbox_bottom.getWidth(), hitbox_bottom.getHeight(),1,0,1);
		}
			
	}
	
	public void tick() {
		if(hover!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
			hover.update(); // Updates the animation so that it goes to the next frame.
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)){
			test = true;
			Game.DEBUG = true;
		}else{
			test = false;	
			Game.DEBUG = false;
		}

		x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		updateHitbox();
	
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x+width*3/10+13), (int)(y+height));
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}
