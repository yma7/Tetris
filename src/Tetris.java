import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris implements KeyListener
{

    // Variables Initialization

    private boolean isGameOver = false;



    private Piece piece;

    private int hold = 2;

    private static int delay = 175;

    private Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.magenta};

    private final static int DECREASE_PER_FIVE_LINES = 30;

    private final static int MININUM_DELAY = 10;

    private Timer timer;

    private int[][] currentPiece;

    private final int[][][] pieces = {
            {{1, 1, 1, 1}},
            {{1, 1}, {1, 1}},
            {{1, 1, 0}, {0, 1, 1}},
            {{0, 1, 1}, {1, 1, 0}},
            {{1, 0, 0}, {1, 1, 1}},
            {{0, 0, 1}, {1, 1, 1}},
            {{0, 1, 0}, {1, 1, 1}}
    };


    private TetrisViewer window;
    private int currentX;
    private int currentY;
    private Board board;

    // Constructor
    public Tetris()
    {
        board = new Board();
        window = new TetrisViewer(this, board);
        window.addKeyListener(this);
        generateNewPiece();
        timer();

    }

    // Getters and setters

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY)
    {
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    // Generates the Tetris piece
    public void generateNewPiece()
    {
        Random random = new Random();
        int r = random.nextInt(256);
        int e = random. nextInt(256);
        int b = random. nextInt(256);
        Color randomColor = new Color(r, e, b);
        currentPiece = pieces[(int)(Math.random() * (pieces.length))];
        currentX = 5;
        currentY = 0;
        piece = new Piece(currentPiece, currentX, currentY, randomColor, board);
        window.repaint();
    }

    // Gets the current tetris piece which is stored as an int
    public int[][] getCurrentPiece()
    {
        return piece.getPiece();
    }

    public Piece getPiece()
    {
        return piece;
    }

    // Moves the block down by one
    public void moveDown()
    {
        // When gameOver stop program
        if(isGameOver)
        {
            return;
        }
        // Checks to see if the blocks below are free of collisions
        if (piece.checkPiece(piece.getPiece(), currentX, currentY + 1))
        {
            // Moves down if open
            currentY++;
        }
        // Otherwise there must be a piece blocking the current tetris
        else
        {
            // Locks the piece in it's current coordinates into the board
            board.lockPiece(piece, currentX, currentY, piece.getColor());
            // Checks for full line
            board.checkFull();
            // Generates a new piece
            generateNewPiece();
            // Stops game if game is finished
            if (board.isGameOver(currentPiece, currentX, currentY))
            {
                stopGame();
            }

        }

    }

    public void moveLeft()
    {
        // Moves the piece left
        if (currentX > 0)
        {
            boolean canMoveLeft = true;
            // Iterates through each part of the piece
            for (int i = 0; i < piece.getPiece().length; i++)
            {
                for (int j = 0; j < piece.getPiece()[0].length; j++)
                {
                    if (piece.getPiece()[i][j] == 1)
                    {
                        // Checks to see if the spot left to the piece is open
                        int nextX = currentX + j - 1;
                        int nextY = currentY + i;
                        if (board.getGrid()[nextY][nextX] == 1)
                        {
                            canMoveLeft = false;
                            break;
                        }
                    }

                }
            }
            // Moves left if is vacant
            if (canMoveLeft)
            {
                currentX--;
            }
        }
    }

    public void moveRight()
    {
        // Does the same as moveLeft but in the right
        if (currentX + piece.getPiece()[0].length < board.getWidth())
        {
            boolean canMoveRight = true;
            for (int i = 0; i < piece.getPiece().length; i++)
            {
                for (int j = 0; j < piece.getPiece()[0].length; j++)
                {
                    if (piece.getPiece()[i][j] == 1)
                    {
                        int nextX = currentX + j + 1;
                        int nextY = currentY + i;
                        if (board.getGrid()[nextY][nextX] == 1)
                        {
                            canMoveRight = false;
                            break;
                        }
                    }

                }
            }
            if (canMoveRight)
            {
                currentX++;
            }
        }
    }

    public void playGame()
    {
        timer.start();
    }

    public void stopGame()
    {
        isGameOver = true;
    }



    // Initalizes a timer
    public void timer() {
        timer = new Timer(delay, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Calls moveDown and repaints the screen
                moveDown();
                window.repaint();
            }
        });
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e)
    {

        // Checks to see if game is finished
        if (isGameOver)
        {
            // Restarts if the user presses r
            if (e.getKeyCode() == KeyEvent.VK_R)
            {
                restartGame();
            }
        }
        switch(e.getKeyCode())
        {
            // All the case switch statements/possible moves a user can input into the window

            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP:
                piece.rotateClockwise(currentX, currentY);
                break;
        }
        window.repaint();
    }

    public void restartGame()
    {
        // Creates and starts a new game
        Tetris newGame = new Tetris();
        newGame.playGame();
    }

    public static void main (String[] args)
    {
        // Begins a tetris game
        Tetris t = new Tetris();
        t.playGame();
    }
}
