package objects;

import static framework.Time.*;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

import static framework.Game.GAME_TIME;

import framework.Animation;
import framework.Draw;
import framework.GameObject;
import framework.ObjectID;

public class Player extends GameObject{
	
	private Animation walkLeft;
	private Animation walkRight;
	private Animation idle_front;
	private Animation idle_left;
	private Animation idle_right;
	private Animation current;
	private int direction = 0;
	public Player(float x, float y, float width, float height){
		super(x,y,width,height,ObjectID.Grass);
		idle_front = new Animation("res/idle_front",60);
		idle_right = new Animation("res/idle_right",60);
		idle_left = new Animation("res/idle_left",60);
		walkLeft = new Animation("res/left",60);
		walkRight = new Animation("res/right",60);
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,current.getCurrentFrame()); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
	}

	@Override
	public void tick() {
		if(current!=null)
			current.update();
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			current = walkRight;
			xSpeed = 0.3f;
			direction = 2;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			current = walkLeft;
			xSpeed = -0.3f;
			direction = 1;
		}
		else
		{
			if(direction == 0)
				current = idle_front;
			else if(direction == 1)
				current = idle_left;
			else if(direction == 2)
				current = idle_right;
			xSpeed = 0;
		}
		x+=xSpeed*GAME_TIME.Delta();
		y+=ySpeed*GAME_TIME.Delta();
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}