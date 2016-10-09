/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Bullet {
    private Image sprite;
    private double posX,posY;
    
    public Bullet(double x, double y, Game game){
        this.posX=x;
        this.posY=y;
        this.sprite=new Image("bullet.png");
    }
    
    public void tick(){
        posY-=10;
    }
    
    public Image getSprite(){
        return sprite;
    }

    /**
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }
    
    
    
    
    
    
}
