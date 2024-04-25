public class Tetris
{
    private int score = 0;
    private boolean isGameOver = false;

    private final int[][][] pieces = {
            {{1, 1, 1, 1}},  // I-piece
            {{1, 1}, {1, 1}},  // O-piece
            {{1, 1, 0}, {0, 1, 1}},  // S-piece
            {{0, 1, 1}, {1, 1, 0}},  // Z-piece
            {{1, 0, 0}, {1, 1, 1}},  // L-piece
            {{0, 0, 1}, {1, 1, 1}},  // J-piece
            {{0, 1, 0}, {1, 1, 1}}  // T-piece
    };

    private int[][] currentPiece;
    private TetrisViewer window;
    private int currentX;
    private int currentY;
    private Board board;

    public Tetris()
    {
        board = new Board();
        window = new TetrisViewer(this, board);
        generateNewPiece();
    }



    public void generateNewPiece()
    {
        currentPiece = pieces[(int)(Math.random() * (pieces.length))];
        currentX = 5;
        currentY = 0;
    }

    public int[][] getCurrentPiece()
    {
        return currentPiece;
    }

    public void moveDown()
    {
        currentY--;
    }

    public void moveLeft()
    {
        currentY--;
    }

    public void moveRight()
    {
        currentX++;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getScore()
    {
        return score;
    }

    public void playGame()
    {
        window.repaint();
    }
    public static void main (String[] args)
    {
        Tetris t = new Tetris();
        while (t.isGameOver == false)
        {
            t.playGame();
        }
    }

}
