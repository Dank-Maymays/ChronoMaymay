package framework;

import static framework.Draw.*;
import static org.lwjgl.opengl.GL11.*;
import objects.Player;

import org.lwjgl.opengl.Display;


public class Game {
	
	public Game()
	{
		Setup();
		Player p = new Player(0,0,64,64);
		while(!Display.isCloseRequested())
		{
			Time.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			p.tick();
			p.render();
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