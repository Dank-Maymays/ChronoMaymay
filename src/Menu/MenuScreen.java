package Menu;

import static framework.Game.GAME_TIME;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import framework.Animation;
import framework.Draw;
public class MenuScreen {
	private Animation title;//title animation
	private Button play;//play and exit buttons
	private Button exit;
	private int x,y,xSpeed,ySpeed;//variables for title animation
	/**
	 * sets up the title and buttons to the correct specification
	 */
	public MenuScreen(){
		title = new Animation("res/title",20);//specified animation folder for the title
		play = new Button(520,380,218,218,"play");//x,y,width,height, and folders for the images
		exit = new Button(530,200,218,218,"exit");
		x=430;
		y=0;
	}
	/**
	 * draws the menu with title and buttons
	 */
	public void drawMenu(){
		tick();//tick and render functions for drawing the animation of the title
		render();
		play.drawButton();//draws the buttons
		exit.drawButton();
		if(exit.isPressed())//if exit button is pressed, game will close
			System.exit(0);
	}
	/**
	 * the render method for the title animation
	 */
	public void render(){
		glEnable(GL_BLEND); //ENABLES BLEND FOR TRANSPARENCY
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //SETS THE BLEND FUNCTION TO WORK PROPERLY -- EVERYTHING BETWEEN HERE
		Draw.drawQuad(x,y, 436, 436,title.getCurrentFrame()); //Draw the title based on current position and current animation.
		glDisable(GL_BLEND); // DISABLES BLEND FUNCTION ------------------------------------------------ AND HERE USES TRANSPARENCY.
	}
	/**
	 * the tick method for the title animation
	 */
	public void tick() {
			if(title!=null) // In case of a loading error we want to make sure that we don't get a null pointer exception by checking first.
				title.update(); // Updates the animation so that it goes to the next frame.
			x+=xSpeed*GAME_TIME.Delta(); // Add the xSpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
			y+=ySpeed*GAME_TIME.Delta(); // Add the ySpeed multiplied by the Delta (difference between currentTime and lastFrame used to have smoother animation) each tick.
	}
    public Button getPlay(){
    	return play;
    }
}
