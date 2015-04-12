package objects;

import static framework.Time.*;

import org.lwjgl.input.Keyboard;

import framework.Animation;
import framework.Draw;
import framework.GameObject;
import framework.ObjectID;

public class Player extends GameObject{
	
	private Animation walkLeft;
	private Animation walkRight;
	private Animation idle;
	private Animation current;
	public Player(float x, float y, float width, float height){
		super(x,y,width,height,ObjectID.Player);
	}
	
	public void render(){
		if(texture == null)
			Draw.drawQuad(x, y, width, height);
		else
			Draw.drawQuad(x, y, width, height,current.nextFrame());
	}

	@Override
	public void tick() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			current = walkRight;
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			current = walkLeft;
		else
			current = idle;
		x+=xSpeed*Delta();
		y+=ySpeed*Delta();
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}