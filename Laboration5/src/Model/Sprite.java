/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
abstract public class Sprite implements Serializable{
    private double posX,posY;
    private double imageWidth,imageHeight;
    private LookDirection lookDirection;
    private Image image;
    
    /**
     * Empty constructor that creates empty sprite
     */
    protected Sprite(){
        posX = 0;
        posY = 0;
        imageWidth = 0;
        imageHeight = 0;
        image = null;
        lookDirection = null;
    }

    /**
     * 
     * @param posX is the x-coordinate of the sprite
     * @param posY is the y-coordinate of the sprite
     * @param image is the image of the sprite
     * @param lookDirection is the LookDirection of the sprite
     */
    protected Sprite(double posX,double posY,Image image,LookDirection lookDirection){
        this.posX = posX;
        this.posY = posY;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        this.image = image;
        this.lookDirection = lookDirection;
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
     * 
     * @return the image
     */
    public Image getImage(){
        return image;
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
