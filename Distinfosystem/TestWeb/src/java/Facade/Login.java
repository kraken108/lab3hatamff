/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import DBManager.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class Login {
    
    
    public Login(){
        
    }
    
    
    public String tryLogin(String username, String password){
        DBManager dbManager;
        try {
            dbManager = new MysqlManager();
            if(dbManager.tryLogin(username,password)){
                return "SUCCESSFUL";
        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
            return "UNSUCCESSFUL";
    }
}
