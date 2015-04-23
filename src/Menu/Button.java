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

public class Button {
	private float x,y,width,height;
	
	public Button(float x, float y, float w, float h){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	public boolean isPressed(){
		if(Mouse.getX()>=x && Mouse.getX()<=x+width && Mouse.getY()>=y+height/2 && Mouse.getY()<=y+3*height/2)
			if(Mouse.isButtonDown(0))
				return true;
		return false;
	}
	
	public void drawButton(){
		Draw.drawQuad(x, y, width, height);

	}
}
