package framework;

import static framework.Draw.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import objects.Player;
import objects.TestBlock;

import org.lwjgl.opengl.Display;


public class Game {
	
	public static boolean DEBUG = true;
	public final static Time GAME_TIME = new Time();
	public static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Game()
	{
		Setup();
		//for(int i = 0; i < WIDTH/64; i ++)
			//for(int j = 0; j < HEIGHT/64;j ++)
				objects.add(new Player(64,64,256,256));
		
		while(!Display.isCloseRequested())
		{
			GAME_TIME.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			Background();
			for(int i = 0; i < objects.size();i++)
			{
				objects.get(i).tick();
				objects.get(i).render();
			}
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}