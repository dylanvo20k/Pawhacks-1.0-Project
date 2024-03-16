public class Bishop extends Pieces {
    public Bishop(int pieceColor) {
        super(BISHOP, pieceColor);
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is diagonal
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        if (rowDiff != colDiff) {
            return false; // Not a diagonal move
        }

        // Check if there are any pieces in the path
        int rowIncrement = (toRow > fromRow) ? 1 : -1;
        int colIncrement = (toCol > fromCol) ? 1 : -1;
        int row = fromRow + rowIncrement;
        int col = fromCol + colIncrement;

        while (row != toRow && col != toCol) {
            if (Board.SQUARE[row][col] != null) {
                return false; // There is a piece in the path
            }
            row += rowIncrement;
            col += colIncrement;
        }

        // Check if destination square is empty or has an enemy piece
        Pieces destinationPiece = Board.SQUARE[toRow][toCol];
        if (destinationPiece != null && destinationPiece.getPieceColor() == getPieceColor()) {
            return false; // Trying to capture an allied piece
        }

        return true;
    }
}
