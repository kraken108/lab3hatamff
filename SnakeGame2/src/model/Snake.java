    package model;

import java.util.LinkedList;
import java.util.List;

/**
 * An instance of this class represents a snake, with head and body
 * consisting of a list of points.
 * A snake can move in four directions. If the snake eats an apple 
 * it will grow in length.
 * 
 * @author Anders Lindström <anderslm@kth.se>
 */
public class Snake implements Intersectable {

    private final LinkedList<Point> body;
    private Direction direction;

    private int undigested; // number of undigested meals

    public Snake(Point head, int noOfSegments) {
        body = new LinkedList<>();
        init(head, noOfSegments);
    }

    public final void init(Point head, int noOfSegments) {
        body.clear();
        body.add(head);
        int x = head.getX();
        int y = head.getY();
        for (int i = 1; i < noOfSegments; i++) {
            body.add(new Point(x, ++y));
        }
        direction = Direction.NORTH;
        undigested = 0;
    }

    public void move() {
        addNewHead();
        if (undigested == 0) {
            body.removeLast();
        } else {
            undigested -= 1;
        }
    }

    public void eat(Apple apple) {
        if (apple.isPowered()) {
            undigested += 2;
        } else {
            undigested += 1;
        }
    }

    public Point getHead() {
        return body.get(0);
    }

    public List<Point> getBody() {
        return (List<Point>) body.clone();
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the new direction for the snake. To prevent the snake from turning
     * back on itself, a direction anti parallel with the current direction is
     * ignored.
     *
     * @param newDirection The new direction
     */
    public void setDirection(Direction newDirection) {
        // Prevent the snake from turning back on itself
        switch (newDirection) {
            case NORTH:
                if (direction != Direction.SOUTH) {
                    direction = newDirection;
                }
                break;
            case SOUTH:
                if (direction != Direction.NORTH) {
                    direction = newDirection;
                }
                break;
            case EAST:
                if (direction != Direction.WEST) {
                    direction = newDirection;
                }
                break;
            case WEST:
                if (direction != Direction.EAST) {
                    direction = newDirection;
                }
                break;
        }
    }

    @Override
    public boolean intersects(Point point) {
        for (Point p : body) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if head intersects the body, otherwise
     * return false.
     */
    public boolean collisionWithSelf() {
        Point head = body.getFirst();
        for (Point p : body) {
            if (p.equals(head) && p != head) {
                return true;
            }
        }
        return false; // unimplemented
    }

    private void addNewHead() {
        Point head = body.getFirst();
        Point newHead = null;
        System.out.println("first: " + head);

        switch (direction) {
            case NORTH:
                newHead = new Point(head.getX(), head.getY() - 1);
                break;
            case SOUTH:
                newHead = new Point(head.getX(), head.getY() + 1);
                break;
            case EAST:
                newHead = new Point(head.getX() + 1, head.getY());
                break;
            case WEST:
                newHead = new Point(head.getX() - 1, head.getY());
                break;
            default:
                break;
        }
        body.addFirst(newHead);
        System.out.println("new: " + newHead);
    }
}
