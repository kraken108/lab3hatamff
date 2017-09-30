package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to communicate with the database regarding Users.
 */
public class DBUser {
    
    /**
     * Returns a userId of a user with a username as param.
     * @param username
     * @param c
     * @return
     * @throws SQLException 
     */
    public int getUserId(String username,Connection c) throws SQLException{
        PreparedStatement getUserStatement = null;
        String getUserQuery = "SELECT * FROM Users WHERE username=?";
        
        getUserStatement = c.prepareStatement(getUserQuery);
        
        getUserStatement.setString(1, username);
        
        ResultSet rs = getUserStatement.executeQuery();
        if(rs.next()){
            int id = (int)rs.getObject("id");
            return id;
        }else{
            throw(new SQLException("No user found"));
        }
    }
}
