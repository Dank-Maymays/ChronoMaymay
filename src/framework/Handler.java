package framework;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import objects.Clone;
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
	
	public static Level getLevel()
	{
		return currentLevel;
	}
	
	public static void resetLevel()
	{
		objects = (LinkedList<GameObject>) currentLevel.getObjects().clone();
	}
	
	public static void removeLastClone()
	{
		Clone potato = null;
		for(GameObject obj: objects)
			if(obj.getID() == ObjectID.Clone)
			{
				Clone c = (Clone) obj;
				c.id--;
				if(c.id < 0)
				{
					potato = c;
				}
			}
		if(potato != null)
			objects.remove(potato);
	}
	
	public static void loadLevel(LinkedList<GameObject> list, int clones)
	{
		currentLevel = new Level(list, clones);
	}
	
	public static void loadLevel(String path, int clones)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(new File(path));
			currentLevel = new Level(img,clones);
			objects = (LinkedList<GameObject>) currentLevel.getObjects().clone();
		} catch(IOException e)
		{
			System.out.println("could not load level");
		}
	}
}
