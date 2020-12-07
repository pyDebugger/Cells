package ch.crowdev.cells;

public class Cell {
    public int x;
    public int y;
    public int direction;
    public int type;

    public Cell(int _x, int _y, int _dir, int _type) {
        x = _x;
        y = _y;
        direction = _dir;
        type = _type;
    }
}
