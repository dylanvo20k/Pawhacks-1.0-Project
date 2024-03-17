public class King extends Pieces {
    public King(int pieceColor) {

        super(KING, pieceColor);
    }


    @Override
public boolean isValidMove(int targetRow, int targetCol) {
    if (Board.SQUARE[targetRow][targetCol] != null &&
        Movement.isSameTeam(this, Board.SQUARE[targetRow][targetCol])) {
        return false;
        }
        return true;
        }
        }



