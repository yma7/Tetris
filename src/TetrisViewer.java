import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class TetrisViewer extends JFrame{


    // Variable definition
    private Tetris tetris;
    private Board board;

    private Image loss;

    private final int BOX_HEIGHT = 50;
    private final int BOX_WIDTH = 50;


    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 1000;





    public TetrisViewer(Tetris t, Board b)
    {
        // Creates new window
        tetris = t;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        board = b;
        loss = new ImageIcon("Resources/Tetris/losingscreen.png").getImage();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        if (board != null)
        {
            // Draws the board
            drawBoard(g);
            if (tetris.getCurrentPiece() != null)
            {
                // Draws the current tetris piece and the entire board which includes the pieces before
                drawPiece(g, tetris.getCurrentPiece(), tetris.getCurrentX(), tetris.getCurrentY(), tetris.getPiece().getColor());
            }
            if (board.isGameOver(tetris.getCurrentPiece(), tetris.getCurrentX(), tetris.getCurrentY()))
            {
                // If game is over then show losing screen
                endGame(g);
            }
            else
            {
                // Clears the line by one if none of these are true
                drawLinesCleared(g);
            }
        }
    }

    public void drawLinesCleared(Graphics g)
    {
        // Displays the linesCleared counter to the right of the grid
        int linesCleared = board.getLinesCleared();
        String text = "Lines Cleared: "  + linesCleared;

        int x = 10 * BOX_WIDTH + 100;
        int y = WINDOW_HEIGHT/2;
        g.setColor(Color.WHITE);
        g.drawString(text,x,y);
    }
    public void drawBoard(Graphics g) {

        if (board.getGrid() == null)
        {
            return;
        }
        // Iterates through the current board
        for (int j = 0; j < 20; j++)
        {
            for (int i = 0; i < 10; i++)
            {
                // Checks a value as there must be a tetris piece if it isn't 0
                if (board.getGrid()[j][i] != 0)
                {
                    // Draws the piece with the random color it was initialized with before
                    g.setColor(board.getColor(j,i));
                    g.fillRect(i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                }
                // Fill the outer with white
                g.setColor(Color.WHITE);
                g.drawRect(i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
            }
        }
    }

    private void drawPiece(Graphics g, int[][] piece, int posX, int posY, Color c)
    {
        // Same as drawBoard but with the current piece
        Color color = c;
        g.setColor(color);
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[0].length; j++)
            {
                int x = posX + j;
                int y = posY + i;
                if (piece[i][j] == 1)
                {
                    g.fillRect(x * BOX_WIDTH, y * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                }
            }
        }
    }

    // Displays the losing screen
    public void endGame(Graphics g)
    {
        g.drawImage(loss, 0,0,this);
    }
}
