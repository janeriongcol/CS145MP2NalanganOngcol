package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	Image bg, rock, spock, scissors, paper, lizard, heart1, heart2, heart3, heart4, heart5;
	
	public Play(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		bg = new Image("res/BG.png");
		rock = new Image("res/attacks/ROCK.png");
		spock = new Image("res/attacks/SPOCK.png");
		scissors = new Image("res/attacks/SCISSORS.png");
		paper = new Image("res/attacks/PAPER.png");
		lizard = new Image("res/attacks/lizard.png");
		heart1 = new Image("res/heart.png");
		heart2 = new Image("res/heart.png");
		heart3 = new Image("res/heart.png");
		heart4 = new Image("res/heart.png");
		heart5 = new Image("res/heart.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		g.drawImage(bg, 0, 0); 
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
	}
	
	public int getID() {
		return 2; //because play = 2
	}
}
