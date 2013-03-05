package javagame;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;


public class MyServer extends JFrame{
	
	public JTextArea status;
	
	public MyServer () { }

	public static void main(String[] args) {
		MyServer ms = new MyServer ();
		ms.createGUI();
		ms.connect();
	}
	
	public void createGUI() {
		JPanel panel = new JPanel (new BorderLayout());
		JLabel label = new JLabel ("STREK UPDATES");
		status = new JTextArea (300, 300);
		status.setEditable(false);
		panel.add(label, BorderLayout.NORTH);
		panel.add(status, BorderLayout.CENTER);
		JFrame frame = new JFrame ();
		frame.setTitle("STREK");
		frame.add(panel);
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void connect() {
		try {
			status.append("STREK$ is now online on PORT 8888.\n");
			ServerSocket ssocket = new ServerSocket(8888);
			for(int player = 1; player <= 2; player++) {
				status.append("STREK$ is waiting for people to play.\n");
				Socket socket = ssocket.accept();
				status.append("STREK$ Player" + player + ": " + /*socket.getInetAddress()*/ "erw"
						+ " is ready to play.\n");
				
				//NETWORKING CODE
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
