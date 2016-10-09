/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anders Lindström <anderslm@kth.se>
 */
public interface Intersectable {
    public boolean intersects(Point point);
}
