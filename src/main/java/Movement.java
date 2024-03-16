import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Movement {
    protected static void handleSquareClick(int row, int col) {
        Pieces clickedPiece = Board.SQUARE[row][col];
        if (Board.selectedPiece == null) {
            if (clickedPiece != null) {
                Board.selectedPiece = clickedPiece;
            }
        } else {
            // Move the selected piece to the clicked square
            Board.SQUARE[row][col] = Board.selectedPiece;
            System.out.println(Board.selectedPiece.getRow() + ", " + Board.selectedPiece.getCol());
            Board.SQUARE[Board.selectedPiece.getRow()][Board.selectedPiece.getCol()] = null;
            Board.selectedPiece.setRow(row);
            Board.selectedPiece.setCol(col);
            Board.selectedPiece = null;
            updateBoard();
        }
    }

    protected static void updateBoard() {
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                JPanel square = Board.squares[row][col];
                square.removeAll(); // Clear previous content
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