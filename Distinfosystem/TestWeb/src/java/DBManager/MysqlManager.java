/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Jakob
 */
public class MysqlManager implements DBManager {

    //private Connection connection;
    private final String contextLookup = "java:comp/env";
    private final String resourceName = "jdbc/Webbshop";
    
    @Override
    public Connection getConnection() throws NamingException, SQLException{
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup(contextLookup);
        String dataResourceName = resourceName;
        DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
        Connection connection = dataSource.getConnection();
        
        return connection;
    }
    
    
    
    public void Register(String userName, String passWord,Connection connection){
        Statement stmt = null;
        String query  = "INSERT INTO users userName" + userName + " AND passWord" + passWord;   
        
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MysqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    ///String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
     ///   + " values (?, ?, ?, ?, ?)";

    
    
}
