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
 * Used to retrieve a connection to the database from the Apache connection pool.
 */
public class MysqlManager implements DBManager {

    //private Connection connection;
    private final String contextLookup = "java:comp/env";
    private final String resourceName = "jdbc/Webbshop";
    
    /**
     * Returns a database connection from the connection pool.
     * @return
     * @throws NamingException
     * @throws SQLException 
     */
    @Override
    public Connection getConnection() throws NamingException, SQLException{
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup(contextLookup);
        String dataResourceName = resourceName;
        DataSource dataSource = (DataSource) environmentContext.lookup(dataResourceName);
        Connection connection = dataSource.getConnection();
        
        return connection;
    }    
    
}
