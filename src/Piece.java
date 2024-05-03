import java.awt.*;

public class Piece {

    private int[][] piece;

    private Color color;

    public Piece(int[][] shape, int x, int y, Color c)
    {
        piece = shape;
        color = c;
    }

    public int[][] getPiece()
    {
        return piece;
    }

    public Color getColor() {
        return color;
    }

    public void rotate()
    {

    }

}
