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

	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static void Setup()
	{
		Display.setTitle("Chrono MayMay");
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create();
		} catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,WIDTH,HEIGHT,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}
	
	public static Texture quickTexture(String loc){
		Texture tex = null;
		
		InputStream in = ResourceLoader.getResourceAsStream("res/"+loc+".png");
		try {
			tex = TextureLoader.getTexture("PNG", in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tex;

	}
	
	public static void drawQuad(float x, float y, float width, float height)
	{
		
		glBegin(GL_QUADS);
		
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		
		glEnd();
	}
	
	public static void drawQuad(float x, float y, float width, float height, Texture texture)
	{				
		texture.bind();		
		glTranslatef(x,y,0);
		
		glBegin(GL_QUADS);
		
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		
		glLoadIdentity();
		
		glEnd();
	}
	
	public static void drawQuad(float x, float y, float width, float height, float r, float g, float b)
	{
		
		glBegin(GL_QUADS);
		
		glColor3f(r,g,b);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		glColor3f(1,1,1);
		
		glEnd();
	}
	
	public Texture loadTexture(String loc) //returns null if texture is not found
	{
		try {
			return TextureLoader.getTexture("PNG",new FileInputStream(new File(loc)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
