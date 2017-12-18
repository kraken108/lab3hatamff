/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.image.Image;


/**
 *
 * @author Jakob
 */
public class Map implements Serializable{
    private final double width, height;
    
    public Map(Image background){

       this.width = background.getWidth();
       this.height = background.getHeight();
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
