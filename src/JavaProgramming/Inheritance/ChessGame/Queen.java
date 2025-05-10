package JavaProgramming.Inheritance.ChessGame;

import java.awt.Image;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<>();
        Piece[][] b = board.getBoard();

        // Directions: horizontal, vertical, and diagonal
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}, // Rook moves
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Bishop moves
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
                        break; // Blocked by a piece (friendly or opponent after capture)
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
        Piece[][] b = board.getBoard();

        // Directions: horizontal, vertical, and diagonal
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}, // Rook moves
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Bishop moves
        };

        for (int[] dir : directions) {
            int dr = dir[0];
            int dc = dir[1];
            for (int i = 1; i < 8; i++) {
                int newR = r + i * dr;
                int newC = c + i * dc;

                if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                    if (newR == kingR && newC == kingC) { // Found the king
                        return true;
                    }
                    if (!b[newR][newC].isEmpty()) { // Path is blocked by another piece
                        break;
                    }
                } else {
                    break; // Out of bounds
                }
            }
        }
        return false;
    }
}