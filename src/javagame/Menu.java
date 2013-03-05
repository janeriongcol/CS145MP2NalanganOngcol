package javagame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image bg, title, connect;
	JTextField ipaddFld, portFld;
	String mouse = "";
	
	public Menu(int state) {
		//ESTABLISH CONNECTION WITH SERVER HERE
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		bg = new Image("res/MENU.png");	
		title = new Image("res/TITLE.png");
		connect = new Image("res/CONNECT.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//DISPLAY STUFF ON SCREEN
		g.drawImage(bg, 0, 0); 
		g.drawImage(title, 0, 0);
		g.drawImage(connect, 172, 420);
		g.drawString(mouse, 300, 300);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//UPDATE IMAGES ON SCREEN
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		Input input = gc.getInput();
		
		//mouse = "x = " + xpos + " y = " + ypos;
		
		//Check if CONNECT is clicked
		if((xpos > 175 && xpos < 320) && (ypos < 70 && ypos > 40))
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				JFrame frame = new JFrame();
				JPanel ipaddPnl = new JPanel(new BorderLayout());
				JPanel portPnl = new JPanel(new BorderLayout());
				JPanel container = new JPanel(new BorderLayout());
				
				ipaddFld = new JTextField(10);
				portFld = new JTextField(5);
				
				JButton ok = new JButton("Connect");
				
				JLabel ipaddLbl = new JLabel("Enter STREK's IP Address");
				JLabel portLbl = new JLabel("Enter STREK's Port Number");
				
				ipaddPnl.add(ipaddLbl, BorderLayout.NORTH);
				ipaddPnl.add(ipaddFld, BorderLayout.CENTER);
				
				portPnl.add(portLbl, BorderLayout.NORTH);
				portPnl.add(portFld, BorderLayout.CENTER);
				portPnl.add(ok, BorderLayout.AFTER_LAST_LINE);
				
				container.add(ipaddPnl, BorderLayout.NORTH);
				container.add(portPnl, BorderLayout.SOUTH);
				
				frame.setTitle("Connect To STREK");
				frame.add(container);
				frame.setSize(250, 150);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				
			}
		
		
	}
	
	public int getID() {
		return 0; //because menu = 0
	}
}
