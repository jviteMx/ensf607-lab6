/*
 * Author Yobbahim Javier Israel Perez Vite
 * */
package palindrome;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {//constructor for server	
	private Socket palindrome;
	private ServerSocket serverSocket;
	private PrintWriter socketOutput;
	private BufferedReader socketInput;
	
	public Server () {//
		try {
			serverSocket = new ServerSocket (8099);//port given in especifications
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void check4Palindrome () {
		String line = null;
		boolean taskExist = true;
		while (taskExist) {
			try {
				line = socketInput.readLine();
				if (line == null)
					taskExist = false;
				boolean palindrome = isPalindrome(line);
				if (palindrome)
					socketOutput.println(line + " positive for palindrome");
				else
					socketOutput.println(line + " negative for palindrome");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	private boolean isPalindrome (String line) {//checks for palindrome
		if (line == null)
			return false;
		int span = line.length()/2;//divides the length
		int Cursor = line.length() - 1;//sets a cursor to the back, this will be compared to front cursor
		for (int i = 0; i < span; i++) {//loop to compare forst and last character, then reduces 1 position and re compares 
			char frontCursor = line.charAt(i);
			char mirrorChar = line.charAt(Cursor);
			Cursor--;
			if (frontCursor != mirrorChar)//if at any point they do not match then is not palindrome
				return false;
			}
		return true;			
	}

	public static void main(String[] args) throws IOException{
        Server service = new Server();
        System.out.println("Palindrome Server is active, no conections");//status check
        service.palindrome = service.serverSocket.accept();
		System.out.println("Palindrome Server is active, conecction accepted");//status check
		service.socketInput = new BufferedReader (new InputStreamReader(service.palindrome.getInputStream()));//recives the string
		service.socketOutput = new PrintWriter (service.palindrome.getOutputStream(), true);//print result stream on socketoutput
		service.check4Palindrome();	//runs the palindrome method to check string		
		service.socketInput.close();//close socket
		service.socketOutput.close();//close socket
		service.palindrome.close();//close socket

	}

}
