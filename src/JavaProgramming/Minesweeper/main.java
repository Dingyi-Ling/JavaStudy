package JavaProgramming.Minesweeper;

import javax.swing.*;
public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            minesweeper game = new minesweeper();
            game.setVisible(true);
        });
    }
}
