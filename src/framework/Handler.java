package framework;

import java.util.ArrayList;

public class Handler {
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public static void addObject(GameObject object){
		objects.add(object);
	}
	
	public static void addObject(int index, GameObject object){
		objects.add(index,object);
	}
	
	public static GameObject removeObject(int index){
		return objects.remove(index);
	}
	
	public static ArrayList<GameObject> getObjects(){
		return objects;
	}
}
