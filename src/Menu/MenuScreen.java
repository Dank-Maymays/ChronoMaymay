package Menu;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import framework.Draw;
public class MenuScreen {
	private Button play;
	private Button exit;
	
	public MenuScreen(){
		play = new Button(490,250,300,150,"play");
		//exit = new Button(490,150,300,150,"play");
	}
	
	public void drawMenu(){
		Draw.drawQuad(0, 0, 1280, 720,0,0,0);
		play.drawButton();
		//exit.drawButton();
	}
	public void start() {
         
        // init OpenGL here
         
        while (!Display.isCloseRequested()) {
             
            drawMenu();
            
            Display.update();
            Display.sync(60);
            System.out.println("Play: " + play.isPressed());
            //System.out.println("Exit: " + exit.isPressed());
        }
         
        Display.destroy();
    }
     
    public static void main(String[] args) {
    	Draw.Setup();
        MenuScreen displayExample = new MenuScreen();
        displayExample.start();
        
    }
}
