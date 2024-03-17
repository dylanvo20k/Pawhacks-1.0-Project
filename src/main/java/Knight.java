public class Knight extends Pieces{
    public Knight(int pieceColor) {

        super(KNIGHT, pieceColor);
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

