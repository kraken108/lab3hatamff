package model;

import java.io.Serializable;

/**
 * Abstract class that represents a visual object and its elements
 * @author Jakob Danielsson & Michael Hjälmö
 */
abstract public class Object implements Serializable{
    private double posX,posY;
    private double imageWidth,imageHeight;
    private LookDirection lookDirection;
    
    /**
     * Empty constructor that creates empty object
     */
    protected Object(){
        posX = 0;
        posY = 0;
        lookDirection = null;
    }

    /**
     * Constructor
     * @param posX X coordinate value
     * @param posY Y coordinate value
     * @param imageWidth Width of image
     * @param imageHeight Height of image
     * @param lookDirection Look direction
     */
    protected Object(double posX,double posY,double imageWidth, double imageHeight,LookDirection lookDirection){
        this.posX = posX;
        this.posY = posY;
        this.lookDirection = lookDirection;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }
    
    /**
     * velX moves the sprite in X-space
     * @param velX Speed value in X-space
     */
    public void moveX(double velX){
        posX+=velX;
    }
    
    /**
    * velX moves the sprite in Y-space
    * @param velY Speed value in Y-space
    */
    public void moveY(double velY){
        posY+=velY;
    }
    
    
    /**
     * the vel values move the sprite in X - and Y - space
     * @param velX Speed value in X-space
     * @param velY Speed value in Y-space
     */
    public void move(double velX, double velY){
        posX+=velX;
        posY+=velY;
    }
    /**
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @param posX sets the X-value of the sprite
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public double getPosY(){
        return posY;
    }

    /**
     * @param posY sets the Y-value of the sprite
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    /**
     * @return the imageWidth
     */
    public double getImageWidth() {
        return imageWidth;
    }

    /**
     * @return the imageHeight
     */
    public double getImageHeight() {
        return imageHeight;
    }

    /**
     * @return the lookDirection
     */
    public LookDirection getLookDirection() {
        return lookDirection;
    }

    /**
     * @param lookDirection sets the LookDirection of the sprite
     */
    public void setLookDirection(LookDirection lookDirection) {
        this.lookDirection = lookDirection;
    }
    
    
}
