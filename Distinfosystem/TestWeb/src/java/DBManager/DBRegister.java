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
    

    
   
   
 
    public void insertUsers(Connection connection, String userName, String passWord) throws SQLException{
        
        PreparedStatement myStmt = null;
        String query = "INSERT INTO users(userName,passWord) VALUES(?,?)";
        
        myStmt = connection.prepareStatement(query);
        
        myStmt.setString(1, userName);
        myStmt.setString(2,passWord);

        
        myStmt.executeUpdate();
        myStmt.close();
    }
  


    
}
