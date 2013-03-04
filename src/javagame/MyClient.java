package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/*
 * Master class that controls everything.
 */

public class MyClient extends StateBasedGame{
	
	public static final String gamename = "LIZARD SPOCK 3D!";
	public static final int menu = 0; //Menu Screen
	public static final int choose = 1; //Choose Character Screen
	public static final int play = 2; //Gameplay Screen
	
	public MyClient(String gamename) {
		super(gamename); //Add title to title bar
		//Add each state/screen individually
		this.addState(new Menu(menu));
		this.addState(new Choose(choose));
		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(choose).init(gc, this);
		this.getState(play).init(gc, this);
		//Show Menu Screen first
		this.enterState(menu);
	}
	
	public static void main(String args[]) {
		AppGameContainer appgc;
		try {
			//Create the window that will hold the game
			appgc = new AppGameContainer(new MyClient(gamename));
			appgc.setDisplayMode(500, 500, false); //false for NO FULLSCREEN
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

}
