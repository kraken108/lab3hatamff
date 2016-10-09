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
    private Bullet bullet;
    private Game game;
   
    
    
    public Player(int sprite, Game game){
        if(sprite==0){
            this.sprite = new Image("BigBlueGuy.png");
            posX = 100;
            posY = 300;
            theBullets = new ArrayList<Bullet>();
            this.game=game;
        }
        else{
            this.sprite = new Image("BigRedGuy.png");
            posX = 900;
            posY = 300;
            theBullets = new ArrayList<Bullet>();
            this.game=game;
        }
        lookDirection = LookDirection.UP;
    }

    Player(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void tick(){
        for(int i=0; i<theBullets.size(); i++){
            bullet = theBullets.get(i);
            
            bullet.tick();
        }
    }
    
    public void addBullet(Bullet b){
        theBullets.add(b);
    }
    
    public void removeBullet(Bullet b){
        theBullets.remove(b);
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
    
    public void shoot(){
        
    }
    
    public ArrayList<Bullet> getBullets(){
        return (ArrayList<Bullet>) theBullets.clone();
    }
    
    public LookDirection move(LookDirection LD){
        switch(LD){
            case UP: posY--; break;
            case DOWN: posY++; break;
            case LEFT: posX--; break;
            case RIGHT: posX++; break;
        }
        return LD;
    }
}
