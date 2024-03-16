
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
        private boolean isCaptured() {
                return this.isCaptured();
        }

}
