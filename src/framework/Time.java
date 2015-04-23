package framework;

import org.lwjgl.Sys;

public class Time {

	private boolean paused = false; //Is the time paused.
	public long lastFrame, totalTime = 0; //lastFrame = time value the last time this was updated. totalTime = the total time.
	private boolean firstUpdate = true; //Used to see if its the first update, to avoid weird delta errors.
	public float d = 0, multiplier = 1;//d is the delta, multiplier is the speed multiplier.
	
	public long getTime()
	{
		return Sys.getTime() * 1000 / Sys.getTimerResolution(); //Gets the current time in milliseconds
	}
	
	public float getDelta()
	{
		int delta = (int) (getTime() - lastFrame); //Gets the difference between the current time and the last time it was updated.
		lastFrame = getTime(); //Sets the lastFrame to the current time (updates it).
		return delta; //returns it.
	}
	
	public void reset()
	{
		totalTime = 0;
		lastFrame = 0;
		firstUpdate = true;
		d = 0;
		
	}
	
	public float Delta()
	{
		if (paused || firstUpdate) //If the game is paused or its the first update (to avoid weird delta errors),
		{
			firstUpdate = false; //Set the first update to false.
			return 0; //Return 0.
		}else
			return d * multiplier; // Returns the delta multiplied by the multiplier to affect the speed.
	}
	
	public long totalTime() //Returns totalTime.
	{
		return totalTime;
	}
	
	public float multiplier() //Returns multiplier.
	{
		return multiplier;
	}
	
	public void update() //Updates the delta and totalTime.
	{
		d = getDelta();
		totalTime += d;
	}
	
	public void changeSpeed(int speed) //Changes the speed.
	{
		if(speed > -1 && speed < 5)
			multiplier = speed;
	}
	
	public void pause() //Pauses and unpauses.
	{
		paused = !paused;
	}
	
	public long lastFrame(){ //returns lastFrame.
		return lastFrame;
	}
}
