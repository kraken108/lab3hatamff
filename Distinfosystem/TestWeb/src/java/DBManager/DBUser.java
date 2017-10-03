/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jakob
 */
public class DBUser {
    
    
    public int getUserId(String username,Connection c) throws SQLException{
        PreparedStatement getUserStatement = null;
        String getUserQuery = "SELECT * FROM Users WHERE username=?";
        
        getUserStatement = c.prepareStatement(getUserQuery);
        
        getUserStatement.setString(1, username);
        
        ResultSet rs = getUserStatement.executeQuery();

        
        
        if(rs.next()){
            int id = (int)rs.getObject("id");
            return id;
        }else{
            throw(new SQLException("No user found"));
        }
    }
}
