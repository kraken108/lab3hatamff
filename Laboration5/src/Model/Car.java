/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;

/**
 *
 * @author micke1
 */
public class Car {
    
    private Sprite theSprite;
    private final int velocity=2;
    private double posX;
    private double posY;
    private LookDirection lookDirection;
    
    public Car(Image image){
        this.theSprite = new Sprite(0,320,image,lookDirection);
        drive();
    }
    
    public void setDirection(LookDirection lookDirection){
        this.lookDirection=lookDirection;
    }
    
    public void setPosX(double posX){
        theSprite.setPosX=posX;
    }
    
    public void setPosY(double posY){
        theSprite.setPosY=posY;
    }
    
    public void moveX(double velX){
        theSprite.moveX(velX);
    }
    
    public void tic(){
        posX+=velocity;
    }
    
    public LookDirection getDirection(){
        return lookDirection;
    }    
    
    public double getPosY(){
        return theSprite.posY;
    }
    
    public double getPosx(){
        return theSprite.posX;
    }
    
    public void drive(){
        posX=-250;
        posY=320;    
        setDirection(lookDirection.RIGHT);
        moveX(velocity);        
    }        
    
}
