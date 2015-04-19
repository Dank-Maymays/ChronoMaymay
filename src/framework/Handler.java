package framework;

<<<<<<< HEAD
import java.util.ArrayList;

public class Handler {
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
=======
import java.util.LinkedList;

public class Handler {
	private static LinkedList<GameObject> objects = new LinkedList<GameObject>();
>>>>>>> origin/master
	
	public static void addObject(GameObject object){
		objects.add(object);
	}
	
<<<<<<< HEAD
	public static void addObject(int index, GameObject object){
		objects.add(index,object);
=======
	public static GameObject getObject(int index){
		return objects.get(index);
>>>>>>> origin/master
	}
	
	public static GameObject removeObject(int index){
		return objects.remove(index);
	}
	
<<<<<<< HEAD
	public static ArrayList<GameObject> getObjects(){
=======
	public static LinkedList<GameObject> getObjects(){
>>>>>>> origin/master
		return objects;
	}
}
