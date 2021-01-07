package ch.crowdev.cells;

public class Cell {
    public int x;
    public int y;
    public int direction; // 1: left, 2: down, 3: right, 4: up
    public int type;
    public int rotation;

    public Cell(int _x, int _y, int _dir, int _type) {
        x = _x;
        y = _y;
        direction = _dir;
        type = _type;
        rotation = _dir * 90;
    }

    public int getType() {
        return type;
    }
    public int getRotation() { return rotation; }
}
