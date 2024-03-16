public class Pieces {
    public static final int EMPTY = 0;
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
    private int pieceValue;
}