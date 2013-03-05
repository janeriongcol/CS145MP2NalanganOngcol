package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Cham extends BasicGameState{
	
	Image bg;
	
	public Cham(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		bg = new Image("res/BG.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		g.drawImage(bg, 0, 0);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 3; //because cham = 3
	}
}
