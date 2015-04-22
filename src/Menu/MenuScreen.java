package Menu;

import java.util.ArrayList;

public class MenuScreen {
	private Button play;
	private Button exit;
	
	public MenuScreen(){
		play = new Button(490,350,300,150);
		exit = new Button(490,150,300,150);
	}
	
	public void drawMenu(){
		play.drawButton();
		exit.drawButton();
	}
}
