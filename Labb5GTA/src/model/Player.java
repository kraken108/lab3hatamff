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
    private LookDirection lookDirection;
    
    
    public Player(int sprite){
        if(sprite==0){
            this.sprite = new Image("BigBlueGuy.png");
            posX = 100;
            posY = 300;
        }
        else{
            this.sprite = new Image("BigRedGuy.png");
            posX = 900;
            posY = 300;
        }
        lookDirection = LookDirection.UP;
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
    
    public LookDirection getDirection() {
        return lookDirection;
    }
    
    public void move(LookDirection LD){
        switch(LD){
            case UP: posY--; break;
            case DOWN: posY++; break;
            case LEFT: posX--; break;
            case RIGHT: posX++; break;
        }
    }
}
