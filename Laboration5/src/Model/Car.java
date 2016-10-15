/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

import java.util.Random;

import javafx.scene.image.ImageView;


/**
 *
 * @author micke1
 */
public class Car {
    
    private Sprite theSprite;
    private int velocity;
    private double posX;
    private double posY;
    private LookDirection lookDirection; 


    public Car(Image image,LookDirection direction){
        this.lookDirection = direction;
        if(direction==LookDirection.RIGHT){
            this.theSprite = new Sprite(-250,490-image.getHeight(),image,lookDirection);
            velocity = 5;
        }
        else{
            this.theSprite = new Sprite(1024,270,image,lookDirection);
            velocity = -5;
        }
        
    }
    
    public void roadRage(int velocity){
        this.velocity=velocity;
    }
    
    public int getVelocity(){
        return velocity;
    }
    
    public Image getImage(){
        return theSprite.getImage();
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
