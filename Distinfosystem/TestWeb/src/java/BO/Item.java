/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

/**
 *
 * @author Jakob
 */
public class Item {
    private String name;
    private Float price;
    
    public Item(String name, Float price){
        this.name = name;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }
    
    
}
