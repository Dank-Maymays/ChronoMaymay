package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
public class Player extends GameObject{
	
	
	public Player(){
		x=150;
		y=300;
		texture = loadTexture("res","sprite_1.png");
	}
	
	public void render(){
		if(texture != null)
		{
			Draw.drawQuad(x, y, width, height, texture);
		}else{
			Draw.drawQuad(x, y, width, height);
		}
	}

	private Texture loadTexture(String folder, String file){
		try {
			return TextureLoader.getTexture("PNG",new FileInputStream(new File(folder + "/" + file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}