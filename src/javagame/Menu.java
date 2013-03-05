package javagame;

import javax.swing.JOptionPane;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	public Menu(int state) {
		//ESTABLISH CONNECTION WITH SERVER HERE
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		Image bg = new Image("res/MENU.png");	
		Image title = new Image("res/TITLE.png");
		Image connect = new Image("res/CONNECT.png");
		g.drawImage(bg, 0, 0); 
		g.drawImage(title, 0, 0);
		g.drawImage(connect, 172, 420);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 0; //because menu = 0
	}
}
