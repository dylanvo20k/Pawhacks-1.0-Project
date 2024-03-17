public class Rook extends Pieces{
    public Rook(int pieceColor) {
        super(ROOK, pieceColor);
    }
@Override
    public boolean isValidMove(int targetRow, int targetCol) {
        int rowDiff = targetRow - row;
        int colDiff = targetCol - col;
        if (row == targetRow || col == targetCol) {
            if (row == targetRow) {
                for (int newCol = col + colDiff; newCol != targetCol; newCol += colDiff) {
                    if (Board.SQUARE[row][newCol] != null) {
                        return false;
                    }
                }
            } else {
                for (int newRow = row + rowDiff; newRow != targetRow; newRow += rowDiff) {
                    if (Board.SQUARE[newRow][col] != null) {
                        return false;
                    }
                }
            }
            return Board.SQUARE[targetRow][targetCol] == null || Board.SQUARE[targetRow][targetCol].getPieceColor() != this.getPieceColor();
        }
        return false;
        }
    }

