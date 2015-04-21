package framework;

import static framework.Draw.Background;

import static framework.Draw.Setup;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;


import objects.Part;

import objects.Platform;

import objects.Player;

import objects.PressurePad;

import objects.Door;

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
		objects.add(new Part(256,256,7));
		objects.add(new Platform(64,300,400,256));
		objects.add(new PressurePad(400,250));
		objects.add(new Door(700, 250, 256, 256, 6));

		Handler.getObjects().add(new Player(64,64,256,256));
		Handler.getObjects().add(new Platform(64,300,400,256));
		Handler.getObjects().add(new Part(256,256,7));
		Handler.getObjects().add(new PressurePad(400,150));
		Handler.getObjects().add(new Door(700, 250, 256, 256, 6));
		while(!Display.isCloseRequested())
		{
			GAME_TIME.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			Background();
			for(int i = 0; i < Handler.getObjects().size();i++)
			{
				Handler.getObjects().get(i).tick();
				Handler.getObjects().get(i).render();
			}
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void createLevel(String path)
	{
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}