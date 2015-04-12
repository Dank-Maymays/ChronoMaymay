package framework;


import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Game {
	
	public static Player alien;
	
	public static void main(String[] args) throws Exception{
		Display.setDisplayMode(new DisplayMode(1280,720));
		Display.create();
		
		alien = new Player();
		
		while(!Display.isCloseRequested()){
			setCamera();
			drawBackground();
			//alien.render();
			//Draw.drawQuad(100, 100, 100, 100);
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public static void setCamera(){
		//Clear screen
		glClear(GL_COLOR_BUFFER_BIT);
		//Modify projection matrix
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,1280,0,720,-1,1);
		//glEnable(GL_TEXTURE_2D);
		//Modify modelview matrix
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
	}
	
	public static void drawBackground(){
		//background
		glBegin(GL_QUADS);
		
		glColor3d(0.0,0.0,0.0);
		glVertex2d(0,0);
		glVertex2d(1280,0);
		glVertex2d(1280,720);
		glVertex2d(0,720);
		
		glEnd();
		
		//ground
		
		glBegin(GL_QUADS);
		
		glColor3d(0.5,0.5,0.5);
		glVertex2d(100,100);
		glVertex2d(1180,100);
		glVertex2d(1180,720);
		glVertex2d(100,720);
		
		glEnd();
	}
}







