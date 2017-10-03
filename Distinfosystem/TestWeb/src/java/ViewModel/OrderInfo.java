/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class OrderInfo {
    private String username;
    private int orderId;
    private ArrayList<ItemInfo> items;
    
    /**
     * Constructs a new Order.
     * @param username
     * @param orderId
     * @param items 
     */
    public OrderInfo(String username, int orderId, ArrayList<ItemInfo> items){
        this.username = username;
        this.orderId = orderId;
        this.items = items;
    }
    
    /**
     * Constructs a string with the order information.
     * @return 
     */
    @Override
    public String toString(){
        String s = "";
        
        s += "Order(" + getOrderId() + "), Ordered by(" + getUsername() + "), Items(";
        for(ItemInfo i : getItems()){
            s += i.getName() + ", ";
        }
        s += ")";
        
        return s;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @return the items
     */
    public ArrayList<ItemInfo> getItems() {
        return items;
    }
    
    
}
