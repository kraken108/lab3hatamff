/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;
import java.util.Random;
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
    
    public Car(Image image, double posX, double posY, LookDirection direction){
        setPosX(posX);
        setPosY(posY);
        setDirection(direction);
        this.theSprite = new Sprite(getPosX(),getPosY(),image,getDirection());
    }
    
    public void setDirection(LookDirection lookDirection){
        this.lookDirection=lookDirection;
    }
    
    public void setPosX(double posX){
        theSprite.setPosX(posX);
    }
    
    public void setPosY(double posY){
        theSprite.setPosY(posY);
    }
    
    public void moveX(double velX){
        theSprite.moveX(velX);
    }
    
    public void tick(){
        theSprite.moveX(velocity);
    }
    
    public LookDirection getDirection(){
        return lookDirection;
    }    
    
    public double getPosY(){
        return theSprite.getPosY();
    }
    
    
    public int RNG(){
        Random random = new Random();
        int rand = random.nextInt(1)+1;
        return rand;        
    }        
    
    public double getPosX(){
        return theSprite.getPosX();
    }
    
    public void drive(){
        posX=-250;
        posY=320;    
        setDirection(lookDirection.RIGHT);
        moveX(velocity);        
    }        
    
}
