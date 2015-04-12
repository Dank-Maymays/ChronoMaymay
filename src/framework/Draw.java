package framework;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Draw {

	public static void drawQuad(double x, double y, double width, double height)
	{
		glBegin(GL_QUADS);
		
		glColor3d(0.5,0.5,0.5);
		glVertex2d(x,y);
		glVertex2d(x+width,y);
		glVertex2d(x+width,y+height);
		glVertex2d(x,y+height);
		
		glEnd();
	}
	
	public static void drawQuad(double x, double y, double width, double height, Texture texture)
	{
		glPushMatrix();
		glBegin(GL_QUADS);
		
		texture.bind();
		glColor3d(0.5,0.5,0.5);
		glTexCoord2d(0,0);
		glVertex2d(x,y);
		glTexCoord2d(1,0);
		glVertex2d(x+width,y);
		glTexCoord2d(1,1);
		glVertex2d(x+width,y+height);
		glTexCoord2d(0,1);
		glVertex2d(x,y+height);
		
		glEnd();
		glPopMatrix();

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
