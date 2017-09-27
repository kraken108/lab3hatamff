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
            Item i = new Item((String) rs.getObject("name"),(Float)rs.getObject("price"));
            items.add(i);
        }
        stmt.close();
        rs.close();
        return items;
    }
    
    
}
