/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BO.Item;
import DBManager.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Jakob
 */
public class ItemController {

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = null;
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            
            DBItem dbItem = new DBItem();
            items = dbItem.getItems(c);
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(items == null){
            return null;
        }else{
            return items;
        }
    }
    
}
