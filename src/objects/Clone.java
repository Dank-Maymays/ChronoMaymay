package objects;

import static framework.Game.GAME_TIME;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import framework.Action;
import framework.Instructions;

public class Clone extends Player{

	Instructions instruct;
	boolean left=false,right=false,jump=false;
	ArrayList<Action> actions = new ArrayList<Action>(); 
	
	public Clone(Instructions list, int x, int y) {
		super(x,y,256,256);
		instruct = list;
	}
	
	public void tick()
	{
		actions = instruct.getCommands();
		for(int i = 0; i < actions.size();i++)
			parseAction(actions.get(i));
		instruct.update();

	   ////PLAYER CODE WITH LEFT RIGHT JUMP FLAGS INSTEAD OF OTHER FLAGS
		
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
		if(right) // If the right arrow key is pressed,
		{
			if(!shooting)
				current = walkRight; // Set the animation to walkRight.
			else
				current = shoot_right;
			xSpeed = PLAYER_SPEED; // Set the xSpeed to 0.3f.
			direction = 1; // Set the direction to 1.
		}
		else if(left) // If the left arrow key is pressed,
		{
			if(!shooting)
				current = walkLeft; // Set the animation to walkLeft.
			else
				current = shoot_left;
			xSpeed = -PLAYER_SPEED; // Set the xSpeed to -0.3f.
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
		if(jump)
		{
			if(!falling && !jumping)
			{
				jumping = true;
				ySpeed = -0.6f;
			}
		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !shooting)
//		{
//			shooting = true;
//			falling = true;
//		}
		
		if(falling || jumping)
			ySpeed += gravity;
		if(ySpeed > MAX_SPEED)
			ySpeed = MAX_SPEED;
		
		x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
		updateHitbox();
		collision();
		
	}
	
	private void parseAction(Action a)
	{
		switch(a)
		{
			case LEFT_DOWN:
				left = true;
				break;
			case LEFT_UP:
				left = false;
				break;
			case RIGHT_DOWN:
				right = true;
				break;
			case RIGHT_UP:
				right = false;
				break;
			case JUMP_DOWN:
				jump = true;
				break;
			case JUMP_UP:
				jump = false;
				break;
		}
	}

}
