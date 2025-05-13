package JavaProgramming.Inheritance.ChessGame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int turn, Image img) {
        super(turn, img);
    }

    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int[][] directions = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int dr = dir[0];
            int dc = dir[1];

            for (int i = 1; i < 8; i++) {
                int newR = r + dr * i;
                int newC = c + dc * i;

                if (newR >= 0 && newR < 8 && newC >= 0 && newC < 8) {
                    Piece targetPiece = board.getBoard()[newR][newC];

                    if (targetPiece.getTeam() == -1) {
                        int[] move = {newR, newC};
                        moves.add(move);
                    } else if (targetPiece.getTeam() != getTeam()) {
                        int[] move = {newR, newC};
                        moves.add(move);
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return moves;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean check(int kingr, int kingc, int r, int c, Board board) {
        if (Math.abs(kingr - r) == Math.abs(kingc - c)) {
            int dr = Integer.compare(kingr, r);
            int dc = Integer.compare(kingc, c);

            int currR = r + dr;
            int currC = c + dc;

            while (currR != kingr && currC != kingc) {
                if (!board.getBoard()[currR][currC].isEmpty()) {
                    return false;
                }
                currR += dr;
                currC += dc;
            }

            return true;
        }

        return false;
    }
}