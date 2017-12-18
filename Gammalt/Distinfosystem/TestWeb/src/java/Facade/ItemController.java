package Facade;

import Model.Item;
import DBManager.*;
import ViewModel.ItemInfo;
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
    public ArrayList<ItemInfo> getItems() throws SQLException, NamingException {
        ArrayList<Item> items = null;
        try {
            DBItem dbItem = new DBItem();
            items = dbItem.getItems();
        } catch (SQLException ex) {
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }

        if(items == null){
            return null;
        }else{
            ArrayList<ItemInfo> itemInfo = new ArrayList<>();
            for(Item i : items){
                itemInfo.add(new ItemInfo(i.getName(),i.getPrice(),i.getInStock(),i.getId()));
            }
            return itemInfo;
        }
    }
    
}
