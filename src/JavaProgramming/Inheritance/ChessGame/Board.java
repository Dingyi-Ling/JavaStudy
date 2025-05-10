package JavaProgramming.Inheritance.ChessGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class Board {

    // keeps track of the position of each king. First row is the position of the white king,
    // second row is the black king.
    private int[][] kingPositions = new int[2][2];

    // represents the entire board - make sure to initialize!.
    private Piece[][] board = new Piece[8][8];

    public Board() {
        // loads the images into a map
        HashMap<String, Image> images = loadImages();

        // Initialize Kings
        board[0][4] = new King(0, images.get("WhiteKing")); // White King at e1
        board[7][4] = new King(1, images.get("BlackKing")); // Black King at e8
        kingPositions[0] = new int[]{0, 4}; // White King position (row, col)
        kingPositions[1] = new int[]{7, 4}; // Black King position

        // Initialize Queens
        board[0][3] = new Queen(0, images.get("WhiteQueen")); // White Queen at d1
        board[7][3] = new Queen(1, images.get("BlackQueen")); // Black Queen at d8

        // Initialize Rooks
        board[0][0] = new Rook(0, images.get("WhiteRook")); // White Rook at a1
        board[0][7] = new Rook(0, images.get("WhiteRook")); // White Rook at h1
        board[7][0] = new Rook(1, images.get("BlackRook")); // Black Rook at a8
        board[7][7] = new Rook(1, images.get("BlackRook")); // Black Rook at h8

        // Initialize Knights
        board[0][1] = new Knight(0, images.get("WhiteKnight")); // White Knight at b1
        board[0][6] = new Knight(0, images.get("WhiteKnight")); // White Knight at g1
        board[7][1] = new Knight(1, images.get("BlackKnight")); // Black Knight at b8
        board[7][6] = new Knight(1, images.get("BlackKnight")); // Black Knight at g8

        // Initialize Bishops
        board[0][2] = new Bishop(0, images.get("WhiteBishop")); // White Bishop at c1
        board[0][5] = new Bishop(0, images.get("WhiteBishop")); // White Bishop at f1
        board[7][2] = new Bishop(1, images.get("BlackBishop")); // Black Bishop at c8
        board[7][5] = new Bishop(1, images.get("BlackBishop")); // Black Bishop at f8

        // Initialize Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(0, images.get("WhitePawn")); // White Pawns at rank 2
            board[6][i] = new Pawn(1, images.get("BlackPawn")); // Black Pawns at rank 7
        }

        // Fill remaining empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Empty();
                }
            }
        }
    }

    // draws the board. There should be a grid of 8x8 squares, and each piece in their location.
    // the last clicked piece (curr) should be drawn on a yellow background.
    public void draw(Graphics g, Piece curr) {
        // Draw the background and grid lines
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Chess.WIDTH, Chess.WIDTH);
        int sw = Chess.SQUARE_WIDTH; // the width of each square on the board

        // Draw colored squares
        boolean isWhiteSquare = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (isWhiteSquare) {
                    g.setColor(new Color(238, 238, 210)); // Light square color
                } else {
                    g.setColor(new Color(118, 150, 86));  // Dark square color
                }
                g.fillRect(col * sw, row * sw, sw, sw);
                isWhiteSquare = !isWhiteSquare;
            }
            isWhiteSquare = !isWhiteSquare; // Alternate starting color for next row
        }

        // Draw pieces and highlight selected piece and its moves
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == curr) { // If the piece is the current piece
                    g.setColor(Color.YELLOW); // Set the color to yellow
                    g.fillRect(c * sw, r * sw, sw, sw); // Fill the block with yellow
                }

                if (!board[r][c].isEmpty()) { // If the block is not empty
                    // Center the piece in the square
                    int pieceX = c * sw + (sw - Chess.IMG_WIDTH) / 2;
                    int pieceY = r * sw + (sw - Chess.IMG_WIDTH) / 2;
                    board[r][c].draw(g, pieceX, pieceY); // draw the piece
                }

                if (board[r][c] == curr && curr != null && !curr.isEmpty()) { // If the piece is the current selected piece
                    g.setColor(Color.RED); // Set the color to red for move indicators
                    // getMoves expects (board, row_of_piece, col_of_piece)
                    java.util.ArrayList<int[]> possibleMoves = curr.getMoves(this, r, c);
                    if (possibleMoves != null) {
                        for (int[] move : possibleMoves) { //Go through the possible moves
                            // move[0] is target_row, move[1] is target_col
                            int moveMarkerX = move[1] * sw + sw / 2 - 15/2; // Centered dot
                            int moveMarkerY = move[0] * sw + sw / 2 - 15/2; // Centered dot
                            g.fillOval(moveMarkerX, moveMarkerY, 15, 15); // Use smaller red dots
                        }
                    }
                }
            }
        }
    }

    /******** DON'T TOUCH THE BELOW CODE ****************/

    // loads all necessary images into a map. Key = piece name, value = corresponding image
    public HashMap<String, Image> loadImages() {
        String[] pieces = {"King", "Queen", "Rook", "Bishop", "Knight", "Pawn"};

        HashMap<String, Image> images = new HashMap<String, Image>();

        for (String p : pieces) {
            for (String color : new String[] {"Black", "White"}) {
                Image img = Toolkit.getDefaultToolkit().getImage("Images/" + color + p + ".png");
                images.put(color + p, img.getScaledInstance(Chess.IMG_WIDTH, Chess.IMG_WIDTH, Image.SCALE_SMOOTH));
            }
        }
        return images;
    }

    public Piece[][] getBoard() {
        return board;
    }

    // moves the piece currently at (r, c) to (newR, newC)
    // returns 0 for a normal move, 1 for a check move, 2 for a checkmate move
    public int move(int r, int c, int newR, int newC) {
        if (board[r][c].isKing()) {
            int team = board[r][c].getTeam();
            kingPositions[team] = new int[] {newR, newC}; // {row, col}
        }

        if (board[newR][newC].isKing()) { // Capturing a king means checkmate
            board[newR][newC] = board[r][c];
            board[r][c] = new Empty();
            return 2; // Checkmate
        }

        board[newR][newC] = board[r][c];
        board[r][c] = new Empty();

        if (check()) return 1; // Check
        return 0; // Normal move
    }

    // determines if the non-current team is in check.
    // This actually checks if the *opponent* of the piece at (i,j) is in check.
    // More precisely, it checks if ANY piece on the board is putting its opponent's king in check.
    public boolean check() {
        for (int r_idx = 0; r_idx < 8; r_idx++) {
            for (int c_idx = 0; c_idx < 8; c_idx++) {
                if (board[r_idx][c_idx].getTeam() != -1) { // If there's a piece on the square
                    int pieceTeam = board[r_idx][c_idx].getTeam();
                    int opponentTeam = (pieceTeam + 1) % 2;
                    // kingPositions[opponentTeam][0] is opponent's king row
                    // kingPositions[opponentTeam][1] is opponent's king col
                    if (board[r_idx][c_idx].check(kingPositions[opponentTeam][0], kingPositions[opponentTeam][1], r_idx, c_idx, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}