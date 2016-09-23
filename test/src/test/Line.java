package test;

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
        this.x=X2;
    }

    public void setY2(double y) {
        this.y=Y2;
    }
    
    @Override
    public void move(long elapsedTimeNs) {
        double dx = super.getDx(), dy = super.getDy();
        double x = dx * elapsedTimeNs / BILLION;
        double y = dy * elapsedTimeNs / BILLION;
        setX(getX()+x);
        setY(getY()+y);
        X2 += dx * elapsedTimeNs / BILLION;
        Y2 += dy * elapsedTimeNs / BILLION;
    }
    
    @Override
    public void paint(GraphicsContext g) {
        g.setStroke(getColor());
        g.strokeLine(super.getX(), super.getY(), X2, Y2);
    }
    
    @Override
    public void constrain(double boxX, double boxY, 
            double boxWidth, double boxHeight) {
        if (X2 < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (X2 > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (getX() > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        
        if (Y2 < boxY) {
            setVelocity(getDx(),Math.abs(getDy()));
        } else if (Y2 > boxHeight) {
            setVelocity(getDx(),-Math.abs(getDy()));
        }
        
        if (getY() < boxY) {
            setVelocity(getDx(),Math.abs(getDy()));
        } else if (getY() > boxHeight) {
            setVelocity(getDx(),-Math.abs(getDy()));
        }
    }
    
    @Override
    public String toString() {
        String info=super.toString();
        info += "X2: " + X2 + ", Y2: " + Y2;
        return info;
    }

}
