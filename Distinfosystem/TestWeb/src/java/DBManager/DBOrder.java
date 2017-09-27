/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jakob
 */
public class DBOrder {

    public String sendOrder(Item[] items, Connection c) throws SQLException {
        for (int i = 0; i < items.length; i++) {
            PreparedStatement selectStatement = null;
            String selectString = "SELECT * FROM ItemStock WHERE itemId=?";
            selectStatement = c.prepareStatement(selectString);
            selectStatement.setInt(1, items[i].getId());
            ResultSet rs = selectStatement.executeQuery();
            if(rs.next()){
                
            }
        }

        String query = "SELECT * FROM Items";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {

        }
        stmt.close();
        rs.close();

        return "";

    }

}
