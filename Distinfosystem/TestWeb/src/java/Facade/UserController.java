package Facade;

import BO.User;
import DBManager.DBAdmin;
import DBManager.DBManager;
import DBManager.MysqlManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * UserController is used to retrieve user information from the database.
 */
public class UserController {

    /**
     * Returns an array of all users in the database.
     * @return Array of users
     * @throws SQLException
     * @throws NamingException 
     */
    public ArrayList<User> getUsers() throws SQLException, NamingException {
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            ArrayList<User> users = dbAdmin.getUsers(c);
            c.close();
            return users;
        } catch (SQLException ex) {
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }
    }
    
    /**
     * Returns information about a single user in the database.
     * @param username
     * @return User object
     * @throws SQLException
     * @throws NamingException 
     */
    public User getSingleUser(String username) throws SQLException, NamingException {
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            User user = dbAdmin.getSingleUser(username,c);
            c.close();
            return user;
        } catch (SQLException ex) {
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }
        
    }
    
    /**
     * Changes the username of a user in the database.
     * @param currentUsername
     * @param newUsername
     * @throws SQLException
     * @throws NamingException 
     */
    public void changeUsername(String currentUsername, String newUsername) throws SQLException, NamingException{
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.changeUsername(currentUsername,newUsername,c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        }
        
    }
    
    /**
     * Changes the password of a user in the database.
     * @param username
     * @param newPassword
     * @throws SQLException
     * @throws NamingException 
     */
    public void changePassword(String username, String newPassword) throws SQLException, NamingException{
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.changePassword(username,newPassword,c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        }
        
    }
    
    /**
     * Adds a right to a user in the database.
     * @param username
     * @param rightToAdd
     * @throws SQLException
     * @throws NamingException 
     */
    public void addRights(String username,String rightToAdd) throws SQLException, NamingException{
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.addRights(username,rightToAdd,c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        }
    }
    
    /**
     * Removes a right from a user in the database.
     * @param username
     * @param rightToRemove
     * @throws NamingException
     * @throws SQLException 
     */
    public void removeRights(String username,String rightToRemove) throws NamingException, SQLException{
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.removeRights(username,rightToRemove,c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        }
    }
    
    /**
     * Deletes a user from the database.
     * @param username
     * @throws NamingException
     * @throws SQLException 
     */
    public void deleteUser(String username) throws NamingException, SQLException{
        try {
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.deleteUser(username,c);
            c.close();
        } catch (SQLException ex) {
            throw(ex);
        }
    }
    
}
