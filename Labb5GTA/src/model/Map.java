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
public class Map {
    private Image background;
    private int width, height;
    
    public Map(Image background){
       this.background = background;
    }
    
    public Image getImage(){
        return background;
    }
}
