package test;

import javafx.scene.paint.Color;

/**
 * A representation of a world containing a set of moving shapes. NB! The worlds
 * y-axis points downward.
 *
 * @author Linders Andström, anderslm@kth.se 2015-09-16
 */
public class World {

    private double width, height; // this worlds width and height

    private final Shape[] shapes; // an array of references to the shapes

    /**
     * Creates a new world, containing a pad and a set of balls. NB! The worlds
     * y-axis points downward.
     *
     * @param width the width of this world
     * @param height the height of this world
     */
    public World(double width, double height) {
        this.width = width;
        this.height = height;

        shapes = new Shape[10]; // an array of references (change to non-zero size)
        createShapes();
        // Create the actual Shape objects (sub types)
        // ....
    }
    
    private void createShapes(){
        shapes[0] = new Line(70,10,50,40,Color.BLACK);
        shapes[0].setVelocity(-100,50);
        shapes[1] = new Circle(100,50,50,false,Color.GREEN);
        shapes[1].setVelocity(90,50);
        shapes[2] = new Circle(100,50,50,false,Color.GREEN);
        shapes[2].setVelocity(180,50);
        shapes[3] = new Rectangle(100,100,50,10,Color.BLUE,false);
        shapes[3].setVelocity(80,190);
        shapes[4] = new Circle(100,50,50,false,Color.GREEN);
        shapes[4].setVelocity(500,50);
        shapes[5] = new Circle(100,50,50,false,Color.GREEN);
        shapes[5].setVelocity(110,110);
        shapes[6] = new Circle(100,50,50,false,Color.GREEN);
        shapes[6].setVelocity(20,500);
        shapes[7] = new Circle(100,50,50,false,Color.GREEN);
        shapes[7].setVelocity(200,50);
        shapes[8] = new Circle(100,50,50,false,Color.GREEN);
        shapes[8].setVelocity(180,180);
        shapes[9] = new Circle(100,50,50,false,Color.GREEN);
        shapes[9].setVelocity(320,150);
        
        
        for(Shape s : shapes){
            if(s instanceof FillableShape){
                FillableShape fs = (FillableShape) s;
                if(((FillableShape) s).isFilled()){
                    System.out.println("test"); 
                    ((FillableShape) s).setFilled(false);
                }
                else
                    ((FillableShape) s).setFilled(true);
            }
        }
    }

    /**
     * Sets the new dimensions, in pixels, for this world. The method could be
     * used for example when the canvas is reshaped.
     *
     * @param newWidth
     * @param newHeight
     */
    public void setDimensions(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    /**
     * Move the world one step, based on the time elapsed since last move.
     *
     * @param elapsedTimeNs the elpsed time in nanoseconds
     */
    public void move(long elapsedTimeNs) {
        // alterantive loop: for(Shape s : shapes) { ...
        for (int i = 0; i < shapes.length; i++) { 
            shapes[i].move(elapsedTimeNs);
            shapes[i].constrain(0, 0, width, height);
        }
        System.out.println(width + ", " + height);
        
    }

    /**
     * Returns a copy of the list of ball references.
     * Due to the implementation of clone, a shallow copy is returned.
     *
     * @return a copy of the list of balls
     */
    public Shape[] getShapes() {
        return (Shape[]) shapes.clone();
    }
}
