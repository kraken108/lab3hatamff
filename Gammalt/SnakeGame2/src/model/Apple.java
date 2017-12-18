package model;

/**
 * An instance of this class represents an apple. An apple might be 
 * powered.
 * 
 * @author Anders Lindström <anderslm@kth.se>
 */
public class Apple implements Intersectable {

    private final Point position;
    public boolean powered;
    public Apple(Point position) {
        this.position = position;
    }
    
    public Apple(Point position, boolean powered) {
        this.position = position;
        this.powered = powered;
    }

    public Point getPosition() {
        return position;
    }

    /**
     * Return whether this apple is powered or not.
     */
    public boolean isPowered() {
        if (powered) {
            return true;
        }
        return false; // change this implementation
    }
    
    @Override
    public boolean intersects(Point point) {
        return this.position.equals(point);
    }
}
