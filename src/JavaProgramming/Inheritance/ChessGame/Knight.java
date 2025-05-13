package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Knight extends Piece {

    // represents a knight which moves in an L-shape (2 squares in one direction, then 1 square perpendicular)

    public Knight(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        // All possible L-moves a knight can make
        int[][] knightMoves = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newR = r + move[0];
            int newC = c + move[1];

            // Check if the move is on the board
            if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                // Check if the square is empty or has an enemy piece
                if (board.getBoard()[newR][newC].getTeam() != getTeam()) {
                    int[] validMove = {newR, newC};
                    moves.add(validMove);
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
    public boolean check(int kingr, int kingc, int r, int c, Board board) {
        // Calculate the distance between the knight and the king
        int rowDiff = Math.abs(kingr - r);
        int colDiff = Math.abs(kingc - c);

        // Knight puts the king in check if it's an L-move away
        return (rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1);
    }
}