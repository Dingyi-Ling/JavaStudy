package JavaProgramming.Arrays2D.Minesweeper;
import java.util.Scanner;
public class MineSweeper {
    private int numRows;
    private int numCols;
    private int mines;
    private String[][] grid;
    private boolean gameOver;
    public MineSweeper(int rows, int cols, int numMines) {
        numRows = rows;
        numCols = cols;
        mines = numMines;
        gameOver= false;
        grid = new String[numRows+2][numCols+2];
        for(int r=0; r<=grid.length-1; r++) {
            for(int c=0; c<=grid[0].length-1; c++) {
                grid[r][c]=".";
            }
        }
        for(int i=0; i<mines;i++) {
            int r = (int)(Math.random()*numRows)+1;
            int c = (int)(Math.random()*numCols)+1;
            if (!grid[r][c].equals("M")) grid[r][c] = "M";
            else i--;
        }
    }
    public void displayBoard() {
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<grid[0].length; c++) {
                System.out.print(grid[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void checkSquare(int row, int col){
        if (row <= 0 || col <= 0 || row >= grid.length - 1 || col >= grid[0].length - 1 || !grid[row][col].equals("M") && !grid[row][col].equals(".")){
            System.out.println("Invalid Choice");
            return;
        }
        if(grid[row][col].equals("M")){
            System.out.println("BOOM! You lose");
            gameOver = true;
        }
        int count = 0;
        if(grid[row][col-1].equals("M")) count++;
        if(grid[row][col+1].equals("M")) count++;
        if(grid[row-1][col-1].equals("M")) count++;
        if(grid[row-1][col].equals("M")) count++;
        if(grid[row-1][col+1].equals("M")) count++;
        if(grid[row+1][col-1].equals("M")) count++;
        if(grid[row+1][col].equals("M")) count++; // 修复了这一行
        if(grid[row+1][col+1].equals("M")) count++;
        grid[row][col] = String.valueOf(count);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MineSweeper game = new MineSweeper(10,10,10);
        game.displayBoard();
        while(!game.gameOver) {
            System.out.println("Please enter a row #");
            int row = scanner.nextInt();
            System.out.println("Please enter a column #");
            int col = scanner.nextInt();
            game.checkSquare(row, col);
            game.displayBoard();

        }
    }
}