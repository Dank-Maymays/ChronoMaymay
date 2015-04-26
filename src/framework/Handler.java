package framework;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import objects.Player;

public class Handler {
	private static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private static LinkedList<Player> players = new LinkedList<Player>(); 
	private static Level currentLevel;
	
	public static LinkedList<GameObject> getObjects(){

		return objects;
	}
	public static LinkedList<Player> getPlayers() {
		return players;
	}
	public static void tick()
	{
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).tick();
	}
	public static void render()
	{
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).render();
	}
	
	public static void resetLevel()
	{
		objects = (LinkedList<GameObject>) currentLevel.getObjects().clone();
	}
	
	public static void loadLevel(String path)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(new File(path));
			currentLevel = new Level(img,1);
			objects = (LinkedList<GameObject>) currentLevel.getObjects().clone();
		} catch(IOException e)
		{
			System.out.println("could not load level");
		}
	}
}
