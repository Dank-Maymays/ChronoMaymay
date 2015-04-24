package Menu;

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
	
	public Button(float x, float y, float w, float h, String f){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		folder = f;
		button = Draw.quickLoad(folder+"/sprite_"+1);
	}
	
	public void setButton(int i){
		button = Draw.quickLoad(folder+"/sprite_"+i);
	}
	public void hover(){
		System.out.println(x);
		System.out.println(Mouse.getX());
		if(Mouse.getX()>=x+70 && Mouse.getX()<=x+170 && Mouse.getY()>=y+100 && Mouse.getY()<=y+200)
			setButton(2);
		else
			setButton(1);
	}
	public boolean isPressed(){
		if(Mouse.getX()>=x+70 && Mouse.getX()<=x+width-120 && Mouse.getY()>=y+2*height && Mouse.getY()<=y+2*height+100)
			if(Mouse.isButtonDown(0))
				return true;
		return false;
	}
	
	public void drawButton(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, y, width, height,button); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		hover();
	}
}
