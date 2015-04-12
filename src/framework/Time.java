package framework;

import org.lwjgl.Sys;

public class Time {

	private static boolean paused = false;
	public static long lastFrame, totalTime;
	private static boolean firstUpdate = true;
	public static float d = 0, multiplier = 1;
	
	public static long getTime()
	{
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float getDelta()
	{
		int delta = (int) (getTime() - lastFrame);
		lastFrame = getTime();
		return delta * 0.01f;
	}
	
	public static float Delta()
	{
		if (paused || firstUpdate)
		{
			firstUpdate = false;
			return 0;
		}else
			return d * multiplier;
	}
	
	public static float totalTime()
	{
		return totalTime;
	}
	
	public static float multiplier()
	{
		return multiplier;
	}
	
	public static void update()
	{
		d = getDelta();
		totalTime += d;
	}
	
	public static void changeSpeed(int speed)
	{
		if(speed > -1 && speed < 5)
			multiplier = speed;
	}
	
	public static void pause()
	{
		paused = !paused;
	}
}
