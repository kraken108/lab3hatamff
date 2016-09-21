
package test;
import javafx.scene.paint.Color;

abstract public class FillableShape extends Shape{
    private boolean filled;
    
    public FillableShape(){
        super(0,0,Color.BLACK);
    }
    public FillableShape(double x, double y, boolean filled,Color color){
        super(x,y,color);
        this.filled = filled;
    }
    
    public boolean isFilled(){
        return filled;
    }
    
    public void setFilled(boolean filled){
        
    }
    
}
