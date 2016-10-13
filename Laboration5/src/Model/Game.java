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

/**
 *
 * @author Jakob
 */
public class Game implements Serializable{
    private ArrayList<Player> thePlayers;
    private ArrayList<Bullet> theBullets;
    private Map theMap;
    private GameState gameState = GameState.MENU;
    private Bot theBot;
    private long previousNs = 0;
    private final long FRAME_NS = 10_000_000;


    
    //private Score scoreBoard;
    
    
    
    public Game(){
        thePlayers = new ArrayList<Player>();
        //theMap = new Map(new Image("images/karta.png"));
        thePlayers.add(new Player(100,300,new Image("images/BigBlueGuy.png"),1));
        thePlayers.add(new Player(900,530,new Image("images/BigRedGuy.png"),2));
        theBot = new Bot();
        //scoreBoard = new Score();
    }
    
    public void paintScoreboard(){
        //scoreBoard.paintScore();
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
 
    public void respawn(){        
        for(Player p: thePlayers){
            if(p.getPlayerState()==PlayerState.DEAD){
                p.setY(60);
                p.setX(60);
                p.SetPlayerState(PlayerState.ALIVE); 
            }
        }

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
                if(k.getPlayerNo() != p.getPlayerNo()){
                    for(Bullet b : p.getRealBullets()){
                        if(b.getPosY() > k.getY() && b.getPosY()<(k.getY()+k.getFrameWidth())
                                && b.getPosX()>k.getX() && b.getPosX()<(k.getX()+k.getFrameWidth())){
                            System.out.println("Hit");
                            p.getRealBullets().remove(b);
                            k.SetPlayerState(PlayerState.DEAD);
                            k.setTimeOfDeath(System.nanoTime());
                        }                            
                    }
                }
            }
        }
        
    }

  
    


}
