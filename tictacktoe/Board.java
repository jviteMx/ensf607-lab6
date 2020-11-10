

/*
 * This class is the drawing of the board.
 * Author@ Yobbahim Javier Israel Perez Vite
 * */
import java.io.BufferedReader;

import java.io.PrintWriter;

import Constants;

public class Board implements Constants {
	private char board[][];
	private int markCount;
   
    private PrintWriter socketOut;
	public Board() {//setting the board up

		markCount = 0;
		board = new char[3][];//3x3
		for (int i = 0; i < 3; i++) {
			board[i] = new char[3];//3x3
			for (int j = 0; j < 3; j++)
				board[i][j] = SPACE_CHAR;//refer to Constants interface
		}
	}
	void Headers() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|col " + j);
		socketOut.println();
	}

	

	void Spaces() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|     ");
		socketOut.println("|");
	}
	public void display() {
		Headers();//show titles
		guiones();//show slashes?
		for (int row = 0; row < 3; row++) {
			Spaces();//make space betwen slashes
			socketOut.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				socketOut.print("|  " + getMark(row, col) + "  ");
			socketOut.println("|");
			Spaces();//make more space
			guiones();//and more slashes
		}
	}
	public char getMark(int row, int col) {//gets coordenates for the mark
		return board[row][col];
	}

	public boolean complete() {
		return markCount == 9;
	}

	public boolean xWins() {
		if (defineWinner(LETTER_X) == 1)//check interface for winner X
			return true;
		else
			return false;
	}

	public boolean oWins() {
		if (defineWinner(LETTER_O) == 1)//calls for method to define winner
			return true;
		else
			return false;
	}

	

	public void addMark(int row, int col, char mark) {
		
		board[row][col] = mark;
		markCount++;
	}

	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	void guiones() {//dont know how to translate this word slash?
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("+-----");
		socketOut.println("+");
	}
	int defineWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (board[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (board[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (board[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (board[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}


	
	public void setSocket(PrintWriter socketOut) {
    	
    	this.socketOut = socketOut;
    }
}
