package objects;

import static framework.Time.*;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

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
		idle = new Animation("res/idle");
	}
	
	public void render(){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		if(texture == null)
			Draw.drawQuad(x, y, width, height);
		else
			Draw.drawQuad(x, y, width, height,current.nextFrame());
		glDisable(GL_BLEND);
	}

	@Override
	public void tick() {
//		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
//			current = walkRight;
//		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
//			current = walkLeft;
//		else
			current = idle;
		x+=xSpeed*Delta();
		y+=ySpeed*Delta();
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}