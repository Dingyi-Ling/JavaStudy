package JavaProgramming.Inheritance.ChessGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class Board {

	private int[][] kingPositions = new int[2][2];

	private Piece[][] board = new Piece [8][8];

	public Board() {

		HashMap<String, Image> images = loadImages();

		board[4][0] = new King(0, images.get("WhiteKing"));
		board[4][7] = new King(1, images.get("BlackKing"));
		kingPositions[0] = new int[]{4, 0};
		kingPositions[1] = new int[] {4, 7};

		board[3][0] = new Queen(0, images.get("WhiteQueen"));
		board[3][7] = new Queen(1, images.get("BlackQueen"));

		board[0][0] = new Rook(0, images.get("WhiteRook"));
		board[7][0] = new Rook(0, images.get("WhiteRook"));
		board[0][7] = new Rook(1, images.get("BlackRook"));
		board[7][7] = new Rook(1, images.get("BlackRook"));

		board[1][0] = new Knight(0, images.get("WhiteKnight"));
		board[6][0] = new Knight(0, images.get("WhiteKnight"));
		board[1][7] = new Knight(1, images.get("BlackKnight"));
		board[6][7] = new Knight(1, images.get("BlackKnight"));

		board[2][0] = new Bishop(0, images.get("WhiteBishop"));
		board[5][0] = new Bishop(0, images.get("WhiteBishop"));
		board[2][7] = new Bishop(1, images.get("BlackBishop"));
		board[5][7] = new Bishop(1, images.get("BlackBishop"));

		for (int i = 0; i <= 7; i++) {
			board[i][1] = new Pawn(0, images.get("WhitePawn"));
		}

		for (int i = 0; i <= 7; i++) {
			board[i][6] = new Pawn(1, images.get("BlackPawn"));
		}

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (board[i][j] == null) {
					board[i][j] = new Empty();
				}
			}
		}
	}

	public void draw(Graphics g, Piece curr) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Chess.WIDTH, Chess.WIDTH);
		int sw = Chess.SQUARE_WIDTH;
		g.setColor(Color.black);
		for (int i = 1; i <= 8; i++) {
			g.drawLine(0, Chess.WIDTH / 8 * i, Chess.WIDTH, Chess.WIDTH / 8 * i);
		}
		for (int i = 1; i <= 8; i++) {
			g.drawLine(Chess.WIDTH / 8 * i, 0, Chess.WIDTH / 8 * i, Chess.WIDTH);
		}
		boolean green = true;
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++){
				if (green == true && j != 7){
					g.setColor(new Color(0,100,0));
					g.fillRect(Chess.WIDTH / 8 * j, Chess.WIDTH / 8 * i, Chess.WIDTH / 8, Chess.WIDTH / 8);
					green = false;
				}
				else if (green == true && j == 7){
					g.setColor(new Color(0,100,0));
					g.fillRect(Chess.WIDTH / 8 * j, Chess.WIDTH / 8 * i, Chess.WIDTH / 8, Chess.WIDTH / 8);
				}
				else if (green == false && j != 7){
					green = true;
				}
			}
		}
		for (int i = 0; i <= 7; i++){
			for (int j = 0; j <= 7; j++){
				if (board[i][j] == curr){
					g.setColor(Color.YELLOW);
					g.fillRect(Chess.WIDTH / 8 * j, Chess.WIDTH / 8 * i, Chess.WIDTH / 8, Chess.WIDTH / 8);
				}
				if (board[i][j].isEmpty() == false){
					board[i][j].draw(g, Chess.WIDTH / 8 * j, Chess.WIDTH / 8 * i);
				}
				if (board[i][j] == curr) {
					g.setColor(Color.red);
					for (int k = 0; k <= board[i][j].getMoves(this, i, j).size() - 1; k++) {
						g.fillOval(board[i][j].getMoves(this, i, j).get(k)[1] * 600 / 8 + 600 / 16 - 15, board[i][j].getMoves(this, i, j).get(k)[0] * 600 / 8 + 600 / 16 - 15, 30, 30);
					}
				}
			}
		}
	}


	/******** DON'T TOUCH THE BELOW CODE ****************/

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

	public int move(int r, int c, int newR, int newC) {

		if (board[r][c].isKing()) {
			int team = board[r][c].getTeam();
			kingPositions[team] = new int[] {newR, newC};
		}

		if (board[newR][newC].isKing()) {
			board[newR][newC] = board[r][c];
			board[r][c] = new Empty();
			return 2;
		}

		board[newR][newC] = board[r][c];
		board[r][c] = new Empty();


		if (check()) return 1;
		return 0;

	}

	public boolean check() {
		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getTeam()!= -1) {
					int opp = (board[i][j].getTeam()+1)%2;
					if (board[i][j].check(kingPositions[opp][0], kingPositions[opp][1], i, j, this)) {
						System.out.println("Player " + opp + " is in check");
						return true;
					}
				}

			}
		return false;
	}
}