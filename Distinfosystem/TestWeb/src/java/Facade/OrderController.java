/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jakob
 */
public class OrderController {
    

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
