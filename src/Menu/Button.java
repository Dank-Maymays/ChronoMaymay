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
	/**
	 * makes a button at given x and y coordinates and given width and height at folder f that contains all the animation textures, which will be either play or exit
	 * @param xCoor x coordinate of the button
	 * @param yCoor y coordinate of the button
	 * @param w width of the button
	 * @param h height of the button
	 * @param f the folder to get the textures (res/play/sprite_1 for example)
	 */
	public Button(float xCoor, float yCoor, float w, float h, String f){
		x = xCoor;
		y = yCoor;
		width = w;
		height = h;
		folder = f;
		button = Draw.quickLoad(folder+"/sprite_"+1);
	}
	/**
	 * sets the button between the textures to highlight the button when the mouse is hovering over it
	 * @param i either 0 or 1 for sprite_0 or sprite_1
	 */
	public void setButton(int i){
		button = Draw.quickLoad(folder+"/sprite_"+i);
	}
	/**
	 * checks the location of the mouse to see if it is hovering over the button, subsequently sets the texture to the highlighted version
	 */
	public void hover(){
		if(Mouse.getX()>=x && Mouse.getX()<=x+width-50 && Mouse.getY()>=y-165 && Mouse.getY()<=y+height-220)//checks if the mouse is above the button
			setButton(2);
		else
			setButton(1);
	}
	/**
	 * returns whether or not a button has been pressed
	 * @return true or false for if the button is pressed
	 */
	public boolean isPressed(){
		if(Mouse.getX()>=x && Mouse.getX()<=x+width-50 && Mouse.getY()>=y-165 && Mouse.getY()<=y+height-220)//same check as in hover method
			if(Mouse.isButtonDown(0))//checks if left mouse is pressed
				return true;
		return false;
	}
	/**
	 * draws the button using the drawQuad method and the correct gl draw specs with the button texture
	 */
	public void drawButton(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x, 720-y, width, height,button); //Draw the player based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
		hover();
	}
}
