/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Player implements Serializable{
    private ArrayList<Bullet> theBullets;
    private String name;
    private int frameX,frameY;
    private final int playerNo;
    private double frameWidth;
    private PlayerState playerState;
    private boolean gunLock;

    private double timeOfDeath;


    private Score theScore;

    private Sprite theSprite;


    private double velX; 
    private double velY;
    
    
    public Player(){
        theSprite = new Sprite();
        theBullets = new ArrayList<Bullet>();
        theScore = new Score();
        velX = 0;
        velY = 0;
        playerNo = 0;
        playerState=PlayerState.ALIVE;
        this.name = name;
        gunLock = false;
        updateFrame();
    }
    public Player(double posX,double posY,Image image, int playerNo,String name){
        theSprite = new Sprite(posX,posY,image,LookDirection.UP);
        theBullets = new ArrayList<Bullet>();
        velX = 0;
        velY = 0;
        theScore = new Score();
        this.playerNo = playerNo;
        this.frameWidth = image.getWidth()/4;
        playerState = PlayerState.ALIVE;
        this.name = name;
        gunLock = false;
        updateFrame();
    }
     
    public void setTimeOfDeath(double timeOfDeath){
        this.timeOfDeath=timeOfDeath;
    }
    
    public double getTimeOfDeath(){
        return timeOfDeath;
    }
    
    public boolean getGunLock(){
        return gunLock;
    }
    
    public void setGunLock(boolean gunLock){
        this.gunLock = gunLock;
    }
    
    public void setX(double x){
        theSprite.setPosX(x);
    }
    
    public void setY(double y){
        theSprite.setPosY(y);
    }
    
    public void SetPlayerState(PlayerState playerState){
        this.playerState = playerState;
    }
    public PlayerState getPlayerState(){
        return playerState;
    }
    
    public int getPlayerNo(){
        return this.playerNo;
    }
    
    public int getFrameX(){
        return frameX;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    private void updateFrame(){
        if(theSprite.getImageWidth()==256)
        switch(theSprite.getLookDirection()){
            case UP: frameX = 0;break;
            case LEFT: frameX = 64; break;
            case DOWN: frameX = 128; break;
            case RIGHT: frameX = 192; break;
        }
    }

    public double getFrameWidth(){
        return frameWidth;
    }
    
    public void setDirection(LookDirection lookDirection){
        theSprite.setLookDirection(lookDirection);
        updateFrame();
    }
    
    public double getX(){
        return theSprite.getPosX();
    }
    
    public double getY(){
        return theSprite.getPosY();
    }
    
    public void addKill(){
        theScore.addKill();
    }
    
    public void addDeath(){
        theScore.addDeath();
    }
    
    public Score getScore(){
        return theScore;
    }
    public LookDirection getDirection() {
        return theSprite.getLookDirection();
    }
    

    public void shoot(Image bullet){
        if(playerState==PlayerState.ALIVE && gunLock == false)
            theBullets.add(new Bullet(theSprite.getPosX(),theSprite.getPosY(),theSprite.getLookDirection(),bullet));
    }
    
    public ArrayList<Bullet> getBullets(){
        return (ArrayList<Bullet>) theBullets.clone();
    }
    
    public ArrayList<Bullet> getRealBullets(){
        return theBullets;
    }
    
    public void bulletsOutOfMap(){
        for(Bullet b : theBullets){
            if(b.getPosX()<0 || b.getPosX()>1000 || b.getPosY()<100 || b.getPosY()>650){
                theBullets.remove(b);
            }
        }  
    }
    
    public void tick(){
        double prevX = theSprite.getPosX(),prevY = theSprite.getPosY();
        theSprite.moveX(velX);
        theSprite.moveY(velY);
        outOfMap(prevX,prevY);
    }
    
    private void outOfMap(double prevX,double prevY){
        if(theSprite.getPosX()<-20 || theSprite.getPosX() > 980){
            theSprite.setPosX(prevX);
        }
        if(theSprite.getPosY()<100 || theSprite.getPosY() > 600){
            theSprite.setPosY(prevY);
        }
    }
    

    public void setVelX(double velX){
        this.velX = velX;
    }
    
    public void setVelY(double velY){
        this.velY = velY;
    }
}
