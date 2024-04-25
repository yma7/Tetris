import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Board {

    private int dy;
    private boolean collision;

    private int dx;

    private final int size = 50;

    private int[][] shape;

    private int[][] grid;

    private boolean isGameOver;

    private int score;
    public Board()
    {
        grid = new int[10][10];
        score = 0;
        isGameOver = false;

    }




}