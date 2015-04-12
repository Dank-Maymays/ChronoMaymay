package framework;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Animation {

	private ArrayList<Texture> frames = new ArrayList<Texture>();
	private int frame = 0;

	public Animation(ArrayList<Texture> frames)
	{
		this.frames = frames;
	}
	
	public Texture nextFrame()
	{
		loopFrame();
		return frames.get(frame++);
	}
	public Texture lastFrame()
	{
		loopFrame();
		return frames.get(frame--);
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
