


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer implements Constants{
	private ExecutorService threaPool;
	private Socket Socket;
	private ServerSocket serviceSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private int countConnections;
	private Player xPlayer, oPlayer;
	private String xName, oName;
	

	public GameServer() {
		try {
			serviceSocket = new ServerSocket(9000);
			threaPool = Executors.newFixedThreadPool(2);//number of threads on pool
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void runService() {
        System.out.println("Server is running..."); 
		try {
			while (true) {
				Socket = serviceSocket.accept();
				System.out.println("Server conection acheived");
				socketIn = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
				socketOut = new PrintWriter(Socket.getOutputStream(), true);
				
				countConnections++;
				
				if (countConnections % 2 == 1) {
					socketOut.println("What is the name for player X?");
					xName = socketIn.readLine();
					xPlayer = new Player(xName, LETTER_X);
				    xPlayer.setSocket(socketIn, socketOut);
					socketOut.println("Waiting for player 2");
				}
				else if(countConnections % 2 == 0) {
					socketOut.println("What is the name for player O?");
					oName = socketIn.readLine();
				    oPlayer = new Player(oName, LETTER_O);
				    xPlayer.getSocketOut().println("player 2 online " + oName);
				    oPlayer.setSocket(socketIn, socketOut);
				    Game theGame = new Game(xPlayer, oPlayer);				    
					threaPool.execute(theGame);
					System.out.println("New game started in one thread");
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		threaPool.shutdown();
		
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) throws IOException {
		GameServer myServer = new GameServer();
		myServer.runService();
	}
}
