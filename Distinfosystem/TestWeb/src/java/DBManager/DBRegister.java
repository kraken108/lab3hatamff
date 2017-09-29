/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.Connection;
import java.util.ArrayList;
import BO.Item;
import DBManager.*;
import Facade.Register;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import BO.Item;
import BO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jakob
 */
public class DBRegister {
        

    public ArrayList<Register> getRegistries() {
            ArrayList<Register> registers = null;
      try{
          DBManager dbManager = new MysqlManager();
          Connection c = dbManager.getConnection();
         // registers = getUsers(c);


          c.close();
      }
      catch (SQLException ex) {                 
          ex.printStackTrace();
      }
      catch (NamingException ex) {
          Logger.getLogger(DBRegister.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      if(registers == null){
          return null;
      }else{
          return registers;          
      }           
    }
    
    
    public void insertUsers(String userName, String passWord){
        
        String insertIntoUsers = "INSERT INTO USERS(userName,passWord)"
                + " VALUES(userName,passWord)";       
    }
    
    public ArrayList<User> getUsers(Connection connection){
        
        
        
        return null;       
    }   
    
}
