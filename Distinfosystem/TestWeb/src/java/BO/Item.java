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
    private int inStock;
    private int id;
    
    
    
    public Item(String name, Float price,int inStock,int id){
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.id = id;
    }

    public Item(String name, Float price, String inStock, String id){
        this.name = name;
        this.price = price;
        
        String st = "";
        st += inStock.charAt(0);
        this.inStock = Integer.parseInt(st);
        
        String s = "";
        s += id.charAt(0);
        this.id = Integer.parseInt(s);
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
    
    @Override
    public String toString(){
        return "Product: " + getName() + " - Price:" + getPrice() + " - (" + getInStock() + ") in Stock";
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the inStock
     */
    public int getInStock() {
        return inStock;
    }
    
    
    
}
