package framework;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static framework.Draw.*;
//import my penis hahaha XD loool
public class Animation {

	private ArrayList<Texture> frames = new ArrayList<Texture>();
	private int fps, frame = 0;
	private Time time = new Time();
	private boolean finished = false, runOnce = false;
	private long animationTime;
	/**
	 * makes a new animation
	 * @param frames the frames of the animation
	 * @param fps the frames per second that the animation runs at
	 */
	public Animation(ArrayList<Texture> frames, int fps)
	{
		this.frames = frames;
		this.fps = fps;
	}
	/**
	 * makes animation
	 * @param path the file path for the png files to make the animation
	 * @param fps the frames per second that the animation runs at
	 */
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
				break;
				
			}
		} while(true);
		animationTime = fps * frames.size();
	}
	
	public boolean firstRun()
	{
		return runOnce;
	}
	
	public boolean isDone()
	{
		return finished;
	}
	
	public void update()
	{
		if(frame > 0 && !runOnce)
			runOnce = true;
		if(runOnce && frame == 0)
			finished = true;
		if(time.getTime() - time.lastFrame() >= 1000/(fps * Game.GAME_TIME.multiplier()))
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
