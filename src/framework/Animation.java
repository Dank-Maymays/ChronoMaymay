package framework;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static framework.Draw.*;

public class Animation {

	private ArrayList<Texture> frames = new ArrayList<Texture>();
	private int fps, frame = 0;
	private Time time = new Time();

	public Animation(ArrayList<Texture> frames, int fps)
	{
		this.frames = frames;
		this.fps = fps;
	}
	
	public Animation(String path, int fps)
	{
		this.fps = fps;
		int count = 1;
		do
		{
			Texture t;
			if((t = loadTexture(path+"/sprite_"+count+".png","PNG") )!= null)
			{
				frames.add(t);
				count++;
			}else
			{
				System.out.println("test");
				break;
				
			}
		} while(true);
	}
	
	public void update()
	{

		if(time.getTime() - time.lastFrame() >= 1000/fps)
		{
			nextFrame();
			time.update();
		}
	}
	
	public Texture getCurrentFrame()
	{
		return frames.get(frame);
	}
	
	public void nextFrame()
	{
		frame++;
		loopFrame();
	}
	
	public void lastFrame()
	{
		frame--;
		loopFrame();
	}
	private void loopFrame()
	{
		frame %= frames.size();
	}
	public Texture getFrame(int frame)
	{
		return frames.get(frame);
	}
	public void setFrame(int frame)
	{
		this.frame = frame;
		loopFrame();
	}
}
