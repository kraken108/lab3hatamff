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
 * A player holds all neccesary elements that is special for a player.
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class Player extends Object implements Serializable{
    private ArrayList<Bullet> theBullets;
    private String name;
    private int frameX,frameY;
    private final int playerNo;
    private double frameWidth;
    private PlayerState playerState;
    private boolean gunLock;

    private double timeOfDeath;
    private Score theScore;

    private double velX; 
    private double velY;
    
    /**
     * Empty constructor.
     */
    public Player(){
        super();
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
    /**
     * Constructor
     * @param posX The x coordinate of the spawn point
     * @param posY The y coordinate of the spawn point
     * @param image The image of the player
     * @param playerNo The players number
     * @param name The name of the player
     */
    public Player(double posX,double posY,Image image, int playerNo,String name){
        super(posX,posY,image.getWidth(),image.getHeight(),LookDirection.UP);
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
    
    /**
     * Removes a bullet at stated index.
     * @param index Index of the bullets position in the bullet list
     */
    public void removeBullet(int index){
        try{
            theBullets.remove(index);
        }catch(IndexOutOfBoundsException e){
        }
        
    }
    
    /**
     * Sets the time of death when a player dies.
     * @param timeOfDeath The time in nano seconds
     */
    public void setTimeOfDeath(double timeOfDeath){
        this.timeOfDeath=timeOfDeath;
    }
    
    /**
     * @return The time the player died, in nano seconds.
     */
    public double getTimeOfDeath(){
        return timeOfDeath;
    }
    
    /**
     * @return the state of the gun, to prevent the player from spam-shooting
     */
    public boolean getGunLock(){
        return gunLock;
    }
    
    /**
     * Set the gun to locked or not locked.
     * @param gunLock Locked or not
     */
    public void setGunLock(boolean gunLock){
        this.gunLock = gunLock;
    }
    
    /**
     * Set the state of the player
     * @param playerState The state
     */
    public void SetPlayerState(PlayerState playerState){
        this.playerState = playerState;
    }
    
    /**
     * @return the state of the player
     */
    public PlayerState getPlayerState(){
        return playerState;
    }
    
    /**
     * @return the number of the player
     */
    public int getPlayerNo(){
        return this.playerNo;
    }
    
    /**
     * @return the x coordinate of the sprite frame
     */
    public int getFrameX(){
        return frameX;
    }
    
    /**
     * Set the name of the player
     * @param name The name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * @return The name of the player
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Update the frame x coordinate depending on which way the character is
     * facing.
     */
    private void updateFrame(){
        if(getImageWidth()==256)
        switch(getLookDirection()){
            case UP: frameX = 0;break;
            case LEFT: frameX = 64; break;
            case DOWN: frameX = 128; break;
            case RIGHT: frameX = 192; break;
        }
    }

    /**
     * @return The width of the frame
     */
    public double getFrameWidth(){
        return frameWidth;
    }
    
    /**
     * Set look direction for the player and update the frame
     * @param lookDirection The direction
     */
    public void setDirection(LookDirection lookDirection){
        setLookDirection(lookDirection);
        updateFrame();
    }
    
    /**
     * Add a kill to the players score.
     */
    public void addKill(){
        theScore.addKill();
    }
    
    /**
     * Remove a kill from the players score.
     */
    public void removeKill(){
        theScore.removeKill();
    }
    
    /**
     * Add a death to the players score.
     */
    public void addDeath(){
        theScore.addDeath();
    }
    
    /**
     * @return The players score
     */
    public Score getScore(){
        return theScore;
    }

    /**
     * Shoots a bullet from the players position.
     * @param bullet Image of the bullet
     */
    public void shoot(Image bullet){
        if(playerState==PlayerState.ALIVE && gunLock == false)
            theBullets.add(new Bullet(getPosX(),getPosY(),getLookDirection(),bullet));
    }
    
    /**
     * @return A list of the players bullets
     */
    public ArrayList<Bullet> getBullets(){
        return (ArrayList<Bullet>) theBullets.clone();
    }
    
    /**
     * Remove the bullet if it is out of the map border.
     */
    public void bulletsOutOfMap(){
        for(int i = 0; i<theBullets.size();i++){
            Bullet b = theBullets.get(i);
            if(b.getPosX()<0 || b.getPosX()>1000 || b.getPosY()<100 || b.getPosY()>650){
                theBullets.remove(i);
            }
        }
    }
    
    /**
     * Updates the players position with the help of the players velocity.
     */
    public void tick(){
        double prevX = getPosX(),prevY = getPosY();
        moveX(velX);
        moveY(velY);
        outOfMap(prevX,prevY);
    }
    
    /**
     * Prevents the player from walking out of the map border.
     * @param prevX The previous X coordinate
     * @param prevY The previous Y coordinate
     */
    private void outOfMap(double prevX,double prevY){
        if(getPosX()<-20 || getPosX() > 980){
            setPosX(prevX);
        }
        if(getPosY()<100 || getPosY() > 600){
            setPosY(prevY);
        }
    }
    
    /**
     * Sets the X velocity
     * @param velX The velocity
     */
    public void setVelX(double velX){
        this.velX = velX;
    }
    
    /**
     * Sets the Y velocity
     * @param velY The velocity
     */
    public void setVelY(double velY){
        this.velY = velY;
    }
}
