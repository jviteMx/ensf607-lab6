package ex1;
/*
 * Author: Yobbahim JAvier Israel Perez Vite
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateServer {
	private BufferedReader socketInput;
	private PrintWriter socketOutput;
	private ServerSocket serverSocket;
	private Socket Socket;

	
	public DateServer() {
		try {
			serverSocket = new ServerSocket(9090);
			System.out.println("Server Date: is active");
			Socket = serverSocket.accept();
			socketInput = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
			socketOutput = new PrintWriter(Socket.getOutputStream(), true);
		} catch (IOException e) {
		}
	}

	public void getInput() throws IOException {//gets input from user
		StringBuffer line = null;//declares null the input
		while (true) {
			line = new StringBuffer(socketInput.readLine());//reads from socket
			if (line != null) {//if the input is not null
				if (line.toString().equals("TERMINATE")) {//if the input is terminate
					break;
				}
				if (line.toString().equals("DATE")) {//if the input is date
					Calendar getCal = Calendar.getInstance();//gets intance from pc calendar
					SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");//selects fromat for date
					socketOutput.println( dateFormat.format(getCal.getTime()));//prints to socket with the format specified
				} else if (line.toString().equals("TIME")) {//reads input time
					Calendar getCal = Calendar.getInstance();
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");//format for time
					socketOutput.println( timeFormat.format(getCal.getTime()));//print to socket for time
				}else {//error catching
					socketOutput.println("input in wrong format, please try again");
				}
			}
		}
		socketInput.close();//close socket
		socketOutput.close();//close socket
		serverSocket.close();//close socket
	}


	public static void main(String[] args) throws IOException {//creates new server and runs the get.input method for server
		DateServer server = new DateServer();
		server.getInput();
	}
}