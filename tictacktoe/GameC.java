import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
public class GameC {
	private String readFromConsole;
	private String passtrough;
	private Socket Socket;
	private BufferedReader stdIn;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	

	public GameC (String serverName, int portNumber) {		
		try {
			Socket = new Socket (serverName, portNumber);
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			socketIn = new BufferedReader (new InputStreamReader (Socket.getInputStream()));
			socketOut = new PrintWriter (Socket.getOutputStream(), true);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			}		
	}	
	private void passName() {//pass name from console.
		readFromConsole = "";
		passtrough = "";
		try {
			passtrough = socketIn.readLine();
			if (passtrough != null) {
			    System.out.println(passtrough);
			    readFromConsole = stdIn.readLine();
			    socketOut.println(readFromConsole);
			}    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void closeSocket () {//Closing sockets.
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
	public void commSockets () {
		passName();
		while (!passtrough.equals("GAME OVER")) {//negating game over.
			try {
				passtrough = socketIn.readLine();  
				if (!passtrough.equals("GAME OVER"))//negating game over.
				    System.out.println(passtrough);
				if (passtrough.contains("select row")) {//contains rows.
					readFromConsole = stdIn.readLine();
					socketOut.println(readFromConsole);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 	
		}
		closeSocket ();	
	}	
	
	
	public static void main (String [] args) throws IOException {		
		GameC aClient = new GameC ("localhost", 9898);
		aClient.commSockets();		
	}
}

