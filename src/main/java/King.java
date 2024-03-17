import java.awt.image.BufferedImage;

public class King extends Pieces {
    public King(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.squareSize;
        this.yPos = row * board.squareSize;

        this.isWhite = isWhite;
        this.name = "King";

        this.sprite = sheet.getSubimage
                (0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance
                (board.squareSize, board.squareSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.col) * (row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1;
    }
}
