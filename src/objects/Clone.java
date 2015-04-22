package objects;

import framework.Action;
import framework.Instructions;

public class Clone extends Player{

	Instructions instruct;
	boolean left=false,right=false,jump=false;
	
	public Clone(Instructions list, int x, int y) {
		super(x,y,256,256);
		instruct = list;
	}
	
	public void tick()
	{
		
	}

}
