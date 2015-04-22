package framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level {

	private int parts;
	private int max_clones;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Level(BufferedImage img, int max_clones) {
		this.parts = parts;
		this.max_clones = max_clones;
		
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
				
				
			}
	}

}
