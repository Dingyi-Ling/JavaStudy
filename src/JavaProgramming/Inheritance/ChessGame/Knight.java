package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Knight extends Piece {


    public Knight(int turn, Image img) {
        super(turn, img);
    }

    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int[][] knightMoves = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newR = r + move[0];
            int newC = c + move[1];

            if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                if (board.getBoard()[newR][newC].getTeam() != getTeam()) {
                    int[] validMove = {newR, newC};
                    moves.add(validMove);
                }
            }
        }

        return moves;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean check(int kingr, int kingc, int r, int c, Board board) {
        int rowDiff = Math.abs(kingr - r);
        int colDiff = Math.abs(kingc - c);

        return (rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1);
    }
}