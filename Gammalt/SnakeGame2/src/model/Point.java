package model;

/**
 * A representation of a point, used e.g. as segments in a snake
 * or the position of an apple.
 * 
 * @author Anders Lindström <anderslm@kth.se>
 */
public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object other) {
        if (other instanceof Point) {
            Point p = (Point) other;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    public Point makeCopy() {
        return new Point(this.x, this.y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
