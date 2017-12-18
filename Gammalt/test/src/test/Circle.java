
package test;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape{
    private double diameter;
    
    public Circle(){
        super(0,0,true,Color.BLACK);
        this.diameter = 10;
    }
    public Circle(double x, double y,double diameter,boolean filled,Color color){
        super(x,y,filled,color);
        this.diameter = diameter;
    }
    
    public double getDiameter(){
        return diameter;
    }
    
    public void setDiameter(double diameter){
        this.diameter = diameter;
    }
    
    @Override
    public void paint(GraphicsContext g) {
        if(isFilled()){
            g.setFill(getColor());
            g.fillOval(getX(), getY(), diameter/2, diameter/2);
        }
        else{
            g.setStroke(getColor());
            g.strokeOval(getX(), getY(), diameter/2, diameter/2);
        }
            
    }
    
    @Override
    public void constrain(
            double boxX, double boxY, 
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (getX()+(diameter/2) > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        
        if (getY() < boxY) {
            setVelocity(getDx(),Math.abs(getDy()));
        } else if (getY()+(diameter/2) > boxHeight) {
            setVelocity(getDx(),-Math.abs(getDy()));
        }
    }
    
    @Override
    public String toString(){
        String info = super.toString();
        info += "Diameter: " + diameter;
        return info;        
    }
    
}
