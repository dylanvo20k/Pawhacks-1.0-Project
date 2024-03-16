import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZING = 100;
    public static int[][] SQUARE; // 2D array to represent pieces and colors
    private static final String PIECES_PATH = "Pawhacks-1.0-Project/src/main/resources/";

    public static void main(String[] args) {
        // Initialize the SQUARE array with starting positions (using Pieces class constants)
        SQUARE = new int[BOARD_SIZE][BOARD_SIZE];
        setStartingPositions();

        SwingUtilities.invokeLater(Board::createAndShowGUI);
    }

    private static void setStartingPositions() {
        // Set pawns
        for (int col = 0; col < BOARD_SIZE; col++) {
            SQUARE[1][col] = Pieces.white | Pieces.pawn; // White pawns
            SQUARE[6][col] = Pieces.black | Pieces.pawn; // Black pawns
        }

        // Set rooks, knights, bishops (adjust row positions as needed)
        SQUARE[0][0] = SQUARE[0][7] = SQUARE[SQUARE.length - 1][0] = SQUARE[SQUARE.length - 1][7] = Pieces.white | Pieces.rook;
        SQUARE[0][1] = SQUARE[0][6] = SQUARE[SQUARE.length - 1][1] = SQUARE[SQUARE.length - 1][6] = Pieces.white | Pieces.knight;
        SQUARE[0][2] = SQUARE[0][5] = SQUARE[SQUARE.length - 1][2] = SQUARE[SQUARE.length - 1][5] = Pieces.white | Pieces.bishop;
        SQUARE[0][3] = Pieces.white | Pieces.queen;
        SQUARE[0][4] = Pieces.white | Pieces.king;

        SQUARE[SQUARE.length - 1][0] = SQUARE[SQUARE.length - 1][7] = Pieces.black | Pieces.rook;
        SQUARE[SQUARE.length - 1][1] = SQUARE[SQUARE.length - 1][6] = Pieces.black | Pieces.knight;
        SQUARE[SQUARE.length - 1][2] = SQUARE[SQUARE.length - 1][5] = Pieces.black | Pieces.bishop;
        SQUARE[SQUARE.length - 1][3] = Pieces.black | Pieces.queen;
        SQUARE[SQUARE.length - 1][4] = Pieces.black | Pieces.king;
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

                // Add piece image based on piece type and color
                if (SQUARE[row][col] != Pieces.empty) {
                    int pieceType = SQUARE[row][col] & ~(Pieces.white | Pieces.black); // Isolate piece type
                    int pieceColor = SQUARE[row][col] & (Pieces.white | Pieces.black); // Isolate color

                    String imagePath = PIECES_PATH + (pieceColor == Pieces.white ? "white" : "black");
                    switch (pieceType) {
                        case Pieces.pawn:
                            imagePath += "Pawn.png";
                            break;
                        case Pieces.knight:
                            imagePath += "Knight.png";
                            break;
                        case Pieces.bishop:
                            imagePath += "Bishop.png";
                            break;
                        case Pieces.rook:
                            imagePath += "Rook.png";
                            break;
                        case Pieces.queen:
                            imagePath += "Queen.png";
                            break;
                        case Pieces.king:
                            imagePath += "King.png";
                            break;
                    }

                    try {
                        BufferedImage image = ImageIO.read(new java.io.File(imagePath));
                        square.add(new JLabel(new ImageIcon(image)));
                    } catch (Exception e) {
                        // Handle image loading error (e.g., print message)
                        System.err.println("Error loading image: " + imagePath);
                    }
                }

                panel.add(square);
            }
        }
    }
}