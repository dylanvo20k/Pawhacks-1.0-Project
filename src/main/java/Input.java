import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {
    Board board;
    public Input(Board board) {
        this.board = board;
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int col = mouseEvent.getX() / board.squareSize;
        int row = mouseEvent.getY() / board.squareSize;
        Pieces xy = board.getPiece(col, row);
        if (xy != null) {
            board.selectedPiece = xy;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        int col = mouseEvent.getX() / board.squareSize;
        int row = mouseEvent.getY() / board.squareSize;

        if(board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);
            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.xPos = board.selectedPiece.col * board.squareSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.squareSize;

            }
        }
        board.selectedPiece = null;
        board.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (board.selectedPiece != null) {
            board.selectedPiece.xPos = mouseEvent.getX() - board.squareSize / 2;
            board.selectedPiece.yPos = mouseEvent.getY() - board.squareSize / 2;
            board.repaint();

        }
    }
}
