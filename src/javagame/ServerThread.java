package javagame;

import java.net.Socket;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JTextArea;

public class ServerThread extends Thread {
	List<ServerThread> clientConn;
	JTextArea log;
	MyConnection conn;
	Socket socket;
	String name;
	String status;
	String opponent;
	
	public ServerThread( JTextArea t, List<ServerThread> c, Socket s, int n ) {
		this.log = t;
		this.clientConn = c;
		this.socket = s;
		this.name = "RandomPlayer" + n;
		this.opponent = null;
	}
	
	public void run() {
		this.conn = new MyConnection(socket);
		this.clientConn.add(this);
		conn.sendMessage("/name " + this.name);
		broadcast("STREK$ Player " + this.name + " (IP:" + socket.getInetAddress() + ")"
				+ " is ready to play.\n", true);
		updateList();
			
		engageClient(conn);
		
		clientConn.remove(this);
		updateList();
	}
	
	public void engageClient(MyConnection conn) {
		String msg;
		
		do {
			msg = conn.getMessage();
			if( msg.startsWith("/changename ") ) {
				String []arr = msg.split(" ", 2);
				if (arr[1].split(" ").length != 1) {
					conn.sendMessage("Server message: Don't use whitespace please.");
				} else if ( connection(arr[1]) == null ) {
					broadcast(name + " has changed name to " + arr[1], true);
					this.name = arr[1];
					conn.sendMessage("/name " + this.name);
					updateList();
				} else {
					conn.sendMessage("Server message: That name is already taken.");
				}
			} else if( msg.startsWith("/changestatus ") ) {
				msg = msg.replace("/changestatus ", "");
				this.status = msg;
				updateList();
				broadcast("Server message: " + this.name + " has changed status to \"" + this.status + "\"", true);
			} else if( msg.startsWith("/whisper ") ) {
				String []arr = msg.split(" ", 3);
				ServerThread s = connection(arr[1]);
				if(s == null) {
					conn.sendMessage("Server message: No user with name \"" + arr[1] + "\" exists.");
				} else if( s.getUser().equals(name) ) {
					conn.sendMessage("Server message: Sorry, you can't whisper for yourself.");
				} else {
					s.getConnectObject().sendMessage("["+this.name +" whispers]: " + arr[2]);
					this.conn.sendMessage("["+this.name +" whispers]: " + arr[2]);
				}
			} else if( msg.equals("/quit") ) {
				broadcast("Server message: " + this.name + " has disconnected.", true);
				conn.sendMessage("/quit");
			} else if( msg.startsWith("/") ){
				String []arr = msg.split(" ", 2);
				conn.sendMessage("Server message: Invalid command " + arr[0]);
			} else {
				broadcast(this.name + ": " + msg, false);
			}
		} while( !msg.equals("/quit") );
	}
	
	public String getUser() {
		return this.name;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public MyConnection getConnectObject() {
		return this.conn;
	}
	
	public void updateList() {
		ServerThread c;
		
		for (ListIterator<ServerThread> ite = clientConn.listIterator(); ite.hasNext();) {
			c = ite.next();
			c.getConnectObject().sendMessage("/clientList");
			for( ListIterator<ServerThread> it = clientConn.listIterator(); it.hasNext(); ) {
				ServerThread t = it.next();
				c.getConnectObject().sendMessage(t.getUser());
				if(t.getStatus() != null) c.getConnectObject().sendMessage(" - " + t.getStatus());
				c.getConnectObject().sendMessage("/endUser");
			}
			c.getConnectObject().sendMessage("/endClientList");
		}
	}
	
	public void broadcast(String s, boolean showMessage) {
		if(showMessage) log.append(s);
		for (ListIterator<ServerThread> it = clientConn.listIterator(); it.hasNext();)
			it.next().getConnectObject().sendMessage(s);
	}
	
	public ServerThread connection(String name) {
		for (ListIterator<ServerThread> it = clientConn.listIterator(); it.hasNext();) {
			ServerThread c = it.next();
			if(c.getUser().equals(name)) return c;
		}
		return null;
	}
}