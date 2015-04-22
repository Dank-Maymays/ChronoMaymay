package framework;

import static framework.Draw.Background;
import static framework.Draw.Setup;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import objects.Block;
import objects.Clone;
import objects.Part;
import objects.Platform;
import objects.Player;
import objects.PressurePad;
import objects.Door;

import org.lwjgl.opengl.Display;


public class Game {
	
	public static boolean DEBUG = false;
	public final static Time GAME_TIME = new Time();
	public static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Game()
	{
		Setup();
		//for(int i = 0; i < WIDTH/64; i ++)
		//for(int j = 0; j < HEIGHT/64;j ++)

		//Player p = new Player(64,64,256,256);
		//Handler.getPlayers().add(p);
		ArrayList<Instruction> is = new ArrayList<Instruction>();
		is.add(new Instruction(0, Action.RIGHT_DOWN));
		is.add(new Instruction(0, Action.JUMP_DOWN));
		is.add(new Instruction(1000, Action.RIGHT_UP));
		is.add(new Instruction(10300, Action.JUMP_UP));
		Instructions iz = new Instructions(is, 5);
		Handler.getObjects().add(new Clone(iz, 64, 64));
		//Handler.getObjects().add(new Platform(64,300,256,256,false));
		//Handler.getObjects().add(new Part(128,256,7));
		//Handler.getObjects().add(new PressurePad(400,150));
		//Handler.getObjects().add(new Door(700, 250, 256, 256, 6));
		for(int i = 0; i < Draw.HEIGHT/64; i++)
		{
			Handler.getObjects().add(new Block(0,i*64));
			Handler.getObjects().add(new Block(Draw.WIDTH-64,i*64));
		}			
		for(int i = 0; i < Draw.WIDTH/64; i++)
			Handler.getObjects().add(new Block(i*64,Draw.HEIGHT-64));
		for(int i = 0; i < 3; i++)
			Handler.getObjects().add(new Block(Draw.WIDTH/2+i*64,Draw.HEIGHT/10*5));
		while(!Display.isCloseRequested())
		{
			GAME_TIME.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			Background();
//			for(int i = 0; i < Handler.getPlayers().size(); i++)
//			{
//				Handler.getPlayers().get(i).tick();
//				Handler.getPlayers().get(i).render();
//			}
			
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