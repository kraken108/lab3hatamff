/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DBLogin {
    
    public Boolean tryLogin(String username, String password, Connection connection) throws SQLException {
        PreparedStatement selectStatement = null;
        String query2 = "SELECT * FROM users WHERE password=? AND userName =?";        
        selectStatement = connection.prepareStatement(query2);
        selectStatement.setString(1, password);
        selectStatement.setString(2, username);
        ResultSet rs = selectStatement.executeQuery();
        
        if (rs.next()) {
            rs.close();
            selectStatement.close();
            return true;
        }else{
            rs.close();
            selectStatement.close();
            return false;
        }
    }
    
    public User getUserInfo(String username, Connection c) throws SQLException{
        PreparedStatement selectStatement = null;
        String query = "SELECT * FROM UserRights WHERE username=?";
        selectStatement = c.prepareStatement(query);
        selectStatement.setString(1,username);
        ResultSet rs = selectStatement.executeQuery();
        
        ArrayList<Rights> rights = new ArrayList<>();
        
        while(rs.next()){
            String rightname = (String)rs.getObject("rightsname");
            try{
                Rights right = Rights.valueOf(rightname);
                rights.add(right);
            }catch(IllegalArgumentException ex){
                return null;
            }
        }
        
        User user = new User(username,rights);
        
        
        return user;
    }
}
