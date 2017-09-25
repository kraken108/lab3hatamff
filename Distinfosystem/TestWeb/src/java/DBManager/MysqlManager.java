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
        connection = DriverManager.getConnection(server, "", "");
        System.out.println("Connected to database");
    }

    @Override
    public void getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean tryLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM User WHERE Password ='" + password + "' AND Username ='" + username + "';";
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

}
