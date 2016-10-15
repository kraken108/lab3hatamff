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
public class Sprite implements Serializable{
    private double posX,posY;
    private double imageWidth,imageHeight;
    private LookDirection lookDirection;
    
    public Sprite(){
        posX = 0;
        posY = 0;
        imageWidth = 0;
        imageHeight = 0;
        lookDirection = null;
    }

    public Sprite(double posX,double posY,Image image,LookDirection lookDirection){
        this.posX = posX;
        this.posY = posY;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        this.lookDirection = lookDirection;
    }
    
    public void moveX(double velX){
        posX+=velX;
    }
    
    public void moveY(double velY){
        posY+=velY;
    }
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
     * @param posX the posX to set
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
     * @param posY the posY to set
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
     * @param lookDirection the lookDirection to set
     */
    public void setLookDirection(LookDirection lookDirection) {
        this.lookDirection = lookDirection;
    }
    
    
}
