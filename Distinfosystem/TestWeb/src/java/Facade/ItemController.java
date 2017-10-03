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
 * ItemController is used to communicate with the database classes that retrieves Items.
 * 
 */
public class ItemController {

    /**
     * Get current items from the database
     * @return Array if Item
     * @throws SQLException
     * @throws NamingException 
     */
    public ArrayList<Item> getItems() throws SQLException, NamingException {
        ArrayList<Item> items = null;
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            
            DBItem dbItem = new DBItem();
            items = dbItem.getItems(c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }

        if(items == null){
            return null;
        }else{
            return items;
        }
    }
    
}
