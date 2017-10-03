package BO;

import java.util.ArrayList;

/**
 * Data representation of an Order from the database.
 */
public class Order {
    private String username;
    private int orderId;
    private ArrayList<Item> items;
    
    /**
     * Constructs a new Order.
     * @param username
     * @param orderId
     * @param items 
     */
    public Order(String username, int orderId, ArrayList<Item> items){
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
        
        s += "Order(" + orderId + "), Ordered by(" + username + "), Items(";
        for(Item i : items){
            s += i.getName() + ", ";
        }
        s += ")";
        
        return s;
    }
}
