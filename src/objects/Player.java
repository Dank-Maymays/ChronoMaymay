package objects;

import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;

import framework.Action;
import framework.Animation;
import framework.Draw;
import framework.Game;
import framework.GameObject;
import framework.Handler;
import framework.Instruction;
import framework.Instructions;
import framework.ObjectID;
import framework.Time;

public class Player extends GameObject{
	
	protected static Animation idle_right = new Animation("res/idle_right",12); // Loads the the sprites from the idle_right folder into idle_right
	protected static Animation idle_left = new Animation("res/idle_left",12); // Loads the sprites from the idle_left folder into idle_left
	protected static Animation walkLeft = new Animation("res/left",24); // Loads the sprites from the left folder into left
	protected static Animation walkRight = new Animation("res/right",24); // Loads the sprites from the right folder into right
	protected static Animation shoot_left = new Animation("res/shoot_left",24); // Loads the sprites from the shoot_left folder into shoot_left
	protected static Animation shoot_right = new Animation("res/shoot_right",24); // Loads the sprites from the shoot_right folder into shoot_right
	protected static Animation idle_shoot_left = new Animation("res/idle_shoot_left",24); // Loads the sprites from the idle_shoot_left folder into idle_shoot_left
	protected static Animation idle_shoot_right = new Animation("res/idle_shoot_right",24); // RIGHT IDLE
	protected static Animation laser_death = new Animation("res/laser_death", 24);	//DEATH BY LASER
	protected Animation current;
	protected Player clone;
	protected float start_x;
	protected float start_y;
	private boolean recording = false;
	private boolean left_down;
	private boolean right_down;
	private boolean jump_down;
	protected int direction = 0; // DIRECTION OF PLAYER
	protected final float gravity = 0.02f;
	protected final float MAX_SPEED = 1f;
	protected final float PLAYER_SPEED = 0.3f;
	protected boolean shooting = false;
	protected boolean falling = false, jumping = false;
	protected boolean dead = false;		//becomes true if player collides with a laser
	protected Rectangle hitbox_left = new Rectangle((int)(x+width/3+width/40-2),(int)(y+height/2+height/5),(int)width/40,(int)(height/2 - height/5));
	protected Rectangle hitbox_right = new Rectangle((int)(x+width*2/3-width/20-3),(int)(y+height/2+height/10+5),(int)width/40,(int)(height/2 - height/5));
	protected Rectangle hitbox_bottom = new Rectangle((int)(x+width/3+width/25),(int)(y+height*19/20),(int)(width/3 - width/10),(int)(height/20));
	protected Rectangle hitbox_top = new Rectangle((int)(x+width/3+width/25),(int)(y+height/2+height/10),(int)(width/3 - width/10),(int)(height/20));
	protected Instructions is;
	private ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	private Time recTime = new Time();
	private long time = 0;
	public static int parts = 0;
	
	public Player(float x, float y, float width, float height){
		super(x,y,width,height,ObjectID.Player,new Rectangle((int)x,(int)(y+height/10),(int)(width*3/10-5),(int)(height/2 - height/10))); // Sends the super class GameObject the x, y, width, height, and the Object ID of Player		
		current = new Animation("res/idle_front",12); // Loads the sprites from the idle_front folder at 12 fps into current animation
	}
	
