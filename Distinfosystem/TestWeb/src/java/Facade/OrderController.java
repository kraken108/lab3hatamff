package Facade;

import BO.Item;
import BO.Order;
import DBManager.DBOrder;
import DBManager.MysqlManager;
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
    public String sendOrder(Item[] items,String username){
        MysqlManager dbManager = new MysqlManager();
        try {
            Connection c = dbManager.getConnection();
            DBOrder dbOrder = new DBOrder();
            String s = dbOrder.sendOrder(items,c,username);
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
    public ArrayList<Order> getAllOrders() throws NamingException, SQLException{
         
        try {
            MysqlManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBOrder dbOrder = new DBOrder();
            ArrayList<Order> orders = dbOrder.getAllOrders(c);
            return orders;
        } catch (NamingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
    }
}
