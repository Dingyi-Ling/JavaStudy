package JavaProgramming.Inheritance.ChessGame;
import java.awt.Image;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<>();
        Piece[][] b = board.getBoard();

        // Horizontal and vertical directions
        int[][] directions = {
                {0, 1},   // Right
                {0, -1},  // Left
                {1, 0},   // Down
                {-1, 0}   // Up
        };

        for (int[] dir : directions) {
            int dr = dir[0];
            int dc = dir[1];

            for (int i = 1; i < 8; i++) {
                int newR = r + i * dr;
                int newC = c + i * dc;

                if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                    if (b[newR][newC].isEmpty()) {
                        moves.add(new int[]{newR, newC});
                    } else {
                        if (b[newR][newC].getTeam() != getTeam()) {
                            moves.add(new int[]{newR, newC}); // Can capture
                        }
                        break; // Blocked by a piece
                    }
                } else {
                    break; // Out of bounds
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
        // Check if the rook can reach the king horizontally or vertically
        Piece[][] b = board.getBoard();

        // The king must be in the same row or column for the rook to check
        if (kingR != r && kingC != c) {
            return false;
        }

        // Determine the direction to move (only one direction will have a non-zero value)
        int dr = Integer.compare(kingR, r); // -1, 0, or 1
        int dc = Integer.compare(kingC, c); // -1, 0, or 1

        // Check if the path to the king is clear
        int currentR = r + dr;
        int currentC = c + dc;

        while (currentR != kingR || currentC != kingC) {
            if (!b[currentR][currentC].isEmpty()) {
                return false; // Path is blocked
            }
            currentR += dr;
            currentC += dc;
        }

        return true; // King is in check
    }
}