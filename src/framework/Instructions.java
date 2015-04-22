package framework;

import java.util.ArrayList;

public class Instructions {

	private ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	private Time time;
	private long totalTime, currentTime = 0;
	
	public Instructions(ArrayList<Instruction> list, long time) {
		instructions = list;
		totalTime = time;
		this.time = new Time();
	}

	public ArrayList<Action> getCommands()
	{
		ArrayList<Action> commands = new ArrayList<Action>();
		while(instructions.size() > 0 && currentTime >= instructions.get(0).getTime())
			commands.add(instructions.remove(0).getAction());
		return commands;
	}
	
	public void update()
	{
		time.update();
		currentTime += time.Delta();
	}
	
	public long getTime()
	{
		return totalTime;
	}
}
