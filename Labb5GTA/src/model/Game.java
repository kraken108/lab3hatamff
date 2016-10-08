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
        theMap = new Map(new Image("karta.png"));
    }
    
    public Image getBackground(){
        return theMap.getImage();
    }
    
}
