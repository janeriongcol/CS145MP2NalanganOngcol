package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Choose extends BasicGameState{
	
	Image bg, title, man1, man2, man3;
	
	public Choose(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		bg = new Image("res/BG.png");
		title = new Image("res/CHOOSEAVATAR.png");
		man2 = new Image("res/man2/MAN2.png");
		man1 = new Image("res/man1/MAN1.png");
		man3 = new Image("res/man3/MAN3.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		g.drawImage(bg, 0, 0); 
		g.drawImage(title, 0, 0);
		g.drawImage(man2, 6, 80);
		g.drawImage(man1, 170, 169);
		g.drawImage(man3, 326, 80);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 1; //because choose = 1
	}
}
