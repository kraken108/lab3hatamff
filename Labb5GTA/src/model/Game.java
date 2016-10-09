/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.Thread.State;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Game {
    private ArrayList<Player> thePlayers;
    private Map theMap;
    private GameState gameState = GameState.MENU;
    
    
    
    
    public Game(){
        thePlayers = new ArrayList<Player>();
        theMap = new Map(new Image("karta.png"));
        for(int i = 0; i<2;i++){
            thePlayers.add(new Player(i));
        }
    }
    
    public Image getBackground(){
        return theMap.getImage();
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
    

    public Player getPlayer(int index){
        return thePlayers.get(index);
    }
    
}
