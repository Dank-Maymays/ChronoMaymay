package framework;

public class Instruction {
	//time & command variables
	private long time;
	private Action action;
	/**
	 * an instruction for a clone to carry out
	 * @param t the time variable for the time length of an instruction
	 * @param a the action the clone will do
	 */
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
