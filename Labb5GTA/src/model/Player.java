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
public class Player {
    private ArrayList<Bullet> theBullets;
    private String name;
    private int posX, posY;
    private Image sprite;
    
    public Player(int sprite){
        if(sprite==0){
            this.sprite = new Image("BigBlueGuy.png");
            posX = 100;
            posY = 300;
        }
        else{
            this.sprite = new Image("BigBlueGuy.png");
            posX = 900;
            posY = 300;
        }
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public Image getSprite(){
        return sprite;
    }
    
}
