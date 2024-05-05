import java.awt.*;

public class Piece {

    private int[][] shape;



    private Color color;

    public Piece(int[][] s, int x, int y, Color c)
    {
        shape = s;
        color = c;
    }

    public int[][] getPiece()
    {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void rotateClockwise(int[][] piece)
    {
        int height = piece.length;
        int width = piece[0].length;
        int[][] rotatedPiece = new int[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                rotatedPiece[i][j] = piece[height - 1 - j][i];
            }
        }
        shape = rotatedPiece;
    }

}
