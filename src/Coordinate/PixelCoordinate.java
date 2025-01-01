package Coordinate;

public class PixelCoordinate {
    private final int x;
    private final int y;

    public PixelCoordinate(int x, int y, int size) {
        this.x = x * size;
        this.y = y * size;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
