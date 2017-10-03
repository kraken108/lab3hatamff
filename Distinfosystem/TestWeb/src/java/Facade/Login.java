package Facade;

import BO.User;
import DBManager.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * Login is used to communicate with the classes that retrieves login information.
 */
public class Login {


    /**
     * Try to login by providing a username and a password.
     * @param username
     * @param password
     * @return String explaining the result, if it's successful or not.
     * @throws SQLException
     * @throws NamingException 
     */
    public String tryLogin(String username, String password) throws SQLException, NamingException {
        DBManager dbManager;
        try {
            dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBLogin dbLogin = new DBLogin();
            Boolean success = dbLogin.tryLogin(username,password,c);
            c.close();
            if (success) {
                return "SUCCESSFUL";
            }else{
                return "UNSUCCESSFUL";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        } catch (NamingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
    }
    
    /**
     * getUserInfo takes a username and returns a User object with all information about the user.
     * @param username
     * @return User object
     * @throws SQLException
     * @throws NamingException 
     */
    public User getUserInfo(String username) throws SQLException, NamingException{
        try{
            DBManager dbManager = new MysqlManager();
            Connection c = dbManager.getConnection();
            DBLogin dbLogin = new DBLogin();
            User user = dbLogin.getUserInfo(username,c);
            c.close();
            return user;
        }catch(SQLException ex){
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }
    }
}
