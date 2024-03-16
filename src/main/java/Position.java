package src.main.java;

public class Position {
        private int currentX;
        private int currentY;
        private Pieces piece;
        public Position (int currentX, int currentY, Pieces piece) {
                this.currentX = currentX;
                this.currentY = currentY;
                this.piece = piece;
        }
        private void setPosition(int newX, int newY) {
                this.currentX = newX;
                this.currentY = newY;
        }
        private void isValidMove() {

        }

        public Position currentSquare(int row, int col) {
                return Pieces[row][col];
        }


}
