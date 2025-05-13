package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Rook extends Piece {

    // represents a rook which can move any number of squares horizontally or vertically

    public Rook(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        // Check the four directions: up, down, left, right
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        for (int[] dir : directions) {
            int dr = dir[0];
            int dc = dir[1];

            // Continue in this direction until we hit the edge or another piece
            for (int i = 1; i < 8; i++) {
                int newR = r + dr * i;
                int newC = c + dc * i;

                // Check if we're still on the board
                if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                    Piece targetPiece = board.getBoard()[newR][newC];

                    if (targetPiece.getTeam() == -1) {
                        // Empty square, we can move here
                        int[] move = {newR, newC};
                        moves.add(move);
                    } else if (targetPiece.getTeam() != getTeam()) {
                        // Enemy piece, we can capture it but then must stop
                        int[] move = {newR, newC};
                        moves.add(move);
                        break;
                    } else {
                        // Our own piece, we can't move here or further in this direction
                        break;
                    }
                } else {
                    // We're off the board
                    break;
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
        // Is the king in the same row or column?
        if (kingr == r || kingc == c) {
            // Check if there's a clear path to the king
            int dr = Integer.compare(kingr, r); // Direction to move in the row (-1, 0, or 1)
            int dc = Integer.compare(kingc, c); // Direction to move in the column

            int currR = r + dr;
            int currC = c + dc;

            while (currR != kingr || currC != kingc) {
                if (!board.getBoard()[currR][currC].isEmpty()) {
                    // There's a piece in the way
                    return false;
                }
                currR += dr;
                currC += dc;
            }

            // Clear path to the king
            return true;
        }

        return false;
    }
}