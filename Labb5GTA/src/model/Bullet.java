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
public class Bullet implements Serializable{
    
    private Sprite theSprite;
    private final int velocity = 10;
    
    public Bullet(){
        this.theSprite = new Sprite(0,0,null,null);
    }
    
    public Bullet(double x, double y, LookDirection direction){
        switch(direction){
            case UP: x+=17;break;
            case DOWN: x+=26; y+=56;break;
            case LEFT: y+=28;break;
            case RIGHT: x+=56; y+=25;break;
        }
        this.theSprite = new Sprite(x,y,new Image("images/BigBullet.png"),direction);
    }
    
    public void tick(){
        switch(theSprite.getLookDirection()){
            case UP: theSprite.moveY(-velocity);break;
            case DOWN: theSprite.moveY(velocity);break;
            case LEFT: theSprite.moveX(-velocity);break;
            case RIGHT: theSprite.moveX(velocity);break;
        }
    }

    /**
     * @return the posX
     */
    public double getPosX() {
        return theSprite.getPosX();
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return theSprite.getPosY();
    }
    
    public void calculateSpawn(LookDirection direction,double x, double y){
        switch(direction){
            case UP: x+=17;break;
            case DOWN: x+=26; y+=56;break;
            case LEFT: y+=28;break;
            case RIGHT: x+=56; y+=25;break;
        }
    }
 
}
