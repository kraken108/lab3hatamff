/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Jakob
 */
public class UserController {

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
