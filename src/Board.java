import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Board {


    private final int size = 50;

    private Color[][] colors;

    private int[][] shape;

    private int[][] grid;

    private final int height = 20;

    private final int width = 10;

    private Color color;

    private boolean isGameOver;

    private Piece currentPiece;



    private int score;
    public Board()
    {
        grid = new int[height][width];
        colors = new Color[height][width];
        score = 0;
        isGameOver = false;
    }

    public void lockPiece(Piece p, int posX, int posY, Color c) {
        int[][] piece = p.getPiece();
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[i].length; j++)
            {
                if (piece[i][j] != 0)
                {
                    int boardX = posX + j;
                    int boardY = posY + i;
                    if (boardX < width && boardY < height && boardX >= 0 && boardY >= 0)
                    {
                        grid[boardY][boardX] = piece[i][j];
                        colors[boardY][boardX] = c;
                    }
                }
            }
        }

    }

    public Color getColor(int x, int y)
    {
        return colors[x][y];
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid()
    {
        return grid;
    }


}