package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	public Play(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		Image bg = new Image("res/BG.png");
		Image rock = new Image("res/attacks/ROCK.png");
		Image spock = new Image("res/attacks/SPOCK.png");
		Image scissors = new Image("res/attacks/SCISSORS.png");
		Image paper = new Image("res/attacks/PAPER.png");
		Image lizard = new Image("res/attacks/lizard.png");
		Image heart1 = new Image("res/heart.png");
		Image heart2 = new Image("res/heart.png");
		Image heart3 = new Image("res/heart.png");
		Image heart4 = new Image("res/heart.png");
		Image heart5 = new Image("res/heart.png");
		g.drawImage(bg, 0, 0); 
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 2; //because play = 2
	}
}
