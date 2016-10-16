package model;
import java.io.Serializable;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.Random;
import View.*;
/**
 * A Game is an object that holds all nessecary objects for the whole game
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class Game implements Serializable{
    private ArrayList<Player> thePlayers;
    private GameState gameState = GameState.MENU;
    private ArrayList<Car> theCars;

   
    /**
     * Empty constructor creating empty game
     */
    public Game(){
        theCars = new ArrayList<Car>();
        thePlayers = new ArrayList<Player>();
        thePlayers.add(new Player());
        thePlayers.add(new Player());
    }
    
    /**
     * Constructor creating the game
     * @param player1name The name of player 1
     * @param player2name The name of player 2
     * @param player1img The image of player 1
     * @param player2img The image of player 2
     */
    public Game(String player1name,String player2name,Image player1img,Image player2img){
        theCars = new ArrayList<Car>();
        thePlayers = new ArrayList<Player>();
        thePlayers.add(new Player(50,150,player1img,1,player1name));
        thePlayers.add(new Player(900,530,player2img,2,player2name));
    }

    /**
     * Add a car to the game.
     * @param image The image of the car
     * @param direction The look direction the car is going
     */
    public void addCar(Image image,LookDirection direction){
        theCars.add(new Car(image,direction));
    }

    /**
     * @return a list of all cars in the game
     */
    public ArrayList<Car> getCar(){
        return (ArrayList<Car>) theCars.clone();
    }
    
    /**
     * @return the state of the game
     */
    public GameState getState(){
        return gameState;
    }
    


    /**
     * @return a list of all players in the game
     */
    public ArrayList<Player> getPlayers(){
        return (ArrayList<Player>) thePlayers.clone();
    }


    /**
     * @param index Index of the players position in the player list
     * @return the player at the stated <em>index</em>
     */
    public Player getPlayer(int index){
        return thePlayers.get(index);
    }
    
    /**
     * Respawn a player at the stated coordinates.
     * @param x X value of the new spawn
     * @param y Y value of the new spawn
     */
    public void respawn(double x, double y){        
        for(Player p: thePlayers){
            if(p.getPlayerState()==PlayerState.DEAD){
                p.setPosY(y);
                p.setPosX(x);
                p.SetPlayerState(PlayerState.ALIVE); 
            }
        }
    }    
    
    /**
     * randomizes which one of the four spawn points the new spawn will get
     */
    public void randSpawn(){
        int rand = RNG();
        if(rand == 1){
            respawn(50,200);
        }
        if(rand == 2){
            respawn(50,500);
        }
        if(rand == 3){
            respawn(900,200);
        }
        if(rand == 4){
            respawn(900,500);
        }
    }
    
    /**
     * randomizes a number between 1 and 4
     * @return the randomized number
     */
    public int RNG(){
        Random random = new Random();
        int rand = random.nextInt(4)+1;
        return rand;
    }
    
    /**
     * Checks if players are dead
     * @return the time the player died
     */
    public double checkIfDead(){
        for(Player p: thePlayers){
            if(p.getPlayerState()==PlayerState.DEAD){
                double time=p.getTimeOfDeath();
                return time;
            }            
        }
        return 0;
    }
    
    /**
     * Collision detection for cars and players.
     * Checks if a player is hit by any of the cars in the game.
     * Kills the player if collision is detected.
     */
    public void detectCarHit(){
        for(Player p : thePlayers){
            if(p.getPlayerState()==PlayerState.ALIVE)
            for(Car c : theCars){
                if(p.getPosX()>c.getPosX()&&p.getPosX()<c.getPosX()+c.getImage().getWidth())
                    if(p.getPosY()+p.getImage().getHeight()>c.getPosY()&&p.getPosY()<c.getPosY()+c.getImage().getHeight()){
                        p.removeKill();
                        p.addDeath();
                        p.SetPlayerState(PlayerState.DEAD);
                        p.setTimeOfDeath(System.nanoTime());   
                    }
            }
        }
    }
    

    /**
     * Collision detection for players and bullets
     * Kills the player if hit by any bullet, and removes the bullet.
     */
    public void detectHit(){
        for(Player p : thePlayers){
            for(Player k : thePlayers){
                if(k.getPlayerState()==PlayerState.ALIVE)
                    if(k.getPlayerNo() != p.getPlayerNo()){
                        ArrayList<Bullet> bullets = p.getBullets();
                        for(int i = 0; i<bullets.size(); i++){
                            Bullet b = bullets.get(i);
                            if(b.getPosY() > k.getPosY() && b.getPosY()<(k.getPosY()+k.getFrameWidth())
                                    && b.getPosX()>k.getPosX() && b.getPosX()<(k.getPosX()+k.getFrameWidth())) {
                                p.removeBullet(i);
                                p.addKill();
                                k.addDeath();
                                k.SetPlayerState(PlayerState.DEAD);

                                k.setTimeOfDeath(System.nanoTime());                            
                        }                            
                    }

                }
            }
        }        
    }
  
    /**
     * Detects if a bullet hits any of the cars.
     * Removes the bullet if collision is detected.
     */
    public void bulletHitCar(){
        for(Car c: theCars){
            for(Player p: thePlayers){
                ArrayList<Bullet> bullets = p.getBullets();
                for(int i=0; i<bullets.size(); i++){
                    Bullet b = bullets.get(i);
                    if(b.getPosX()>c.getPosX()&&b.getPosX()<c.getPosX()+c.getImage().getWidth())
                        if(b.getPosY()>c.getPosY()&&b.getPosY()<c.getPosY()+c.getImage().getHeight()){
                            p.removeBullet(i);                            
                    }
                }
            }
        }
    }

    /**
     * Removes cars that are out of the map.
     */
    public void removeCar(){
        for(int i=0; i<theCars.size(); i++)
            if(theCars.get(i).getPosX()>1124 || theCars.get(i).getPosX()<-400){
                System.out.println("RÄDD OCH ÄGD AV PROFESSOR BENGTSSON");
                theCars.remove(i);
            }
    }
}
