package lab3projekt;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {

    private double X2;
    private double Y2;

    public Line() {
        super();
        X2 = 0;
        Y2 = 0;    
    }

    public Line(double x, double y,double X2, double Y2,Color color) {
        super(x,y,color);
        this.X2 = X2;
        this.Y2 = Y2;
    }

    public double getX2() {
        return X2;
    }

    public double getY2() {
        return Y2;
    }

    public void setX2(double x) {

    }

    public void setY2(double y) {

    }

    @Override
    public void paint(GraphicsContext g) {

    }

    @Override
    public void constrain(double one, double two, double three, double four) {

    }

    public String toString() {
        String info = "";
        return info;
    }

}
