import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Board {

    // Variable definition

    private Color[][] colors;


    private int[][] grid;

    private final int height = 20;

    private final int width = 10;


    private  int linesCleared = 0;



    public Board()
    {
        grid = new int[height][width];
        colors = new Color[height][width];
    }


    // Locks piece onto board
    public void lockPiece(Piece p, int posX, int posY, Color c)
    {
        // Gets the current tetris piece
        int[][] piece = p.getPiece();
        // Iterates through it
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[i].length; j++)
            {
                if (piece[i][j] != 0)
                {
                    // Gets current board x and y values
                    int boardX = posX + j;
                    int boardY = posY + i;
                    // Locks the int and color value onto the 2d board
                    grid[boardY][boardX] = piece[i][j];
                    colors[boardY][boardX] = c;
                }
            }
        }
    }

    // Getters and setters
    public Color getColor(int x, int y) {
        return colors[x][y];
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid() {
        return grid;
    }

    // Checks for full lines
    public void checkFull() {
        // Iterates through the board/grid
        for (int i = 0; i < grid.length; i++)
        {
            boolean fullRow = true;
            for (int j = 0; j < grid[0].length; j++)
            {
                // If there is a 0 (empty value) then the row is not full
                if (grid[i][j] != 1)
                {
                    fullRow = false;
                    break;
                }
            }
            // Otherwise the row is full
            if (fullRow)
            {
                for (int row = i; row > 0; row--)
                {
                    for (int col = 0; col < grid[0].length; col++)
                    {
                        // Moves everything down by one
                        grid[row][col] = grid[row - 1][col];
                        colors[row][col] = colors[row - 1][col];
                    }
                }
                // Adds one to linesCleared counter
                linesCleared++;
            }
        }
    }

    // Checks to see if game is over
    public boolean isGameOver(int piece[][], int x, int y)
    {
        // Iterates through piece
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[0].length; j++) {

                if (piece[i][j] == 1)
                {
                    int boardX = x + j;
                    int boardY = y + i;
                    // If the starting value of the piece (top row top col) is already full then the game is over
                        // No where left for the piece to go
                    if (boardX < 0 || boardX >= grid[0].length || boardY >= grid.length || grid[boardY][boardX] != 0)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getLinesCleared() {
        return linesCleared;
    }

    public void setLinesCleared(int linesCleared) {
        this.linesCleared = linesCleared;
    }
}

