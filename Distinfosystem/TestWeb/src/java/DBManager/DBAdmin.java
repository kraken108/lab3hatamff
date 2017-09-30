package DBManager;

import BO.Rights;
import BO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Used to communicate with the database by administrators
 */
public class DBAdmin {

    
    /**
     * Get all users from the database and their rights.
     * @param c
     * @return
     * @throws SQLException 
     */
    public ArrayList<User> getUsers(Connection c) throws SQLException{
        PreparedStatement selectUsersStatement = null;
        PreparedStatement getRightsStatement = null;
        
        
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
    
    
    /**
     * Get all information about a single user from the database
     * @param username
     * @param c
     * @return
     * @throws SQLException 
     */
    public User getSingleUser(String username,Connection c) throws SQLException{
        PreparedStatement getUserInfoStatement = null;
        
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
    
    /**
     * Change username of a user in the database.
     * @param currentUsername
     * @param newUsername
     * @param c
     * @throws SQLException 
     */
    public void changeUsername(String currentUsername, String newUsername, Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        String changeUsernameString = "UPDATE Users SET userName=? WHERE userName=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, newUsername);
        changeUsernameStatement.setString(2, currentUsername);
        changeUsernameStatement.executeUpdate();
    }
    
    /**
     * Change the password of a user in the database.
     * @param username
     * @param newPassword
     * @param c
     * @throws SQLException 
     */
    public void changePassword(String username, String newPassword, Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        String changeUsernameString = "UPDATE Users SET passWord=? WHERE userName=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, newPassword);
        changeUsernameStatement.setString(2, username);
        changeUsernameStatement.executeUpdate();
    }
    
    /**
     * Add rights to a user in the database.
     * @param username
     * @param rightToAdd
     * @param c
     * @throws SQLException 
     */
    public void addRights(String username, String rightToAdd,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        
        
        String changeUsernameString = "INSERT INTO UserRights VALUES(?,?)";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, username);
        changeUsernameStatement.setString(2, rightToAdd);
        changeUsernameStatement.executeUpdate();
    }
    
    /**
     * Remove rights from a user in the database.
     * @param username
     * @param rightToRemove
     * @param c
     * @throws SQLException 
     */
    public void removeRights(String username, String rightToRemove,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        
        
        String changeUsernameString = "DELETE FROM UserRights WHERE username=? AND rightsname=?";
        changeUsernameStatement = c.prepareStatement(changeUsernameString);
        changeUsernameStatement.setString(1, username);
        changeUsernameStatement.setString(2, rightToRemove);
        changeUsernameStatement.executeUpdate();
    }
    
    /**
     * Deletes a user from the database.
     * @param username
     * @param c
     * @throws SQLException 
     */
    public void deleteUser(String username,Connection c) throws SQLException{
        PreparedStatement changeUsernameStatement = null;
        PreparedStatement deleteFromOrdersStatement = null;
        PreparedStatement deleteFromUsersStatement = null;
               
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
