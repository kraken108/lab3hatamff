/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Bot {
    private Image image;
    private double posX,posY;
    
    public Bot(){
        posX = 500;
        posY = 350;
        image = new Image("images/BigBlueGuy.png");
    }
    
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }
    
    public Image getSprite(){
        return image;
    }
    
    public void setPosX(double posX){
        this.posX+=posX;
    }
    
    public void setPosY(double posY){
        this.posY+=posY;
    }
    
    
}
