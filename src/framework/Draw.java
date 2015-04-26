package framework;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Draw {

	public static final int WIDTH = 1280, HEIGHT = 720; 												//CONTROLS WIDTH AND HEIGHT OF SCREEN
	/**
	 * sets up the lwjgl java build path and natives for lwjgl.jar
	 */
	public static void Setup() 																			//SETS UP THE DISPLAY AND NATIVES
	{
		if(System.getProperty("os.name").contains("Windows")) 											// If the operating system we're on is a windows,
			System.setProperty("org.lwjgl.librarypath", new File("natives/windows").getAbsolutePath()); // Load the natives for Windows
		else if(System.getProperty("os.name").contains("Mac")) 											// If the operating system we're on is a mac,
			System.setProperty("org.lwjgl.librarypath", new File("natives/macosx").getAbsolutePath());  // Load the natives for Mac
		else // otherwise,
		{
			System.out.println("Your OS is not supported"); 					// Print to console that the OS is not supported.
			System.exit(0); 													// Close the program.
		}
		
		Display.setTitle("Chrono MayMay"); 										// Set the title to whatever String is passed in
		try{ 																	// Try to ...
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT)); 				// Set the display dimensions to WIDTH and HEIGHT.
			Display.create(); 													// Load the display.
		} catch(LWJGLException e) 												// If something goes wrong,
		{
			e.printStackTrace(); 												//Print dat stack.
		}
		
		glMatrixMode(GL_PROJECTION); 											// Sets the matrix mode to GL_PROJECTION to set up the coordinate system.
		glLoadIdentity(); 														// Useful if you want your game not to fail.
		glOrtho(0,WIDTH,HEIGHT,0,1,-1); 										//Sets the far left to 0, the far right to WIDTH, down to HEIGHT, and top to 0. 1 and -1 to make it 2D based.
		glMatrixMode(GL_MODELVIEW); 											// Sets the matrix mode to GL_MODELVIEW to allow for drawing.
		
	}
	/**
	 * wrapper for animation, setup
	 */
	public static void startTrans() //WRAPPER FUNCTION TO MAKE LIFE EASIER
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	/**
	 * wrapper for animation, finish
	 */
	public static void endTrans() //WRAPPER FUNCTION TO MAKE LIFE EASIER
	{
		glDisable(GL_BLEND);
	}
	/**
	 * sets up background
	 */
	public static void Background() 											// Draws the background
	{
		glColor3f(0.3f,0.3f,0.3f); 												//Set color to grayish.
		drawQuad(WIDTH/10,HEIGHT/10,WIDTH/10*8,HEIGHT); 						// Sets dimensions depending on window size.
		glColor3d(1,1,1); 														// Resets the color back to white.
	}
	/**
	 * draws a quad shape at given x and y with the given width and height
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param width width of quad
	 * @param height height of quad
	 */
	public static void drawQuad(float x, float y, float width, float height) 	//Draws a single color quad
	{
		glDisable(GL_TEXTURE_2D); 												//Makes sure texture mode is off to make sure you don't get weird errors.
		
		glBegin(GL_QUADS);
		
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		
		glEnd();
	}
	/**
	 * draws a quad shape at given x and y with the given width and height with given texture
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param width width of quad
	 * @param height height of quad
	 * @param texture the texture to be drawn
	 */
	public static void drawQuad(float x, float y, float width, float height, Texture texture) //Draws a quad with a texture binded to it.
	{		
		glEnable(GL_TEXTURE_2D); 															  // Makes sure texture mode is on to make sure you can use textures.
		texture.bind();	  																	  //Binds the texture to be used for drawing.
		glTranslatef(x,y,0);																  //Sets 0, 0 to x and y, as in the new 0, 0 is at x and y.
		
		glBegin(GL_QUADS); //Draw square with textures
		
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		
		glEnd(); //Stop drawing
		
		glLoadIdentity(); //probably useful if you dont want game to crash
	}
	/**
	 * draws a quad shape at given x and y with the given width and height with given RGB color values
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param width width of quad
	 * @param height height of quad
	 * @param r red color value
	 * @param g green color value
	 * @param b blue color value
	 */
	public static void drawQuad(float x, float y, float width, float height, float r, float g, float b) //Draws quad with a certain RGB color
	{
		glDisable(GL_TEXTURE_2D);
		
		glBegin(GL_QUADS);
		
		glColor3f(r,g,b);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		glColor3f(1,1,1);
		
		glEnd();
	}
	/**
	 * loads a texture from the given path
	 * @param path file path
	 * @param type extension, mainly .png
	 * @return the texture loaded
	 */
	public static Texture loadTexture(String path, String type) //returns null if texture is not found
	{
		try {
			return TextureLoader.getTexture(type,new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * shortcut for the loadTexture method, give it the foldername/filename
	 * @param name
	 * @return
	 */
	public static Texture quickLoad(String name)
	{
		return loadTexture("res/"+name+".png","png");
	}
	
}
