package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	public Menu(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN 250,250
		Image bg = new Image("res/BG.png");
		g.drawImage(bg, 0, 0); //theobject, x, y
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 0; //because menu = 0
	}
}
