package framework;

public class Instruction {
	//time & command variables
	private long time;
	private String command;
	
	public Instruction(long t, String s){
		time = t;
		command = s;
	}
	
	public long getTime(){
		return time;
	}
	
	public String getCommand(){
		return command;
	}
}
