import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;


public class Main implements Runnable {

	private int width = 1280;
	private int height = 720;
	
	private Thread thread;
	private boolean running;
	
	private long window;
	
	public void start()
	{
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	private void init()
	{
		if(glfwInit() != GL11.GL_TRUE)
		{
			// TODO: do stuff
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width,height, "Chromo", NULL, NULL);
		
		if(window == 0)
		{
			//TODO: work it out
			return;
		}
		
		
		ByteBuffer vmove = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, GLFWvidmode.width(vmove) - width, GLFWvidmode.height(vmove) - height);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}
	
	public void run() {
		init();
		while(running){
			tick();
			render();
			
			if(glfwWindowShouldClose(window) == GL_TRUE)
				running = false;
		}
	}
	
	private void tick()
	{
		
	}
	
	private void render()
	{
		glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();

	}

}
