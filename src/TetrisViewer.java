import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class TetrisViewer extends JFrame{
    private Tetris tetris;
    private Board board;

    private final int BOX_HEIGHT = 50;
    private final int BOX_WIDTH = 50;


    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 500;


    private int[][] grid;


    public TetrisViewer(Tetris t, Board b)
    {
        tetris = t;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        board = b;
        grid = new int[10][10];
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("Score ", 640, 100);
        g.drawString("" + tetris.getScore() , 652, 125);
        drawBoard(g);
        drawPiece(g, tetris.getCurrentPiece(), tetris.getCurrentX(), tetris.getCurrentY());
    }

    public void drawBoard(Graphics g)
    {
        for (int i = 0; i < 10; i ++)
        {
            for (int j = 0; j < 10; j++)
            {
                g.setColor(Color.WHITE);
                int x = i * BOX_WIDTH;
                int y = j * BOX_HEIGHT;
                g.drawRect(x,y,BOX_WIDTH,BOX_HEIGHT);
            }
        }
    }
    private void drawPiece(Graphics g, int[][] piece, int posX, int posY)
    {
        g.setColor(Color.blue);
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[0].length; j++)
            {
                if (piece[i][j] == 1)
                {
                    int x = posX + j;
                    int y = posY + i;
                    g.fillRect(x * BOX_WIDTH, y * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                }
            }
        }
    }
}
