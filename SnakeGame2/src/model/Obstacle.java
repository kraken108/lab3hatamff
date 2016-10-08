/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jakob
 */
public class Obstacle {

    private final LinkedList<Point> obstacle;

    public Obstacle(Point start, int noOfSegments) {
        obstacle = new LinkedList<>();
        init(start, noOfSegments);
    }

    public Obstacle(LinkedList<Point> points) {
        obstacle = new LinkedList<>();
        for(Point p : points){
            obstacle.add(p);
        }
    }

    public final void init(Point start, int noOfSegments) {
        obstacle.clear();
        obstacle.add(start);
        int x = start.getX();
        int y = start.getY();
        for (int i = 1; i < noOfSegments; i++) {
            obstacle.add(new Point(x, ++y));
        }
    }

    public List<Point> getPoints() {
        return (List<Point>) obstacle.clone();
    }

    public boolean intersects(Point point) {
        for (Point p : obstacle) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }
}
