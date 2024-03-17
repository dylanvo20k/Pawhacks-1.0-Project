public class Pieces {
    public static final int PAWN = 1;
    public static final int KNIGHT = 2;
    public static final int BISHOP = 3;
    public static final int ROOK = 4;
    public static final int QUEEN = 5;
    public static final int KING = 6;

    public static final int WHITE = 7;
    public static final int BLACK = 8;

    private int pieceType;
    private int pieceColor;
    private int col;
    private int row;
    boolean firstMove = true;


    public Pieces(int pieceType, int pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;

    }

    public int getPieceType() {
        return pieceType;
    }

    public int getPieceColor() {
        return pieceColor;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public boolean isValidMove(int targetRow, int targetCol) {
        if (Board.SQUARE[targetRow][targetCol] != null &&
                Movement.isSameTeam(this, Board.SQUARE[targetRow][targetCol])) {
            return false;
        }
        return true;
    }
}
