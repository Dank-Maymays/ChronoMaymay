package framework;


import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static framework.Draw.*;

public class Game {
	
	public static Player player;
	public static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public static void main(String[] args){
		
		Setup();
		
		player = new Player(0,0,64,64);
		objects.add(player);
		
		gameLoop();
		Display.destroy();
	}
	
	private static void gameLoop() {
		
		while(!Display.isCloseRequested())
		{
			Time.update();

			for(int i = 0; i < objects.size(); i++)
			{
				objects.get(i).tick();
				objects.get(i).render();
			}
			drawQuad(WIDTH/10,0,WIDTH-(WIDTH/10)*2,HEIGHT-(HEIGHT/10)*2,0.5f,0.5f,0.5f);
			
			Display.update();
			Display.sync(60);
		}
		
	}
}







