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
public class Bullet extends Sprite implements Serializable{
    private final int velocity = 20;
    
    public Bullet(){
        super(0,0,null,null);
        //this.theSprite = new Sprite(0,0,null,null);
    }
    
    public Bullet(double x, double y, LookDirection direction,Image image){
        super(x,y,image,direction);
        calculateSpawn(direction,x,y);
    }
    
    public void tick(){
        switch(getLookDirection()){
            case UP: moveY(-velocity);break;
            case DOWN: moveY(velocity);break;
            case LEFT: moveX(-velocity);break;
            case RIGHT: moveX(velocity);break;
        }
    }


    public void calculateSpawn(LookDirection direction,double x, double y){
        switch(direction){
            case UP: setPosX(getPosX()+17);break;
            case DOWN: setPosX(getPosX()+26); setPosY(getPosY()+56);break;
            case LEFT: setPosY(getPosY()+28);break;
            case RIGHT: setPosX(getPosX()+56); setPosY(getPosY()+26);break;
        }
    }
 
}
