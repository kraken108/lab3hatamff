/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BO.Item;
import DBManager.DBOrder;
import DBManager.MysqlManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Jakob
 */
public class OrderController {
    
    
    
    public Boolean sendOrder(Item[] items){
        MysqlManager dbManager = new MysqlManager();
        try {
            Connection c = dbManager.getConnection();
            DBOrder dbOrder = new DBOrder();
           // dbOrder.sendOrder(items,c);
            
        } catch (NamingException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
