package Facade;

import Model.Item;
import Model.Order;
import DBManager.DBOrder;
import DBManager.MysqlManager;
import ViewModel.ItemInfo;
import ViewModel.OrderInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * OrderController is used to send new orders and read current orders from the database.
 */
public class OrderController {
    

    /**
     * Send a new order with a username the order is made by and an array of items.
     * @param items The items the order contains.
     * @param username The username the order is made by.
     * @return Message if the order was successful or not.
     */
    public String sendOrder(ItemInfo[] items,String username){
        try {
            DBOrder dbOrder = new DBOrder();
            
            Item[] itemModel = new Item[items.length];
            
            for(int i = 0; i<items.length; i++){
                if(items[i] == null){
                    
                }else{
                    itemModel[i] = new Item(items[i].getName(),items[i].getPrice(),items[i].getInStock(),items[i].getId());
                }
                
            }
            String s = dbOrder.sendOrder(itemModel,username);
            return s;
        } catch (NamingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }
    
    /**
     * Get all orders from the database. Used by stock staff.
     * @return An array of all available orders.
     * @throws NamingException
     * @throws SQLException 
     */
    public ArrayList<OrderInfo> getAllOrders() throws NamingException, SQLException{
         
        try {
            DBOrder dbOrder = new DBOrder();
            ArrayList<Order> orders = dbOrder.getAllOrders();
            
            ArrayList<OrderInfo> orderInfo = new ArrayList<>();
            
            for(Order o : orders){
                ArrayList<ItemInfo> itemInfo = new ArrayList<>();
                for(Item i : o.getItems()){
                    itemInfo.add(new ItemInfo(i.getName(),i.getPrice(),i.getInStock(),i.getId()));
                }
                orderInfo.add(new OrderInfo(o.getUsername(),o.getOrderId(),itemInfo));
            }
            
            return orderInfo;
        } catch (NamingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
    }
}
