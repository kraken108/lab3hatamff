package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Registers a new user to the database.
 */
public class DBRegister {

   
 
    /**
     * Register a new user with a username and password.
     * @param connection
     * @param userName
     * @param passWord
     * @throws SQLException 
     */
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
