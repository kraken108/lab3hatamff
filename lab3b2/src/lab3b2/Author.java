/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b2;

import java.io.Serializable;

/**
 *
 * @author micke1
 */
public class Author implements Serializable{
    
    private String name; //author name
    
    
    public Author(String name){
        this.name=name;
    }
    
    public Author(){
        name="Unknown";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
   
    
    
    
    
    
}
