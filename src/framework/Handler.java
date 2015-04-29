package framework;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import objects.Clone;
import objects.Platform;
import objects.Player;
import objects.PressurePad;

public class Handler {
	private static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private static Player player;
	private static Level currentLevel;
	
	public static Level getLevel()
	{
		return currentLevel;
	}
	
	public static LinkedList<GameObject> getObjects(){

		return objects;
	}
	
	public static LinkedList<GameObject> cloneLevel()
	{
		LinkedList<GameObject> list = new LinkedList<GameObject>();
		for(GameObject obj: currentLevel.getObjects())
			list.add(obj);
		return list;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static void setPlayer(Player p)
	{
		player = p;
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
		objects = cloneLevel();
	}
	
	public static void removeLastClone()
	{
		Clone potato = null;
		for(GameObject obj: objects)
			if(obj.getID() == ObjectID.Clone)
			{
				Clone c = (Clone) obj;
				c.clone_id--;
				if(c.clone_id < 0)
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
			objects = cloneLevel();
		} catch(IOException e)
		{
			System.out.println("could not load level");
		}
	}

	public static void linkPlatforms()
	{
		for(GameObject obj: objects)
			if(obj.getID() == ObjectID.Platform){
				System.out.println(((Platform)obj).getPlatID());
				((Platform) obj).setButton(findPressurePad(((Platform)obj).getPlatID()));
			}
				
	}
	
	public static PressurePad findPressurePad(int id) {
		
		PressurePad pp = null; 
		for(GameObject obj: objects)
			if(obj.getID() == ObjectID.Pad)
			{
				PressurePad p = (PressurePad) obj;
				if(p.getPad_id() == id)
				{
					pp = p;
					break;
				}
			}
		return pp;
	}
}
