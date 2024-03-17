import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    int rows = 8;
    int cols = 8;

    ArrayList<Pieces> pieceList = new ArrayList<>();
    public int squareSize = 85;

    Color lightBrown = new Color(135, 75, 45);
    Color brown = new Color(222, 177, 155);
    public Pieces selectedPiece;
    Input input = new Input(this);

    public Board() {
        this.setPreferredSize(new Dimension(cols * squareSize, rows * squareSize));
        this.setBackground(lightBrown);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    public void addPieces() {
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));

        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method first

        Graphics2D g2d = (Graphics2D) g;

        // Draw the board squares
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? brown : lightBrown);
                g2d.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
            }
        }

        // Highlight valid moves if a piece is selected
        if (selectedPiece != null) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(91, 159, 92));
                        g2d.fillRect(c * squareSize, r * squareSize, squareSize, squareSize);
                    }
                }
            }
        }

        // Draw pieces on the board
        for (Pieces piece : pieceList) {
            piece.paint(g2d);
        }
    }


    public Pieces getPiece(int col, int row) {
        for (Pieces piece : pieceList) {
            if (piece.col == col && piece.row == row) {
                return piece;
            }
        }
        return null;
    }
    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false;
        }
        if(move.piece.moveCollides(move.newCol, move.newRow)) {
            return false;
        }
        return true;
    }
    public boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }
    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * squareSize;
        move.piece.yPos = move.newRow * squareSize;

        move.piece.isFirstMove = false;

        capture(move);
    }
    public void capture(Move move) {
        pieceList.remove(move.capture);
    }
}