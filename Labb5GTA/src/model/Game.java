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
public class Game {
    private ArrayList<Player> thePlayers;
    private Map theMap;
    
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
    
    public ArrayList<Player> getPlayers(){
        return (ArrayList<Player>) thePlayers.clone();
    }
    
}
