package JavaProgramming.Inheritance.ChessGame;
import java.awt.Image;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int turn, Image img) {
        super(turn, img);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<>();
        Piece[][] b = board.getBoard();

        // All possible knight moves (L-shape: 2 in one direction, 1 in the perpendicular direction)
        int[][] knightMoves = {
                {-2, -1}, {-2, 1},  // Two up, one left/right
                {2, -1},  {2, 1},   // Two down, one left/right
                {-1, -2}, {-1, 2},  // One up, two left/right
                {1, -2},  {1, 2}    // One down, two left/right
        };

        for (int[] move : knightMoves) {
            int newR = r + move[0];
            int newC = c + move[1];

            // Check if the move is within the board boundaries
            if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                // Check if the square is empty or contains an opponent's piece
                if (b[newR][newC].isEmpty() || b[newR][newC].getTeam() != getTeam()) {
                    moves.add(new int[]{newR, newC});
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
        // Check if the knight can reach the king in one move
        // Knights check by being in an L-shape position from the king

        // Calculate the absolute differences in position
        int rowDiff = Math.abs(kingR - r);
        int colDiff = Math.abs(kingC - c);

        // A knight checks the king if it can move to the king's position in one move
        // This happens when the knight is either:
        // 2 squares away in one dimension and 1 square away in the other dimension
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}