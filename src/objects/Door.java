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
import framework.Handler;
import framework.ObjectID;

public class Door extends GameObject{
	private Animation closedDoor;
	private Animation openDoor;
	private Animation opening;
	private Animation current;	//contains current animation
	private int state = 0; //0 = CLOSED, 1 = OPENING, 2 = OPEN
	private int door;
	private boolean test = false;
	private int numLights = 0;
	
	public Door (float x, float y, float width, float height,int door, int numLights){
		super(x,y,256,256,ObjectID.Door, new Rectangle((int)(x+width*0.262),(int)(y+height*.375),(int)(width*0.432),(int)(height*0.628)));
		opening = new Animation("res/doors/"+ numLights + "_light",10);	//chooses numLights based on the parameter passed
		openDoor = new Animation("res/open_door/"+ numLights + "_light",1);
		closedDoor = new Animation("res/closed_door/"+ numLights + "_light",1);
		if(door == 0)
			current = closedDoor;
		else
			current = openDoor;
		this.numLights = numLights;
		this.door = door;
	}
	
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,current.getCurrentFrame()); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
			
	}	
	
	public int getState()
	{
		return state;
	}
	
	public int getDoor()
	{
		return door;
	}
	
	public void tick() {
		
		if(door == 0) {
			if(state == 0)
			{ 
				current = closedDoor;
				current.setFrame(Player.parts);
				if(current.getCurrentFrame() == current.getFrame(numLights))
				{
					state = 1;
					current = opening;
				}
				
			} else if(state == 1)
			{
				current.update();
				if(current.getCurrentFrame() == current.getFrame(18))
					state = 2;
			} else
			{
				current = openDoor;
				current.setFrame(Player.parts);
			}
		} else
		{
			current = openDoor;
			current.setFrame(Player.parts);
		}
//		if(door == 0){ 
//			if(current!=null && state == 1) //Only automatically updates frame if door is opening
//				current.update(); // Updates the animation so that it goes to the next frame
//			if(state == 1 && current.getCurrentFrame() == current.getFrame(15))
//				door = 2;
//			if(Handler.getLevel().getParts() == Player.parts)
//			{
//				state = 1;
//				current = opening;
//			} else
//			{
//				current.getFrame(Player.parts);
//			}
//		} else if(door == 2)
//		{
//			current = openDoor;
//		}else
//			current.getFrame(Player.parts);
	}
	
	public void collision() {
		// TODO runs every tick and checks each collision box to see if we're colliding with anything.
	}
}

