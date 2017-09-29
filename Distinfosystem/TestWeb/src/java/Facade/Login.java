/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BO.User;
import DBManager.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Jakob
 */
public class Login {


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
