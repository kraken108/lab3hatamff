/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jakob
 */
public class DBLogin {
    
    public Boolean tryLogin(String username, String password, Connection connection) throws SQLException {
        String query = "SELECT * FROM users WHERE passWord ='" + password + "' AND userName ='" + username + "';";
        System.out.println(query);

        Statement stmt;

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
            rs.close();
            stmt.close();
            return true;
        }else{
            rs.close();
            stmt.close();
            return false;
        }
    }
}
