public class Queen extends Pieces {
    public Queen(int pieceColor) {

        super(QUEEN, pieceColor);
    }
//
//    public boolean isValidBishop(int targetRow, int targetCol) {
//        int rowDiff = Math.abs(targetRow - row);
//        int colDiff = Math.abs(targetCol - col);
//
//        if (rowDiff != colDiff) {
//            return false; // Not a diagonal move
//        }
//
//        // Check if there are any pieces in the path
//        int rowIncrement = (targetRow > row) ? 1 : -1;
//        int colIncrement = (targetCol > col) ? 1 : -1;
//        int newRow = row + rowIncrement;
//        int newCol = col + colIncrement;
//
//        while (newRow != targetRow && newCol != targetCol && newRow >= 0 && newCol >= 0) {
//            if (Board.SQUARE[newRow][newCol] != null) {
//                return false; // There is a piece in the path
//            }
//            newRow += rowIncrement;
//            newCol += colIncrement;
//        }
//
//        // Check if destination square is empty or has an enemy piece
//        Pieces destinationPiece = Board.SQUARE[targetRow][targetCol];
//        if (destinationPiece != null && destinationPiece.getPieceColor() == getPieceColor()) {
//            return false; // Trying to capture an allied piece
//        }
//
//        return true;
//    }
//
//
    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        return true;
    }
//        if (Board.SQUARE[targetRow][targetCol] != null &&
//                Movement.isSameTeam(this, Board.SQUARE[targetRow][targetCol])) {
//            return false;
//        }
//        return true;
//    }
        // Check if the move is diagonal
    }
//
//
//
//
//
