import javax.swing.*;
import java.awt.*;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZING = 100;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Board::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame board = new JFrame("Chessboard");
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        addSquares(boardPanel);

        board.getContentPane().add(boardPanel);
        board.pack();
        board.setVisible(true);
    }

    private static void addSquares(JPanel panel) {
        Color brown = new Color(135, 75, 45);
        Color lightBrown = new Color(222, 177, 155);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel();
                square.setPreferredSize(new Dimension(SQUARE_SIZING, SQUARE_SIZING));
                square.setBackground((row + col) % 2 == 0 ? brown : lightBrown);
                panel.add(square);
            }
        }
    }
}
