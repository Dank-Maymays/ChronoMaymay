package objects;

import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.util.Rectangle;
import org.lwjgl.input.Keyboard;

import framework.Animation;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.Handler;
import framework.ObjectID;

public class Player extends GameObject{
	
	private Animation walkLeft; // WALKING LEFT ANIMATION
	private Animation walkRight; // WALKING RIGHT ANIMATION
	private Animation idle_left; // IDLE LEFT ANIMATION
	private Animation idle_right; // IDLE RIGHT ANIMATION
	private Animation current; // CURRENT ANIMATION -- USED FOR SETTING THE RIGHT ANIMATION
	private Animation shoot_left; // WALK AND SHOOT LEFT ANIMATION
	private Animation shoot_right; // WALK AND SHOOT RIGHT ANIMATION
	private Animation idle_shoot_left; // IDLE AND SHOOT LEFT ANIMATION
	private Animation idle_shoot_right; // IDLE AND SHOOT RIGHT ANIMATION
	private int direction = 0; // DIRECTION OF PLAYER
	private float gravity = 0.02f;
	private final float MAX_SPEED = 1f;
	private boolean shooting = false;
	private boolean falling = false, jumping = false;
	private Rectangle hitbox_left = new Rectangle((int)(x+width/3+width/40-2),(int)(y+height/2+height/10+5),(int)width/40,(int)(height/2 - height/10-10));
	private Rectangle hitbox_right = new Rectangle((int)(x+width*2/3-width/20-3),(int)(y+height/2+height/10+5),(int)width/40,(int)(height/2 - height/10-10));
	private Rectangle hitbox_bottom = new Rectangle((int)(x+width/3+width/25),(int)(y+height*19/20),(int)(width/3 - width/10),(int)(height/20));
	private Rectangle hitbox_top = new Rectangle((int)(x+width/3+width/25),(int)(y+height/2+height/10),(int)(width/3 - width/10),(int)(height/20));
	private boolean test = false;
	
	public Player(float x, float y, float width, float height){
		super(x,y,width,height,ObjectID.Grass); // Sends the super class GameObject the x, y, width, height, and the Object ID of Player
		hitbox = new Rectangle((int)x,(int)(y+height/10),(int)(width*3/10-5),(int)(height/2 - height/10));
		current = new Animation("res/idle_front",12); /* Loads the sprites from the idle_front folder at 12 fps into current animation -- That way it starts off facing 
													     front and doesnt waste memory by storing the front animation that does nothing. */
		idle_right = new Animation("res/idle_right",12); // Loads the the sprites from the idle_right folder into idle_right
		idle_left = new Animation("res/idle_left",12); // Loads the sprites from the idle_left folder into idle_left
		walkLeft = new Animation("res/left",24); // Loads the sprites from the left folder into left
		walkRight = new Animation("res/right",24); // Loads the sprites from the right folder into right
		shoot_left = new Animation("res/shoot_left",24); // Loads the sprites from the shoot_left folder into shoot_left
		shoot_right = new Animation("res/shoot_right",24); // Loads the sprites from the shoot_right folder into shoot_right
		idle_shoot_left = new Animation("res/idle_shoot_left",24); // Loads the sprites from the idle_shoot_left folder into idle_shoot_left
		idle_shoot_right = new Animation("res/idle_shoot_right",24); // Loads the sprites from the idle_shoot_right folder into idle_shoot_right
		
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,current.getCurrentFrame()); //Draw the player based on current position and current animation.
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
		if(current!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
			current.update(); // Updates the animation so that it goes to the next frame.
		if(shooting)
		{
			if(current.getCurrentFrame() == current.getFrame(0) && current.firstRun())
			{
				current.setFrame(0);
				shooting = false;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0))
			test = true;
		else
			test = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) // If the right arrow key is pressed,
		{
			if(!shooting)
				current = walkRight; // Set the animation to walkRight.
			else
				current = shoot_right;
			xSpeed = 0.3f; // Set the xSpeed to 0.3f.
			direction = 1; // Set the direction to 1.
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) // If the left arrow key is pressed,
		{
			if(!shooting)
				current = walkLeft; // Set the animation to walkLeft.
			else
				current = shoot_left;
			xSpeed = -0.3f; // Set the xSpeed to -0.3f.
			direction = -1; // Set the direction to -1
		}
		else // Otherwise, set the idling animations.
		{
			switch(direction)
			{
				case -1:
					if(!shooting)
						current = idle_left;
					else
						current = idle_shoot_left;
					break;
				case 1:
					if(!shooting)
						current = idle_right;
					else
						current = idle_shoot_right;
					break;
					
			}
			xSpeed = 0; // Set the xSpeed to 0.
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !shooting)
		{
			shooting = true;
			falling = true;
		}
		
		if(falling || jumping)
			ySpeed += gravity;
		if(ySpeed > MAX_SPEED)
			ySpeed = MAX_SPEED;
		
	//	x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		updateHitbox();
	
	}

	public void collision()
	{
		for(int i = 0; i < Handler.getObjects().size(); i++)
		{
			GameObject tempObj = Handler.getObject(i);
			if(tempObj.getHitbox().intersects(hitbox))
				if(tempObj instanceof Platform)
					System.out.println("hit");
		}
	}
	
	private void updateHitbox()
	{
		hitbox.setLocation((int)(x+width*3/10+13), (int)(y+height/2+height/10));
	}

}