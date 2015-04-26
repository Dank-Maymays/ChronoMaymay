package framework;

import java.util.ArrayList;

public class Instructions {

	private ArrayList<Instruction> cur_instruct = new ArrayList<Instruction>();
	private Time time;
	private long currentTime = 0, totalTime = 0;
	private int index = 0;
	/**
	 * a group of instructions for a clone to follow
	 * @param list the list of instructions
	 * @param time the time of the instruction
	 */
	public Instructions(ArrayList<Instruction> list, long time) {
		cur_instruct = list;
		totalTime = time;
		this.time = new Time();
	}
	/**
	 * gets a list of actions as commands
	 * @return the list of actions
	 */
	public ArrayList<Action> getCommands()
	{
		ArrayList<Action> commands = new ArrayList<Action>();
		while(index < cur_instruct.size() &&currentTime >= cur_instruct.get(index).getTime())
		{
			commands.add(cur_instruct.get(index).getAction());
			index++;
		}
		return commands;
	}
	/**
	 * resets the index of the commands and the time to 0
	 */
	public void reset()
	{
		index = 0;
		currentTime = 0;
	}
	/**
	 * updates the time for the instructions
	 */
	public void update()
	{
		time.update();
		currentTime += time.Delta();
	}
	
	public long getTotalTime()
	{
		return totalTime;
	}
	
	public long getTime()
	{
		return currentTime;
	}
	
}
