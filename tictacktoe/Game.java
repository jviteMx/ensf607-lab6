

import Referee;
import tictacktoe.Player;

public class Game implements Constants, Runnable {
	private Board board;
	private Referee reeferi;
	private Player xPlayer, oPlayer;

    public Game(Player xPlayer, Player oPlayer) {
        board  = new Board();
    
        this.xPlayer = xPlayer;
        this.oPlayer = oPlayer;
	}    
    public void setGame(){
	    xPlayer.setBoard(board);
		oPlayer.setBoard(board);			
		reeferi = new Referee();
		reeferi.setoPlayer(oPlayer);
		reeferi.setxPlayer(xPlayer);	        
		referi(reeferi);	
	}
	
    public void referi(Referee r) {
    	reeferi.runTheGame();
    }	
	
	public void run() {
		setGame();
		
	}
}
