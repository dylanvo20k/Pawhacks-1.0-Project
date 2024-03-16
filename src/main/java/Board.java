import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board {
    protected static final int BOARD_SIZE = 8;
    protected static final int SQUARE_SIZE = 100; // Size of each square
    private static final String ICONS_PATH = "resources/";
    private static final String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    protected static JPanel[][] squares = new JPanel[BOARD_SIZE][BOARD_SIZE];
    protected static Pieces selectedPiece = null;


    // 2D array to represent pieces on the board
    protected static Pieces[][] SQUARE = new Pieces[BOARD_SIZE][BOARD_SIZE];
    public static void main(String[] args) {
        // Set initial pieces
        initializeBoard(START_FEN);

        SwingUtilities.invokeLater(Board::createAndShowGUI);
    }
    // This method parses a FEN String (standard notation in chess) and translate it into a 2D array.
    // Given a FEN String, it populates the array with pieces using the helper.
    private static void initializeBoard(String fen) {
        // Split FEN String Notation
        String[] parts = fen.split(" ");
        String boardState = parts[0];

        // Convert FEN notation to 2D array
        int row = 0;
        int col = 0;
        for (char c : boardState.toCharArray()) {
            if (c == '/') {
                row++;
                col = 0;
            } else if (Character.isDigit(c)) {
                int emptySpaces = Character.getNumericValue(c);
                for (int i = 0; i < emptySpaces; i++) {
                    SQUARE[row][col++] = null;
                }
            } else {
                int color = Character.isUpperCase(c) ? Pieces.WHITE : Pieces.BLACK;
                int pieceType = fenToPiece(Character.toUpperCase(c));
                SQUARE[row][col++] = new Pieces(pieceType, color);
            }
        }
    }

    // Helper method to convert FEN notation to piece type
    private static int fenToPiece(char c) {
        switch (c) {
            case 'P': return Pieces.PAWN;
            case 'N': return Pieces.KNIGHT;
            case 'B': return Pieces.BISHOP;
            case 'R': return Pieces.ROOK;
            case 'Q': return Pieces.QUEEN;
            case 'K': return Pieces.KING;
            default: return -1; // Invalid piece type
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
                square.setBackground((row + col) % 2 == 0 ? lightBrown : brown);

                final int r = row;
                final int c = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Movement.handleSquareClick(r, c);
                    }
                });

                squares[row][col] = square;
                panel.add(square);
            }
        }
        Movement.updateBoard();
    }


    // Get the file path for the piece icon based on the piece type and color
    protected static String getPieceImagePath(Pieces piece) {
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
        // System.out.println(ICONS_PATH + color + type + ".png");
        return ICONS_PATH + color + type + ".png";
    }
}
