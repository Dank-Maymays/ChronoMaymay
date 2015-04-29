package framework;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import objects.Block;
import objects.Door;
import objects.Part;
import objects.Platform;
import objects.Player;
import objects.PressurePad;

public class Level {

	private int parts;
	private int max_clones;
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public Level(LinkedList<GameObject>objects, int max_clones)
	{
		this.objects = (LinkedList<GameObject>) objects.clone();
		this.max_clones = max_clones;
	}
	
	public Level(BufferedImage img, int max_clones) {
		this.max_clones = max_clones;
		parts = 0;
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] pixels = new int[width][height];
		
		for(int row = 0; row < width; row++)
			for(int col = 0; col < height; col++)
			{
				pixels[row][col] = img.getRGB(row,col);
			}
		for(int row = 0; row < width; row++)
			for(int col = 0; col < height; col++)
			{
				Color pixel = new Color(pixels[row][col]);
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				int a = (pixels[row][col]>>24)&0xFF;
				//System.out.println("meme");
				if(r == 0 && g == 0 && b == 0 && a != 0){
					objects.add(new Block(64*row,64*col)); //TODO implement image level loader
				}
				if(r == 1 && g <10 && b == 0) objects.add(new Part(64*row,64*col,++parts));
				if(r == 2 && g < 2 && b < 10) objects.add(new Door(64*row,64*col,256,256,g,b));
				if(r == 3){
					System.out.println("player");
					Player p = new Player(64*row,64*col,256,256);
					Handler.setPlayer(p);
					objects.add(p);
				}
				if(r == 4 && g < 2) objects.add(new Platform(64*row, 64*col, g, b));
				if(r == 5) objects.add(new PressurePad(64*row,64*col,b));

			}

	}

	public int getParts() {
		return parts;
	}

	public int getMax_clones() {
		return max_clones;
	}

	public LinkedList<GameObject> getObjects() {
		return objects;
	}

}
