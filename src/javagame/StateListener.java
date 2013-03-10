package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

class StateManager extends StateBasedGame{
	
	public static final String gamename = "Bato Bato Pick and the Lizard Spock in 3D";
	public static final int menu = 0; //Menu Screen
	public static final int choose = 1; //Choose Character Screen
	public static final int play = 2; //Gameplay Screen 
	public static final int cham = 3; //Cham Cham Cham Screen
	
	public StateManager() {
		super(gamename); //Add title to title bar
		//Add each state/screen individually
		this.addState(new Menu(menu));
		this.addState(new Choose(choose));
		this.addState(new Play(play));
		this.addState(new Cham(cham));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(choose).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(cham).init(gc, this);
		//Show Menu Screen first
		this.enterState(menu);
	}
}

public enum StateListener {
	INSTANCE;
	StateManager stateManager;
	
	StateListener(){
		stateManager = new StateManager();
	}
}
