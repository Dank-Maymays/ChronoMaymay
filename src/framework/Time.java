package framework;

import org.lwjgl.Sys;

public class Time {

	private boolean paused = false;
	public long lastFrame, totalTime;
	private boolean firstUpdate = true;
	public float d = 0, multiplier = 1;
	
	public long getTime()
	{
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public float getDelta()
	{
		int delta = (int) (getTime() - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public float Delta()
	{
		if (paused || firstUpdate)
		{
			firstUpdate = false;
			return 0;
		}else
			return d * multiplier;
	}
	
	public long totalTime()
	{
		return totalTime;
	}
	
	public float multiplier()
	{
		return multiplier;
	}
	
	public void update()
	{
		d = getDelta();
		totalTime += d;
	}
	
	public void changeSpeed(int speed)
	{
		if(speed > -1 && speed < 5)
			multiplier = speed;
	}
	
	public void pause()
	{
		paused = !paused;
	}
	
	public long lastFrame(){
		return lastFrame;
	}
}
