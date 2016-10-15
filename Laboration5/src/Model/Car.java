/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A car holds all necesarry information and methods for a visual car in the game
 * @author micke1
 */
public class Car extends Sprite implements Serializable{
    
    private int velocity;
    private LookDirection lookDirection;
    
    public Car(){
        super();
        calculateSpawn();
    }
    public Car(Image image,LookDirection direction){
        super(0,0,image,direction);
        calculateSpawn();
        
    }
    
    public int getVelocity(){
        return velocity;
    }
    
    public void calculateSpawn(){
        if(getLookDirection()==LookDirection.RIGHT){
            setPosX(-250);
            setPosY(490-getImageHeight());
            velocity = 5;
        }
        else{
            setPosX(1024);
            setPosY(270);
            velocity = -5;
        }
    }
    
    public void roadRage(int velocity){
        this.velocity=velocity;
    }
    
    public void setDirection(LookDirection lookDirection){
        this.lookDirection=lookDirection;
    }
    
    
    public void tick(){
        moveX(velocity);
    }
    
    public LookDirection getDirection(){
        return lookDirection;
    }    
    
    
    public void drive(){
        setPosX(getPosX()-250);
        setPosY(getPosY()+320);  
        setDirection(lookDirection.RIGHT);
        moveX(velocity);        
    }        
    
}
