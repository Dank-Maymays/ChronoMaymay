package framework;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import objects.Block;
import objects.Part;

public class Level {

	private int parts;
	private int max_clones;
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public Level(BufferedImage img, int max_clones) {
		this.max_clones = max_clones;
		int parts = 1;
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] pixels = new int[height][width];
		
		for(int row = 0; row < height; row++)
			for(int col = 0; col < width; col++)
			{
				pixels[row][col] = img.getRGB(row,col);
			}
		for(int row = 0; row < height; row++)
			for(int col = 0; col < width; col++)
			{
				int pixel = pixels[row][col];
				int r = (pixel)&0xFF;
				int g = (pixel>>8)&0xFF;
				int b = (pixel>>16)&0xFF;
				int a = (pixel>>24)&0xFF;
				
				if(r == 0 && g == 0 && b == 0 && a != 0) objects.add(new Block(64*row,64*col)); //TODO implement image level loader
				if(r == 0 && g == 0 && b == 255 && a == 255) objects.add(new Part(32*row,32*col,parts++));
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
