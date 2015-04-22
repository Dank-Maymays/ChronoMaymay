package framework;

public class Instruction {
	//time & command variables
	private long time;
	private Action action;
	
	public Instruction(long t, Action a){
		time = t;
		action = a;
	}
	
	public long getTime(){
		return time;
	}
	
	public Action getAction(){
		return action;
	}
}
