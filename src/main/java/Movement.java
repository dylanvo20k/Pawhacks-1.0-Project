import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Movement {
    protected static void handleSquareClick(int row, int col) {
        Pieces clickedPiece = Board.SQUARE[row][col];
        if (Board.selectedPiece == null) {
            if (clickedPiece != null) {
                Board.selectedPiece = clickedPiece;
                highlightLegalMoves(Board.selectedPiece);
            }
        } else {
            if (clickedPiece == null || !isSameTeam(Board.selectedPiece, clickedPiece)) {
                // Move the selected piece to the clicked square
                Board.SQUARE[row][col] = Board.selectedPiece;
                // System.out.println(Board.selectedPiece.getRow() + ", " + Board.selectedPiece.getCol());
                Board.SQUARE[Board.selectedPiece.getRow()][Board.selectedPiece.getCol()] = null;
                Board.selectedPiece.setRow(row);
                Board.selectedPiece.setCol(col);
                Board.selectedPiece = null;
                updateBoard();
            } else {
                // Pieces on the same team, do not allow capturing
                System.out.println("Cannot capture a piece on the same team.");
            }
        }
    }
    protected static void highlightLegalMoves(Pieces piece) {
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                if (piece.isValidMove(row, col)) {
                    JPanel square = Board.squares[row][col];
                    square.setBackground(new Color(55, 249, 100));
                }
            }
        }
    }
    protected static boolean isSameTeam(Pieces piece1, Pieces piece2) {
        return piece1.getPieceColor() == piece2.getPieceColor();
    }

    protected static void updateBoard() {
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                JPanel square = Board.squares[row][col];
                square.removeAll(); // Clear previous content
                square.setBackground((row + col) % 2 == 0 ? new Color(222, 177, 155) : new Color(135, 75, 45));
                if (Board.SQUARE[row][col] != null) {
                    try {
                        // Load and display the piece icon
                        String imagePath = Board.getPieceImagePath(Board.SQUARE[row][col]);
                        BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
                        square.add(new JLabel(new ImageIcon(image)));
                    } catch (IOException e) {
                        // Handle image loading error
                        e.printStackTrace();
                    }
                }
                square.revalidate(); // Refresh square
                square.repaint(); // Repaint square
            }
        }
    }
}
