package javagame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Receiver extends Thread implements WindowListener {
	ChatWindow chatWindow;
	MyConnection conn;
	String msg;
	CyclicBarrier gate;

	Receiver( ChatWindow f, MyConnection c, CyclicBarrier g ) {
		this.chatWindow = f;
		this.conn = c;
		this.gate = g;
		f.addWindowListener(this);
	}
	public void run() {
		try {
			gate.await();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		while(true) {
			msg = conn.getMessage();
			if( msg != null ) {
				if(msg.equals("/clientList")) updateList();
				else if(msg.equals("/quit")) break;
				else if(msg.startsWith("/name ")) {
					String []arr = msg.split(" ", 2);
					chatWindow.setTitle(arr[1] + "'s Client");
				}
				else chatWindow.getChatPane().append(msg+"\n");
			}
		}
		
		System.exit(0);
	}
	
	public void updateList() {
		chatWindow.getClientListPane().setText("");
		String s = conn.getMessage();
		do {
			if(s.equals("/endUser")) s = "\n";
			chatWindow.getClientListPane().append(s);
			s = conn.getMessage();
		} while(!s.equals("/endClientList"));
	}
	
	public void windowOpened(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}	
	public void windowClosing(WindowEvent e) {
		conn.sendMessage("/quit");
	}
}

class Sender extends Thread implements ActionListener, KeyListener {
	ChatWindow chatWindow;
	MyConnection conn;
	String msg;
	CyclicBarrier gate;
	
	Sender( ChatWindow f, MyConnection c, CyclicBarrier g ) {
		this.chatWindow = f;
		this.conn = c;
		this.gate = g;
		this.msg = "";
		f.getSendButton().addActionListener(this);
		f.getFieldObject().addKeyListener(this);
	}
	public void run() {
		try {
			gate.await();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		while(true) {
			if(msg.equals("/quit")) break;
		}
	}
	
	public void sendMessage() {
		msg = chatWindow.getFieldObject().getText();
		conn.sendMessage(msg);
		chatWindow.getFieldObject().setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		this.sendMessage();
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e) {
		if( e.getKeyChar() == e.VK_ENTER ) this.sendMessage();
	}
}

class ChatWindow extends JFrame {
	JTextArea chatArea;
	JTextArea clientList;
	JTextField query;
	JButton sendButton;
	
	public void initComponents(Container pane) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 15, 0, 5);
		c.weightx = 0.8;
		add(new JLabel("Chat Window"), c);
		chatArea = new JTextArea();
		chatArea.setMinimumSize(new Dimension(350, 200));
		chatArea.setEditable(false);
		c.gridy = 1;
		c.weighty = 1.0;
		c.insets = new Insets(5, 15, 5, 5);
		add(new JScrollPane(chatArea), c);
		c.gridy = 2;
		c.weighty = 0;
		c.insets = new Insets(5, 15, 15, 5);
		query = new JTextField();
		add(query, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 0;
		c.insets = new Insets(10, 5, 0, 15);
		add(new JLabel("Online clients"), c);
		clientList = new JTextArea();
		clientList.setEditable(false);
		c.gridy = 1;
		c.weighty = 1.0;
		c.insets = new Insets(5, 5, 5, 15);
		add(new JScrollPane(clientList), c);
		c.gridy = 2;
		c.weighty = 0;
		c.insets = new Insets(5, 5, 15, 15);
		sendButton = new JButton("Send");
		add(sendButton, c);
	}	

	public JTextArea getChatPane() {
		return this.chatArea;
	}
	
	public JTextField getFieldObject() {
		return this.query;
	}
	
	public JTextArea getClientListPane() {
		return this.clientList;
	}
	
	public JButton getSendButton() {
		return this.sendButton;
	}
	
	public ChatWindow() {
		this.setTitle("Conference Chat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 400));
		this.setLayout(new GridBagLayout());
		initComponents(this.getContentPane());
		this.pack();
	}
	
}

public enum ClientListener {
	INSTANCE;
	MyConnection conn;
	ChatWindow console;
	Sender sender;
	Receiver receiver;
	
	ClientListener() {
		try {
			System.out.println("Client: Connecting to server...");
			Socket socket = new Socket("127.0.0.1",8888);
			System.out.println("Client: Connected! \\^_^/");
			conn = new MyConnection(socket);
			console = new ChatWindow();
			
			CyclicBarrier gate = new CyclicBarrier(2);
			sender = new Sender(console, conn, gate);
			receiver = new Receiver(console, conn, gate);
			
			receiver.start();
			sender.start();
			console.setVisible(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
