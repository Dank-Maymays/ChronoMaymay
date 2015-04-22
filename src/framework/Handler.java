package framework;


import java.util.ArrayList;
import java.util.LinkedList;

import objects.Player;

public class Handler {
	private static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private static LinkedList<Player> players = new LinkedList<Player>(); 

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
}