	public Player(float x, float y, float width, float height, ObjectID id){
		super(x,y,width,height,id,new Rectangle((int)x,(int)(y+height/10),(int)(width*3/10-5),(int)(height/2 - height/10))); // Sends the super class GameObject the x, y, width, height, and the Object ID of Player		
		current = new Animation("res/idle_front",12); // Loads the sprites from the idle_front folder at 12 fps into current animation
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
		if(true)
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
		if(dead){
			if (current.getCurrentFrame() == current.getFrame(20) || current.getCurrentFrame() == current.getFrame(21)){
				current = new Animation("res/idle_front", 12);
				//SOMETHING TO RESET THE LEVEL
				dead = false;
			}
		}
		else{
			if(shooting)
			{
				if(current.getCurrentFrame() == current.getFrame(0) && current.firstRun())
				{
					current.setFrame(0);
					shooting = false;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) // If the right arrow key is pressed,
			{
				if(!shooting)
					current = walkRight; // Set the animation to walkRight.
				else
					current = shoot_right;
				xSpeed = PLAYER_SPEED; // Set the xSpeed to 0.3f.
				direction = 1; // Set the direction to 1.

				if(recording && !right_down)
				{
					System.out.printf("RIGHT_DOWN added at %d", time);
					instructions.add(new Instruction(time,Action.RIGHT_DOWN));
					right_down = true;
				}
			} else if(right_down)
			{

				System.out.printf("RIGHT_UP added at %d", time);
				instructions.add(new Instruction(time,Action.RIGHT_UP));
				right_down = false;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) // If the left arrow key is pressed,
			{
				if(!shooting)
					current = walkLeft; // Set the animation to walkLeft.
				else
					current = shoot_left;
				xSpeed = -PLAYER_SPEED; // Set the xSpeed to -0.3f.
				direction = -1; // Set the direction to -1
				if(recording && !left_down)
				{
					System.out.printf("LEFT_DOWN added at %d", time);
					instructions.add(new Instruction(time,Action.LEFT_DOWN));
					left_down = true;
				}

			} else if(left_down)
			{
				System.out.printf("LEFT_UP added at %d", time);
				instructions.add(new Instruction(time,Action.LEFT_UP));
				left_down = false;
			}
			if(!Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
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
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			{
				if(!jumping)
				{
					jumping = true;
					ySpeed = -0.6f;
					if(recording && !jump_down)
					{
						System.out.printf("JUMP_DOWN added at %d", time);
						instructions.add(new Instruction(time,Action.JUMP_DOWN));
						jump_down = true;
					}
				}
			} else if(jump_down)
			{
				System.out.printf("JUMP_UP added at %d", time);
				instructions.add(new Instruction(time,Action.JUMP_UP));
				jump_down = false;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !recording)
			{
				recording =  true;
				clone = new Player(this.x,this.y,256,256);
				clone.falling = this.falling;
				clone.jumping = this.jumping;
				clone.current = this.current;
				time = 0;
			}
			else if(recording && !Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				recording = false;
				Clone c = new Clone(new Instructions((ArrayList<Instruction>) instructions.clone(), time), clone);
				Handler.getObjects().add(c);
				start_x = 0;
				start_y = 0;
				recTime.reset();
				time = 0;
				instructions.clear();

			}
			if(recording)
			{
				recTime.update();
				time += recTime.Delta();
			}
			if(falling || jumping)			
				ySpeed += gravity;
			if(ySpeed > MAX_SPEED)
				ySpeed = MAX_SPEED;

			x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			updateHitbox();
			collision();
		}
	}

	protected void collision()
	{
		for(int i = 0; i < Handler.getObjects().size(); i++)
		{
			GameObject tempObj = Handler.getObjects().get(i);
			if(tempObj.getID().walkable)
			{
				if(tempObj.getHitbox().intersects(hitbox_bottom))
				{
					ySpeed = 0;
					falling = jumping = false;
					y = tempObj.getHitbox().getY()-height+2;
				} else {
					falling = true;
				}
				if(tempObj.getHitbox().intersects(hitbox_top))
				{
					y = tempObj.getHitbox().getY()+tempObj.getHitbox().getHeight() - (hitbox_top.getY()-hitbox_top.getHeight()-height)+10;
					ySpeed = 0;
				}
				if(tempObj.getHitbox().intersects(hitbox_right))
				{
					xSpeed = 0;
					x = tempObj.getHitbox().getX()-(width*2/3-width/20-3)-hitbox_right.getWidth();
					System.out.println("right");
				}
				if(tempObj.getHitbox().intersects(hitbox_left))
				{
					xSpeed = 0;
					x = tempObj.getHitbox().getX();
					System.out.println("left");
				}
				
			}
			if(tempObj.getID() == ObjectID.Clone)
			{
				Clone p = (Clone) tempObj;
				if(p.getHitbox_top().intersects(hitbox_bottom))
				{
					ySpeed = 0;
					falling = jumping = false;
					y = p.getHitbox_top().getY()-height+2;
				}
			}
			
			if(tempObj.getHitbox().intersects(hitbox))
			{
				if(tempObj instanceof Part && !((Part) tempObj).collected)
				{
					parts++;
					((Part) tempObj).collected = true;
				}
			}
			if(tempObj.getID() == ObjectID.Laser && ((Laser)tempObj).getIsOn()){	//collision with laser
				if(tempObj.getHitbox().intersects(hitbox)){
					current = laser_death;
					dead = true;
				}
			}
		}
	}
	
	public Rectangle getHitbox_left() {
		return hitbox_left;
	}

	public Rectangle getHitbox_right() {
		return hitbox_right;
	}

	public Rectangle getHitbox_bottom() {
		return hitbox_bottom;
	}

	public Rectangle getHitbox_top() {
		return hitbox_top;
	}

	protected void updateHitbox()
	{
		hitbox.setLocation((int)(x+width*3/10+13), (int)(y+height/2+height/10));
		hitbox_left.setLocation((int)(x+width/3+width/40-2), (int)(y+height/2+height/20*3));
		hitbox_right.setLocation((int)(x+width*2/3-width/20-3),(int)(y+height/2+height/20*3));
		hitbox_top.setLocation((int)(x+width/3+width/25),(int)(y+height/2+height/10-5));
		hitbox_bottom.setLocation((int)(x+width/3+width/25),(int)(y+height*19/20));
	}

}