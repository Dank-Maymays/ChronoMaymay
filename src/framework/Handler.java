package framework;


import java.util.ArrayList;


import java.util.LinkedList;

public class Handler {
	private static LinkedList<GameObject> objects = new LinkedList<GameObject>();

	
	public static void addObject(GameObject object){
		objects.add(object);
	}
	

	public static void addObject(int index, GameObject object){
		objects.add(index,object);
	}
	public static GameObject getObject(int index){
		return objects.get(index);

	}
	
	public static GameObject removeObject(int index){
		return objects.remove(index);
	}
	

	public static LinkedList<GameObject> getObjects(){

		return objects;
	}
}
