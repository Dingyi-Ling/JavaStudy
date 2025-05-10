package JavaProgramming.Inheritance.ChessGame;
import java.awt.Image;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<>();
        Piece[][] b = board.getBoard();

        // Diagonal directions: top-right, top-left, bottom-right, bottom-left
        int[][] directions = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
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
        // Check if the bishop can reach the king diagonally

        // Calculate the differences to determine if the king is on a diagonal
        int rowDiff = Math.abs(kingR - r);
        int colDiff = Math.abs(kingC - c);

        // If not on a diagonal, the bishop cannot check the king
        if (rowDiff != colDiff) {
            return false;
        }

        // Determine the direction to move towards the king
        int dr = (kingR > r) ? 1 : -1;
        int dc = (kingC > c) ? 1 : -1;

        // Check if the path to the king is clear
        int currentR = r + dr;
        int currentC = c + dc;

        while (currentR != kingR && currentC != kingC) {
            if (!board.getBoard()[currentR][currentC].isEmpty()) {
                return false; // Path is blocked
            }
            currentR += dr;
            currentC += dc;
        }

        return true; // King is in check
    }
}