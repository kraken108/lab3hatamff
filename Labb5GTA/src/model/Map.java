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
    private final Image background;
    private final double width, height;
    
    public Map(Image background){
       this.background = background;
       this.width = background.getWidth();
       this.height = background.getHeight();
    }
    
    public Image getImage(){
        return background;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }
    
    
    
}
