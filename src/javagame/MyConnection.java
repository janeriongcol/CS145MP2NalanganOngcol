package javagame;

import java.io.*;
import java.net.*;

public class MyConnection {
	Socket socket;
	PrintWriter w;
	BufferedReader r;

	public MyConnection( Socket s ) {
		this.socket = s;
		try {
			this.w = new PrintWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			this.r = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public boolean sendMessage( String s ) {
		w.println(s);
		w.flush();
		return true;
	}
	
	public String getMessage() {
		try {
			return r.readLine();
		}
		catch( Exception e ) {
			e.printStackTrace();
			return "";
		}
	}
}
