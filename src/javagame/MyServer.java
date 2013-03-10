package javagame;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
			status.append("Starting STREK$ server...\n");
			ServerSocket ssocket = new ServerSocket(8888);
			status.append("STREK$ is waiting for people to play.\n");
			
			List<ServerThread> clientConn = new ArrayList<ServerThread>();
			Socket socket;
			ServerThread st;
			int clientNum = 1;
			
			while(true) {
				socket = ssocket.accept();
				st = new ServerThread(status, clientConn, socket, clientNum++);
				st.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
