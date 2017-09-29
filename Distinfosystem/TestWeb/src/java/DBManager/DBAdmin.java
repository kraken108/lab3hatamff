/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Rights;
import BO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DBAdmin {
    
    
    
    public ArrayList<User> getUsers(Connection c) throws SQLException{
        PreparedStatement selectUsersStatement = null;
        PreparedStatement getRightsStatement = null;
        
        
        //TODO: Change to userId instead of username
        
        
        String getRightsQuery = "SELECT * FROM UserRights WHERE username=?";
        String query = "SELECT * FROM Users";
        
        selectUsersStatement = c.prepareStatement(query);
        
        ResultSet rs = selectUsersStatement.executeQuery();
        
        ArrayList<User> users = new ArrayList<>();
        
        while(rs.next()){
            String username = (String) rs.getObject("userName");
            getRightsStatement = c.prepareStatement(getRightsQuery);
            getRightsStatement.setString(1, username);
            ResultSet rightsResult = getRightsStatement.executeQuery();
            ArrayList<Rights> rights = new ArrayList<>();
            while(rightsResult.next()){
                Rights right = Rights.valueOf((String)rightsResult.getObject("rightsname"));
            }
            
            User user = new User(username,rights);
            users.add(user);            
        }
        return users;
    }
    
    
    public User getSingleUser(String username,Connection c) throws SQLException{
        PreparedStatement getUserInfoStatement = null;
        
        
        //TODO: change username to userId
        String getUserInfoQuery = "SELECT * FROM UserRights WHERE username=?";
        getUserInfoStatement = c.prepareStatement(getUserInfoQuery);
        getUserInfoStatement.setString(1, username);
        ResultSet rs = getUserInfoStatement.executeQuery();
        ArrayList<Rights> rights = new ArrayList<>();
        
        while(rs.next()){
            String rightsString = (String)rs.getObject("rightsname");
            Rights right = Rights.valueOf(rightsString);
            rights.add(right);
        }

        User user = new User(username,rights);
        
        return user;
        
    }
    
    
    public void changeUsername(String currentUsername, String newUsername, Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        String changeUsernameString = "UPDATE Users SET userName=? WHERE userName=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, newUsername);
        changeUsernameStatement.setString(2, currentUsername);
        changeUsernameStatement.executeUpdate();
    }
    
    public void changePassword(String username, String newPassword, Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        String changeUsernameString = "UPDATE Users SET passWord=? WHERE userName=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, newPassword);
        changeUsernameStatement.setString(2, username);
        changeUsernameStatement.executeUpdate();
    }
    
    public void addRights(String username, String rightToAdd,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        
        //TODO: change username to userid
        
        String changeUsernameString = "INSERT INTO UserRights VALUES(?,?)";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, username);
        changeUsernameStatement.setString(2, rightToAdd);
        changeUsernameStatement.executeUpdate();
    }
    
    public void removeRights(String username, String rightToRemove,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        
        //TODO: change username to userid
        
        String changeUsernameString = "DELETE FROM UserRights WHERE username=? AND rightsname=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, username);
        changeUsernameStatement.setString(2, rightToRemove);
        changeUsernameStatement.executeUpdate();
    }
    
    public void deleteUser(String username,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        PreparedStatement deleteFromOrdersStatement = null;
        PreparedStatement deleteFromUsersStatement = null;
        
        //TODO: change username to userId
        
        
        String deleteFromOrdersQuery = "DELETE FROM Orders WHERE username=?";
        String changeUsernameString = "DELETE FROM UserRights WHERE username=?";
        String deleteFromUsersQuery = "DELETE FROM Users WHERE userName=?";
        
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, username);
        changeUsernameStatement.executeUpdate();
        
        deleteFromOrdersStatement = c.prepareStatement(deleteFromOrdersQuery);
        deleteFromOrdersStatement.setString(1, username);
        deleteFromOrdersStatement.executeUpdate();
        
        deleteFromUsersStatement = c.prepareStatement(deleteFromUsersQuery);
        deleteFromUsersStatement.setString(1, username);
        deleteFromUsersStatement.executeUpdate();
        
    }
    
}
