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
          
          
          
          c.close();
      }catch (SQLException ex){
          ex.printStackTrace();
      }catch (NamingException ex){
          
      }
      
      if(registers == null){
          return null;
      }else{
          return registers;          
      }
            
    }
    
    
    
    /*
    
    try{
       
        DBManager dbManager = new MysqlManager();
        Connection c = dbManager.getConnection();
        DBItem dbItem = new DBItem();
    }
    catch{
    
    }
    */
    
}
