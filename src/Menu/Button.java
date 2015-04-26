package Menu;

import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import framework.Draw;
import framework.Game;

import org.newdawn.slick.opengl.Texture;

public class Button {
	private float x,y,width,height;
	private Texture button;
	private String folder;
	
	public Button(float xCoor, float yCoor, float w, float h, String f){
		x = xCoor;
		y = yCoor;
		width = w;
		height = h;
		folder = f;
		button = Draw.quickLoad(folder+"/sprite_"+1);
	}
	
	public void setButton(int i){
		button = Draw.quickLoad(folder+"/sprite_"+i);
	}
	public void hover(){
		if(Mouse.getX()>=x && Mouse.getX()<=x+width-50 && Mouse.getY()>=y-165 && Mouse.getY()<=y+height-220)
			setButton(2);
		else
			setButton(1);
	}
	public boolean isPressed(){
		if(Mouse.getX()>=x && Mouse.getX()<=x+width-50 && Mouse.getY()>=y-165 && Mouse.getY()<=y+height-220)
			if(Mouse.isButtonDown(0))
				return true;
		return false;
	}
	public void drawButton(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, 720-y, width, height,button); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		hover();
	}
}
