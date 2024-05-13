import java.awt.*;

public class Piece {

    // Variable intialization
    private int[][] shape;

    private Board b;
    private Color color;

    public Piece(int[][] s, int x, int y, Color c, Board board)
    {
        shape = s;
        color = c;
        b = board;
    }

    // Getters and setters
    public int[][] getPiece()
    {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void rotateClockwise(int currentX, int currentY)
    {

        int height = shape.length;
        int width = shape[0].length;
        int[][] rotatedPiece = new int[width][height];
        // Iterates through the width and height of the piece
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                // Rotates the piece clockwise
                rotatedPiece[i][j] = shape[height - 1 - j][i];
            }
        }
        // Checks to see if the rotated piece fits into constraints ie: isn't colliding with anything
        if (checkPiece(rotatedPiece, currentX, currentY))
        {
            // If true then rotates the piece
            shape = rotatedPiece;
        }
    }

    public boolean checkPiece(int[][] piece, int x1, int y1)
    {
        // Iterates through the piece
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[0].length; j++)
            {
                if (piece[i][j] != 0)
                {
                    // Gets the current x and y values
                    int y = y1 + i;
                    int x = x1 + j;
                    // Checks to see if is in constraints
                    if (y >= b.getHeight() || x < 0 || x >= b.getWidth() || b.getGrid()[y][x] == 1)
                    {
                        // Returns false is any of these are true
                        return false;
                    }
                }

            }
        }
        // Otherwise the piece can be fit onto the board
        return true;
    }
}
