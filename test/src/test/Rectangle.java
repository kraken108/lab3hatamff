
package test;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FillableShape{
    private double width, height;
    
    public Rectangle(){
        super();
        width = 10;
        height = 10;
      
    }
    
    public Rectangle(double x, double y, double width
            , double height, Color color, boolean filled){
        
        super(x,y,filled,color);
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }
    
    @Override
    public void paint(GraphicsContext g){
        if(isFilled()){
            g.setFill(getColor());
            g.fillRect(getX(), getY(), width, height);
        }
        else{
            g.setStroke(getColor());
            g.strokeRect(getX(), getY(), width, height);
        }
    }
    
    @Override
    public void constrain(
            double boxX, double boxY, 
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()),getDy());
        } else if (getX()+width > boxWidth) {
            setVelocity(-Math.abs(getDx()),getDy());
        }
        
        if (getY() < boxY) {
            setVelocity(getDx(),Math.abs(getDy()));
        } else if (getY()+height > boxHeight) {
            setVelocity(getDx(),-Math.abs(getDy()));
        }
    }
    
    public String toString(){
        String info = super.toString();
        info += "width: " + width + "height: " + height;
        return info;
    }
}
