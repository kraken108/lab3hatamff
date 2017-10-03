/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class Order {
    private String username;
    private int orderId;
    private ArrayList<Item> items;
    
    
    public Order(String username, int orderId, ArrayList<Item> items){
        this.username = username;
        this.orderId = orderId;
        this.items = items;
    }
    
    @Override
    public String toString(){
        String s = "";
        
        s += "Order(" + orderId + "), Ordered by(" + username + "), Items(";
        for(Item i : items){
            s += i.getName() + ", ";
        }
        s += ")";
        
        return s;
    }
}
