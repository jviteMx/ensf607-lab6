package ex1;
/*
 * Author: Yobbahim JAvier Israel Perez Vite*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DateClient {
	private PrintWriter socketOutput;
	private BufferedReader stdIn;
	private Socket dateSocket;	
	private BufferedReader socketInput;

	public DateClient(String serverName, int portNumber) {
		try {
			dateSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketInput = new BufferedReader(new InputStreamReader(dateSocket.getInputStream()));
			socketOutput = new PrintWriter((dateSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	 private void closeSocket () {
		
		try {
			stdIn.close();
			socketInput.close();
			socketOutput.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void translateToServer () {
		String line = "";
		String response = "";
		
		while (!line.equals("TERMINATE")) {
			System.out.println("Please select an option (DATE/TIME)");
			System.out.println("Type TERMINATE to quit");
			try {
				line = stdIn.readLine();
				socketOutput.println(line);
				response = socketInput.readLine(); //read response form the socket
				if (response == null)
					break;
				System.out.println(response);
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
		}
		closeSocket ();
		
	}
    
	public static void main(String[] args) throws IOException  {
		DateClient aClient = new DateClient("localhost", 9090);
		aClient.translateToServer();
	}
}
