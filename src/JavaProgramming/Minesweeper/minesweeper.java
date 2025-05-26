package JavaProgramming.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class minesweeper extends JFrame {
    // Game constants
    private static final int BEGINNER_SIZE = 9;
    private static final int INTERMEDIATE_SIZE = 16;
    private static final int EXPERT_SIZE = 24;
    private static final int BEGINNER_MINES = 10;
    private static final int INTERMEDIATE_MINES = 40;
    private static final int EXPERT_MINES = 99;
    private static final int CELL_SIZE = 30;

    // Game variables
    private JButton[][] cells;
    private boolean[][] mines;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int[][] adjacentMines;
    private int gridSize;
    private int totalMines;
    private int revealedCount;
    private boolean gameOver;
    private Timer timer;
    private int secondsElapsed;
    private JLabel timerLabel;
    private JLabel mineCountLabel;
    private int remainingMines;

    // Cell colors
    private final Color unrevealedColor = new Color(180, 180, 180); // Light gray for unrevealed cells
    private final Color revealedColor = new Color(220, 220, 220);   // Lighter gray for revealed cells

    public minesweeper() {
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu difficultyMenu = new JMenu("Difficulty");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        JRadioButtonMenuItem beginnerItem = new JRadioButtonMenuItem("Beginner (9x9, 10 mines)");
        JRadioButtonMenuItem intermediateItem = new JRadioButtonMenuItem("Intermediate (16x16, 40 mines)");
        JRadioButtonMenuItem expertItem = new JRadioButtonMenuItem("Expert (24x24, 99 mines)");

        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(beginnerItem);
        difficultyGroup.add(intermediateItem);
        difficultyGroup.add(expertItem);
        beginnerItem.setSelected(true);

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        difficultyMenu.add(beginnerItem);
        difficultyMenu.add(intermediateItem);
        difficultyMenu.add(expertItem);

        menuBar.add(gameMenu);
        menuBar.add(difficultyMenu);
        setJMenuBar(menuBar);

        // Set up action listeners for menu items
        newGameItem.addActionListener(e -> startNewGame());
        exitItem.addActionListener(e -> System.exit(0));
        beginnerItem.addActionListener(e -> {
            gridSize = BEGINNER_SIZE;
            totalMines = BEGINNER_MINES;
            startNewGame();
        });
        intermediateItem.addActionListener(e -> {
            gridSize = INTERMEDIATE_SIZE;
            totalMines = INTERMEDIATE_MINES;
            startNewGame();
        });
        expertItem.addActionListener(e -> {
            gridSize = EXPERT_SIZE;
            totalMines = EXPERT_MINES;
            startNewGame();
        });

        // Set up the status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        timerLabel = new JLabel("Time: 0");
        mineCountLabel = new JLabel("Mines: 0");
        statusPanel.add(mineCountLabel, BorderLayout.WEST);
        statusPanel.add(timerLabel, BorderLayout.EAST);
        add(statusPanel, BorderLayout.NORTH);

        // Set up the timer
        timer = new Timer(1000, e -> {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed);
        });

        // Initialize game with beginner difficulty
        gridSize = BEGINNER_SIZE;
        totalMines = BEGINNER_MINES;
        startNewGame();
    }

    private void startNewGame() {
        // Stop the timer and reset it
        timer.stop();
        secondsElapsed = 0;
        timerLabel.setText("Time: 0");

        // Initialize game variables
        gameOver = false;
        revealedCount = 0;
        remainingMines = totalMines;
        mineCountLabel.setText("Mines: " + remainingMines);

        // Remove the old game panel if it exists
        Container contentPane = getContentPane();
        Component[] components = contentPane.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel && !(component instanceof JMenuBar)) {
                contentPane.remove(component);
            }
        }

        // Create a new game panel
        JPanel gamePanel = new JPanel(new GridLayout(gridSize, gridSize));
        add(gamePanel, BorderLayout.CENTER);

        // Initialize arrays
        cells = new JButton[gridSize][gridSize];
        mines = new boolean[gridSize][gridSize];
        revealed = new boolean[gridSize][gridSize];
        flagged = new boolean[gridSize][gridSize];
        adjacentMines = new int[gridSize][gridSize];

        // Create cells
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JButton cell = new JButton();
                cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cell.setFont(new Font("Arial", Font.BOLD, 16));
                cell.setMargin(new Insets(0, 0, 0, 0));
                cell.setFocusPainted(false);
                // Set the initial unrevealed appearance with a raised border effect
                cell.setBackground(unrevealedColor);
                cell.setBorder(BorderFactory.createRaisedBevelBorder());

                final int r = row;
                final int c = col;

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) return;

                        // Start the timer on the first click
                        if (!timer.isRunning()) {
                            placeMines(r, c);
                            countAdjacentMines();
                            timer.start();
                        }

                        if (e.getButton() == MouseEvent.BUTTON1 && !flagged[r][c]) {
                            // Left click - reveal cell
                            revealCell(r, c);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            // Right click - flag/unflag cell
                            if (!revealed[r][c]) {
                                flagged[r][c] = !flagged[r][c];
                                if (flagged[r][c]) {
                                    cell.setText("🚩");
                                    remainingMines--;
                                } else {
                                    cell.setText("");
                                    remainingMines++;
                                }
                                mineCountLabel.setText("Mines: " + remainingMines);
                            }
                        }
                    }
                });

                cells[row][col] = cell;
                gamePanel.add(cell);
            }
        }

        // Pack the frame and center it on the screen
        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    private void placeMines(int firstRow, int firstCol) {
        Random random = new Random();
        int minesPlaced = 0;

        // Create a safe zone around the first click
        boolean[][] safeZone = new boolean[gridSize][gridSize];

        // Mark the clicked cell and all adjacent cells as safe
        for (int r = Math.max(0, firstRow - 1); r <= Math.min(gridSize - 1, firstRow + 1); r++) {
            for (int c = Math.max(0, firstCol - 1); c <= Math.min(gridSize - 1, firstCol + 1); c++) {
                safeZone[r][c] = true;
            }
        }

        while (minesPlaced < totalMines) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);

            // Don't place a mine in the safe zone or where a mine already exists
            if (!safeZone[row][col] && !mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private void countAdjacentMines() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (mines[row][col]) {
                    adjacentMines[row][col] = -1; // Mine
                } else {
                    int count = 0;
                    for (int r = Math.max(0, row - 1); r <= Math.min(gridSize - 1, row + 1); r++) {
                        for (int c = Math.max(0, col - 1); c <= Math.min(gridSize - 1, col + 1); c++) {
                            if (mines[r][c]) {
                                count++;
                            }
                        }
                    }
                    adjacentMines[row][col] = count;
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        if (revealed[row][col] || flagged[row][col] || gameOver) {
            return;
        }

        revealed[row][col] = true;
        revealedCount++;

        if (mines[row][col]) {
            // Game over - hit a mine
            gameOver = true;
            timer.stop();
            cells[row][col].setBackground(Color.RED);
            cells[row][col].setBorder(BorderFactory.createLoweredBevelBorder());
            cells[row][col].setText("💣");
            revealAllMines();
            JOptionPane.showMessageDialog(this, "Game Over! You hit a mine.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Update cell appearance for revealed cells
            cells[row][col].setBackground(revealedColor);
            // Change from raised to lowered border to create "dug" appearance
            cells[row][col].setBorder(BorderFactory.createLoweredBevelBorder());
            if (adjacentMines[row][col] > 0) {
                cells[row][col].setText(String.valueOf(adjacentMines[row][col]));
                setCellColor(row, col);
            } else {
                // If cell has no adjacent mines, recursively reveal neighboring cells
                for (int r = Math.max(0, row - 1); r <= Math.min(gridSize - 1, row + 1); r++) {
                    for (int c = Math.max(0, col - 1); c <= Math.min(gridSize - 1, col + 1); c++) {
                        revealCell(r, c);
                    }
                }
            }

            // Check if the player has won
            if (revealedCount == gridSize * gridSize - totalMines) {
                gameOver = true;
                timer.stop();
                JOptionPane.showMessageDialog(this, "Congratulations! You won in " + secondsElapsed + " seconds.", "You Won!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void setCellColor(int row, int col) {
        switch (adjacentMines[row][col]) {
            case 1:
                cells[row][col].setForeground(Color.BLUE);
                break;
            case 2:
                cells[row][col].setForeground(new Color(0, 128, 0)); // Green
                break;
            case 3:
                cells[row][col].setForeground(Color.RED);
                break;
            case 4:
                cells[row][col].setForeground(new Color(0, 0, 128)); // Dark Blue
                break;
            case 5:
                cells[row][col].setForeground(new Color(128, 0, 0)); // Maroon
                break;
            case 6:
                cells[row][col].setForeground(new Color(0, 128, 128)); // Teal
                break;
            case 7:
                cells[row][col].setForeground(Color.BLACK);
                break;
            case 8:
                cells[row][col].setForeground(Color.GRAY);
                break;
        }
    }

    private void revealAllMines() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (mines[row][col] && !revealed[row][col]) {
                    cells[row][col].setText("💣");
                } else if (flagged[row][col] && !mines[row][col]) {
                    // Incorrectly flagged cells
                    cells[row][col].setText("❌");
                }
            }
        }
    }
}