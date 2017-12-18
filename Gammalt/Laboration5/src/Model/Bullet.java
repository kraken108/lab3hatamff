package model;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Bullet holds all nessecary elements for a visual bullet in the game
 * @author Jakob Danielsson & Michael Hjälmö
 */


public class Bullet extends Object implements Serializable{
    private final int velocity = 20;
    
    /**
     * Empty constructor
     */
    public Bullet(){
        super();
    }
    
    /**
     * Constructor
     * @param x X position of the bullet spawn point
     * @param y Y Position of the bullet spawn point
     * @param direction Direction of the bullet
     * @param image Image of the bullet
     */
    public Bullet(double x, double y, LookDirection direction,Image image){
        super(x,y,image.getWidth(),image.getHeight(),direction);
        calculateSpawn(direction,x,y);
    }
    
    /**
     * Update the bullets position
     */
    public void tick(){
        switch(getLookDirection()){
            case UP: moveY(-velocity);break;
            case DOWN: moveY(velocity);break;
            case LEFT: moveX(-velocity);break;
            case RIGHT: moveX(velocity);break;
        }
    }


    /**
     * Calculate spawn point depending of which way the player faces.
     * @param direction The direction
     * @param x The x position
     * @param y The y position
     */
    private void calculateSpawn(LookDirection direction,double x, double y){
        switch(direction){
            case UP: setPosX(getPosX()+17);break;
            case DOWN: setPosX(getPosX()+26); setPosY(getPosY()+56);break;
            case LEFT: setPosY(getPosY()+28);break;
            case RIGHT: setPosX(getPosX()+56); setPosY(getPosY()+26);break;
        }
    }
 
}
