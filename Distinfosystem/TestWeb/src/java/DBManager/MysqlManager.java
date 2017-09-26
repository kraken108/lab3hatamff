/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Item;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class MysqlManager implements DBManager {

    private Connection connection;

    public MysqlManager() throws ClassNotFoundException, SQLException {
        String database = "Webbshop";
        String server = "jdbc:mysql://localhost:3306/" + database
                + "?UseClientEnc=UTF8";

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(server, "reguser", "abc123");
        System.out.println("Connected to database");
    }

    @Override
    public void getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean tryLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE passWord ='" + password + "' AND userName ='" + username + "';";
        System.out.println(query);

        Statement stmt;

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Item> getItems() throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        
        String query = "SELECT * FROM Items";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        

        while(rs.next()){
            Item i = new Item((String) rs.getObject("name"),(Float)rs.getObject("price"));

            items.add(i);
        }
        return items;
    }
    
    
    public void Register(String userName, String passWord){
        
        
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
