import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class TetrisViewer extends JFrame{


    private Tetris tetris;
    private Board board;

    private final int BOX_HEIGHT = 50;
    private final int BOX_WIDTH = 50;


    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 1000;



    private int[][] grid;


    public TetrisViewer(Tetris t, Board b)
    {
        tetris = t;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        board = b;
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("Score ", 640, 100);
        g.drawString("" + tetris.getScore() , 652, 125);
        drawBoard(g);
        drawPiece(g, tetris.getCurrentPiece(), tetris.getCurrentX(), tetris.getCurrentY(), tetris.getPiece().getColor());
    }

    public void drawBoard(Graphics g) {

        int[][] grid = board.getGrid();
        for (int j = 0; j < 20; j++)
        {
            for (int i = 0; i < 10; i++)
            {
                if (grid[j][i] != 0)
                {
                    g.setColor(board.getColor(j,i));
                    g.fillRect(i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                }
                g.setColor(Color.WHITE);
                g.drawRect(i * BOX_WIDTH, j * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
            }
        }
    }

    private void drawPiece(Graphics g, int[][] piece, int posX, int posY, Color c)
    {
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
}
