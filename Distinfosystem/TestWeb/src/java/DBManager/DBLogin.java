package DBManager;

import BO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Used to communicate with the database regarding logging in to the webbshop.
 */
public class DBLogin {
    
    /**
     * Try to login using a username and a password.
     * @param username
     * @param password
     * @param connection
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * Get information about a single user.
     * @param username
     * @param c
     * @return
     * @throws SQLException 
     */
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
