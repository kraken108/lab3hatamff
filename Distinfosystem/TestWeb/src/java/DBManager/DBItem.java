/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import Model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 * Used to communicate with the database regarding Items.
 */
public class DBItem {
 
    /**
     * Get all items from the database.
     * @param connection
     * @return
     * @throws SQLException 
     */
    public ArrayList<Item> getItems() throws SQLException, NamingException {
        DBManager dbManager = new MysqlManager();
        Connection connection = dbManager.getConnection();
            
        ArrayList<Item> items = new ArrayList<>();
        
        String query = "SELECT * FROM Items";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            int inStock = getInStock((int)rs.getObject("id"));
            Item i = new Item((String) rs.getObject("itemName"),(Float)rs.getObject("price"),inStock,(int)rs.getObject("id"));
            items.add(i);
        }
        stmt.close();
        rs.close();
        connection.close();
        return items;
    }

    /**
     * Gets information about an item if its in stock.
     * @param itemId
     * @param connection
     * @return nr of items in stock of a current itemId
     * @throws SQLException 
     */
    private int getInStock(int itemId) throws SQLException, NamingException{
        DBManager dbManager = new MysqlManager();
            Connection connection = dbManager.getConnection();
        
        String query = "SELECT * FROM ItemStock WHERE itemId="+itemId+";";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        int x = 0;
        while(rs.next()){
            x++;
        }
        stmt.close();
        rs.close();
        connection.close();
        return x;
    }
    
    /**
     * Returns an Item by searching for item id.
     * @param itemId
     * @param c
     * @return
     * @throws SQLException 
     */
    public Item getItemById(int itemId) throws SQLException, NamingException{
        DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            
        PreparedStatement stmt = null;
        String query = "SELECT * FROM Items WHERE id=?";
        
        stmt = c.prepareStatement(query);
        stmt.setInt(1, itemId);
        
        ResultSet rs = stmt.executeQuery();
        Item i = null;
        
        if(rs.next()){
            i = new Item((String)rs.getObject("itemName"),(Float)rs.getObject("price"),0,itemId);
            c.close();
            return i;
        }else{
            c.close();
            throw(new SQLException("Couldnt find item"));
        }
    }
    
}
