package framework;

import org.lwjgl.input.Keyboard;
import static framework.Time.*;

public class Player extends GameObject{
	
	public Player(float x, float y, float width, float height){
		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;
		xSpeed = 1;
		texture = Draw.quickTexture("sprite_1");
	}
	
	public void render(){
		if(texture == null)
			Draw.drawQuad(x, y, width, height);
		else
			Draw.drawQuad(x, y, width, height,texture);
	}

	@Override
	public void tick() {
		x+=xSpeed*Delta();
		y+=ySpeed*Delta();
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}