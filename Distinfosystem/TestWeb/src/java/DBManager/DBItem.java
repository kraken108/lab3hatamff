/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DBItem {
 
    public ArrayList<Item> getItems(Connection connection) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        
        String query = "SELECT * FROM Items";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            int inStock = getInStock((int)rs.getObject("id"),connection);
            Item i = new Item((String) rs.getObject("itemName"),(Float)rs.getObject("price"),inStock,(int)rs.getObject("id"));
            items.add(i);
        }
        stmt.close();
        rs.close();
        return items;
    }

    private int getInStock(int itemId,Connection connection) throws SQLException{
        
        String query = "SELECT * FROM ItemStock WHERE itemId="+itemId+";";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        int x = 0;
        while(rs.next()){
            x++;
        }
        stmt.close();
        rs.close();
        return x;
    }
    
    
}
