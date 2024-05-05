import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris implements KeyListener
{
    private int score = 0;

    private int[][] rotatedPiece;
    private Piece piece;
    private boolean isGameOver = false;

    private int delay = 500;

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

    public Tetris()
    {
        board = new Board();
        window = new TetrisViewer(this, board);
        window.addKeyListener(this);               // #4 Required for KeyListener
        generateNewPiece();
        timer();

    }

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

    public int getScore()
    {
        return score;
    }




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
        piece = new Piece(currentPiece, currentX, currentY, randomColor);
    }

    public int[][] getCurrentPiece()
    {
        return piece.getPiece();
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void moveDown()
    {
        if (currentY + piece.getPiece().length < board.getHeight())
        {
            currentY++;
        }
        else
        {
            board.lockPiece(piece, currentX, currentY, piece.getColor());
            generateNewPiece();
        }
    }

    public void moveLeft()
    {
        if (currentX > 0)
        {
            currentX--;
        }
    }


    public void moveRight()
    {
        if (currentX + piece.getPiece()[0].length < board.getWidth())
        {
            currentX++;
        }

    }



    public void playGame()
    {
        while (!isGameOver)
        {
            timer.start();
        }
    }

    public boolean checkPiece()
    {
        return true;
    }
    public void timer() {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public void setPiece(int[][] p) {
        this.piece = piece;
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode())
        {
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
                piece.rotateClockwise(currentPiece);
                break;
        }
        window.repaint();
    }


    public static void main (String[] args)
    {
        Tetris t = new Tetris();
        t.playGame();
    }
}
