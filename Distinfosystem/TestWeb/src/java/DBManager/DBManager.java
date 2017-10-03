package DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * Interface to Database manager classes
 */
public interface DBManager {
    public Connection getConnection()throws NamingException, SQLException;
}
