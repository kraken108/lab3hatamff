package model;
import java.io.Serializable;
import javafx.scene.image.Image;


/**
 * A car holds all necesarry information and methods for a visual car in the game
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class Car extends Object implements Serializable{
    
    private int velocity;
    private Image image;
    private LookDirection lookDirection;
    
    /**
     * Empty constructor
     */
    public Car(){
        super();
        calculateSpawn();
    }
    /**
     * Constructor
     * @param image Image of the car
     * @param direction Direction of the car
     */
    public Car(Image image,LookDirection direction){
        super(0,0,image.getWidth(),image.getHeight(),direction);
        this.image = image;
        calculateSpawn();
    }
    
    /**
     * @return The velocity of the car
     */
    public int getVelocity(){
        return velocity;
    }
    
    /**
     * Calculates a new spawn depending on which way the car is facing.
     */
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
    
    /**
     * Changes the velocity of the car.
     * @param velocity The new velocity
     */
    public void roadRage(int velocity){
        this.velocity=velocity;
    }
    
    /**
     * Set the direction the car is facing
     * @param lookDirection The direction
     */
    public void setDirection(LookDirection lookDirection){
        this.lookDirection=lookDirection;
    }
    
    /**
     * Update the cars position
     */
    public void tick(){
        moveX(velocity);
    }
    
    /**
     * @return the direction the car is facing.
     */
    public LookDirection getDirection(){
        return lookDirection;
    }    

    /**
     * 
     * @return Image of car
     */
    public Image getImage(){
        return image;
    }
}
