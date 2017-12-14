/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 *
 * @author Jakob
 */
public class ViewImage {
    private String imageData;
    private String username;
    
    public ViewImage(String imageData, String username) {
        this.imageData = imageData;
        this.username = username;
    }
    
    public ViewImage(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
    
    
}
