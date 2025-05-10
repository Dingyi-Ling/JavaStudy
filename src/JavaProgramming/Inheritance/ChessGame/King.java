package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class King extends Piece{

    // represents a king.

    public King(int turn, Image img) {
        super(turn,img);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Skip the current position
                if (i == 0 && j == 0) continue;

                int newR = r + i;
                int newC = c + j;

                // Check if the move is within the board boundaries
                if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                    // Check if the square is empty or contains an opponent's piece
                    if (board.getBoard()[newR][newC].getTeam() != getTeam()) {
                        moves.add(new int[]{newR, newC});
                    }
                }
            }
        }
        return moves;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean check(int kingR, int kingC, int r, int c, Board board) {
        // A king cannot put another king in check unless they are adjacent
        // Calculate the Manhattan distance between the two kings
        int rowDiff = Math.abs(kingR - r);
        int colDiff = Math.abs(kingC - c);

        // Kings can check each other if they're adjacent (but this should never happen in a valid game)
        return rowDiff <= 1 && colDiff <= 1 && (rowDiff != 0 || colDiff != 0);
    }
}