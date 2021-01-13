package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * This class creates a server port and takes in clients
 * @author Karlen Chow
 * @version 1.0
 * @since November 29, 2020
 *
 */
public class ServerController {
	private Socket socket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ExecutorService pool;
	
	public ServerController() {
		try {
			serverSocket = new ServerSocket(9898);
			pool = Executors.newFixedThreadPool(10);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runServer() {
		try {
			while(true) {
				socket = serverSocket.accept();
				System.out.println("Server is running..");
				socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socketOut = new PrintWriter(socket.getOutputStream(), true);
				DBController db = new DBController();
				ModelController m = new ModelController(socketOut, socketIn,db);
				pool.execute(m);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
	public static void main(String[] args) {
		ServerController s = new ServerController();
		s.runServer();
	}

}
