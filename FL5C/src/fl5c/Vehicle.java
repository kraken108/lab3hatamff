/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fl5c;

/**
 *
 * @author micke1
 */
public abstract class Vehicle {
    
    private int wheels;
    private String color;
    
    public Vehicle(int wheels, String color){
        this.wheels=wheels;
        this.color=color;                
    }

    /**
     * @return the wheels
     */
    public int getWheels() {
        return wheels;
    }

    /**
     * @param wheels the wheels to set
     */
    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    public abstract String lisenceType();
        
    
    
}
