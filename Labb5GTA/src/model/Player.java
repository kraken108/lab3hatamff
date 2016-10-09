/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Player {
    private ArrayList<Bullet> theBullets;
    private String name;
    private int posX, posY;
    private int frameX,frameY;
    private Image sprite;
    private LookDirection lookDirection;

    private Bullet bullet;
    private Game game;
   
    

    private double velX;
    private double velY;

    
    public Player(int sprite){
        if(sprite==0){
            this.sprite = new Image("BigBlueGuy.png");
            posX = 100;
            posY = 300;
            theBullets = new ArrayList<Bullet>();
        }
        else{
            this.sprite = new Image("BigRedGuy.png");
            posX = 900;
            posY = 300;
            theBullets = new ArrayList<Bullet>();
        }
        lookDirection = LookDirection.UP;
        updateDirection();
        velX = 0;
        velY = 0;
    }
    public int getFrameX(){
        return frameX;
    }
    private void updateDirection(){
        switch(lookDirection){
            case UP: frameX = 0;break;
            case LEFT: frameX = 64; break;
            case DOWN: frameX = 128; break;
            case RIGHT: frameX = 192; break;
        }
    }

    
    public void add(){
        for(int i=0; i<theBullets.size(); i++){
            bullet = theBullets.get(i);
            
            bullet.tick();
        }
    }
    
    public void addBullet(Bullet b){
        theBullets.add(b);
    }
    
    public void removeBullet(Bullet b){
        theBullets.remove(b);
    }
    
    public void setDirection(LookDirection lookDirection){
        this.lookDirection = lookDirection;
        updateDirection();
    }
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public Image getSprite(){
        return sprite;
    }
    
    public LookDirection getDirection() {
        return lookDirection;
    }
    

    public void shoot(){
        theBullets.add(new Bullet(posX,posY,lookDirection));
        
    }
    
    public ArrayList<Bullet> getBullets(){
        return (ArrayList<Bullet>) theBullets.clone();
    }
    


    public void tick(){
        int prevX = posX,prevY = posY;
        posX+= velX;
        posY+= velY;
        outOfMap(prevX,prevY);
    }
    
    private void outOfMap(int prevX,int prevY){
        if(posX<0 || posX > 980){
            posX = prevX;
        }
        if(posY<100 || posY > 575){
            posY = prevY;

        }
    }
    
    public void setVelX(double velX){
        this.velX = velX;
    }
    
    public void setVelY(double velY){
        this.velY = velY;
    }
}
