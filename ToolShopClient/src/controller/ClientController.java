package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
/**
 * This class connects from the client to the server and sends/receives information from the server
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class ClientController {
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public ClientController(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			System.out.println("Connection established");
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(),true);
			ModelController m = new ModelController(socketIn, socketOut);
		}catch(UnknownHostException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void communicate() {
		while(true) {}
	}
	
	public static void main(String[] args) {
		try {
			ClientController c = new ClientController("localhost",9898);
			c.communicate();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
}
