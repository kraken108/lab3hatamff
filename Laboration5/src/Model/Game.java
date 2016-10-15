/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;
import java.io.Serializable;
import static java.lang.Math.sqrt;
import java.lang.Thread.State;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import java.util.Random;

/**
 *
 * @author Jakob
 */
public class Game implements Serializable{
    private ArrayList<Player> thePlayers;
    private ArrayList<Bullet> theBullets;
    private GameState gameState = GameState.MENU;
    private Bot theBot;
    private ArrayList<Car> theCars;

    
    //private Score scoreBoard;
    
    
    public Game(){
        theCars = new ArrayList<Car>();
        thePlayers = new ArrayList<Player>();
        thePlayers.add(new Player());
        thePlayers.add(new Player());
        theBot = new Bot();
    }
    public Game(String player1name,String player2name,Image player1img,Image player2img){
        theCars = new ArrayList<Car>();
        thePlayers = new ArrayList<Player>();
        //theMap = new Map(new Image("images/karta.png"));
        thePlayers.add(new Player(100,300,player1img,1,player1name));
        thePlayers.add(new Player(900,530,player2img,2,player2name));
        theBot = new Bot();
        //scoreBoard = new Score();
    }
    
    public void paintScoreboard(){
        //scoreBoard.paintScore();
    }
    
    public void addCar(Image image){
        theCars.add(new Car(image));
    }

    public ArrayList<Car> getCar(){
        return (ArrayList<Car>) theCars.clone();
    }
    
    public GameState getState(){
        return gameState;
    }
    
    public void setGameState(GameState newState){
        
        if(newState == gameState){
            return;
        }
        
        if(gameState == GameState.MENU && newState == GameState.RUNNING){
            gameState = GameState.RUNNING;
            
        } else if(gameState == GameState.RUNNING && newState == GameState.GAMEOVER){
            gameState = GameState.GAMEOVER;
        
        
        } else if(gameState == GameState.GAMEOVER && newState == GameState.MENU){
            gameState = GameState.MENU;
        }        
    }    

    public ArrayList<Player> getPlayers(){
        return (ArrayList<Player>) thePlayers.clone();
    }
    
    public ArrayList<Bullet> getBullets(){
        return (ArrayList<Bullet>) theBullets.clone();
    }
    
    

    public Player getPlayer(int index){
        return thePlayers.get(index);
    }
    
    public Bot getBot(){
        return theBot;
    }
 
    public void respawn(double x, double y){        
        for(Player p: thePlayers){
            if(p.getPlayerState()==PlayerState.DEAD){
                p.setY(y);
                p.setX(x);
                p.SetPlayerState(PlayerState.ALIVE); 
            }
        }
    }    
    
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
    
    public int RNG(){
        Random random = new Random();
        int rand = random.nextInt(4)+1;
        return rand;
    }
    
    public double checkIfDead(){
        
        for(Player p: thePlayers){
            if(p.getPlayerState()==PlayerState.DEAD){
                double time=p.getTimeOfDeath();
                return time;
            }            
        }
        return 0;
    }
    
    public void followPlayer(){
        double x,y;
        x = thePlayers.get(0).getX() - theBot.getPosX();
        y = thePlayers.get(0).getY() - theBot.getPosY();
        
        double hyp = sqrt((x*x) + (y*y));
        
        x /= hyp;
        y /= hyp;
        
        theBot.setPosX(x*1);
        theBot.setPosY(y*1);
    }
    
    public void detectHit(){
        for(Player p : thePlayers){
            for(Player k : thePlayers){
                if(k.getPlayerState()==PlayerState.ALIVE)
                    if(k.getPlayerNo() != p.getPlayerNo()){
                        ArrayList<Bullet> bullets = p.getBullets();
                        for(int i = 0; i<bullets.size(); i++){
                            Bullet b = bullets.get(i);
                            if(b.getPosY() > k.getY() && b.getPosY()<(k.getY()+k.getFrameWidth())
                                    && b.getPosX()>k.getX() && b.getPosX()<(k.getX()+k.getFrameWidth())) {
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
  
    


}
