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
    private int frameX,frameY;
    private LookDirection direction;
    private Sprite theSprite;
    
    
    public Bullet(double x, double y, LookDirection direction){
        //this.theSprite = new Sprite(x,y,new Image("images/BigBullet.png"));
        
        this.sprite=new Image("images/BigBullet.png");
        this.direction=direction;
        switch(direction){
            case UP: this.posX=x+17; this.posY=y;break;
            case DOWN: this.posX=x+26; this.posY=y+56;break;
            case LEFT: this.posX=x; this.posY=y+28;break;
            case RIGHT: this.posX=x+56; this.posY=y+25;break;
        }
        frameX = 0;
        frameY = 0;
    }
    
    public int getFrameX(){
        return frameX;
    }
    
    public int getFrameY(){
        return frameY;
    }
    
    public void tick(){
        switch(direction){
            case UP: posY-=10;break;
            case DOWN: posY+=10;break;
            case LEFT: posX-=10;break;
            case RIGHT: posX+=10;break;
        }
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
