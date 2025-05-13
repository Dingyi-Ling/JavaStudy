package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class King extends Piece{

	
	public King(int turn, Image img) {
		super(turn,img);
	}

	public boolean isKing() {
		return true;
	}

	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		
		for (int i = -1; i < 2; i ++)
			for (int j = -1; j < 2; j ++) {
				if (r + i >= 0 && r + i < 8 && c + j < 8 && c + j >= 0) 
					if (board.getBoard()[r + i][c + j].getTeam() != getTeam()) {
						int[] move = {r+i,c+j};
						moves.add(move);
					}
						
			}
		return moves;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean check(int kingr, int kingc, int r, int c, Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
