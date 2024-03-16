import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 100; // Size of each square
    private static final String ICONS_PATH = "resources/";

    // 2D array to represent pieces on the board
    private static Pieces[][] SQUARE = new Pieces[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        // Set initial pieces
        initializeBoard();

        SwingUtilities.invokeLater(Board::createAndShowGUI);
    }

    private static void initializeBoard() {
        // Set white pieces
        SQUARE[0][0] = new Pieces(Pieces.ROOK, Pieces.WHITE);
        SQUARE[0][1] = new Pieces(Pieces.KNIGHT, Pieces.WHITE);
        SQUARE[0][2] = new Pieces(Pieces.BISHOP, Pieces.WHITE);
        SQUARE[0][3] = new Pieces(Pieces.QUEEN, Pieces.WHITE);
        SQUARE[0][4] = new Pieces(Pieces.KING, Pieces.WHITE);
        SQUARE[0][5] = new Pieces(Pieces.BISHOP, Pieces.WHITE);
        SQUARE[0][6] = new Pieces(Pieces.KNIGHT, Pieces.WHITE);
        SQUARE[0][7] = new Pieces(Pieces.ROOK, Pieces.WHITE);
        for (int col = 0; col < BOARD_SIZE; col++) {
            SQUARE[1][col] = new Pieces(Pieces.PAWN, Pieces.WHITE);
        }

        // Set black pieces
        SQUARE[7][0] = new Pieces(Pieces.ROOK, Pieces.BLACK);
        SQUARE[7][1] = new Pieces(Pieces.KNIGHT, Pieces.BLACK);
        SQUARE[7][2] = new Pieces(Pieces.BISHOP, Pieces.BLACK);
        SQUARE[7][3] = new Pieces(Pieces.QUEEN, Pieces.BLACK);
        SQUARE[7][4] = new Pieces(Pieces.KING, Pieces.BLACK);
        SQUARE[7][5] = new Pieces(Pieces.BISHOP, Pieces.BLACK);
        SQUARE[7][6] = new Pieces(Pieces.KNIGHT, Pieces.BLACK);
        SQUARE[7][7] = new Pieces(Pieces.ROOK, Pieces.BLACK);
        for (int col = 0; col < BOARD_SIZE; col++) {
            SQUARE[6][col] = new Pieces(Pieces.PAWN, Pieces.BLACK);
        }
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
                JPanel square = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                square.setBackground((row + col) % 2 == 0 ? brown : lightBrown);

                // Check if there's a piece on this square
                if (SQUARE[row][col] != null) {
                    try {
                        // Load and display the piece icon
                        String imagePath = getPieceImagePath(SQUARE[row][col]);
                        BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
                        square.add(new JLabel(new ImageIcon(image)));
                    } catch (IOException e) {
                        // Handle image loading error
                        e.printStackTrace();
                    }
                }
                panel.add(square);
            }
        }
    }

    // Get the file path for the piece icon based on the piece type and color
    private static String getPieceImagePath(Pieces piece) {
        String color = piece.getPieceColor() == Pieces.WHITE ? "white" : "black";
        String type = "";
        switch (piece.getPieceType()) {
            case Pieces.PAWN:
                type = "Pawn";
                break;
            case Pieces.KNIGHT:
                type = "Knight";
                break;
            case Pieces.BISHOP:
                type = "Bishop";
                break;
            case Pieces.ROOK:
                type = "Rook";
                break;
            case Pieces.QUEEN:
                type = "Queen";
                break;
            case Pieces.KING:
                type = "King";
                break;
        }
        //System.out.println(ICONS_PATH + color + type + ".png");
        return ICONS_PATH + color + type + ".png";
    }
}