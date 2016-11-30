/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

/**
 *
 * @author micke1
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * NB! To run this application from a shell, e.g. Windows
 * command prompt, use
 * java -classpath ".;.\the_name_of_the_connector.jar" JDBCTest 
 * <username> <password>
 * In the above example, the jar is located in the current directory.
 * 
 * If you use Eclipse or NetBeans, read the documentation on where to 
 * put the jar file.
 * 
 * @author Anders LindstrÃ¶m, anderslm@kth.se
 */
public class JDBCTest {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java JDBCTest <user> <password>");
            System.exit(0);
        }

        String user = args[0]; // user name
        String pwd = args[1]; // password 
        String database = "Company"; // the name of the database in your DBMS
        String server
                = "jdbc:mysql://localhost:3306/" + database
                + "?UseClientEnc=UTF8";

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //java.sql.Driver d=new com.mysql.jdbc.Driver();
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");

            executeQuery(con, "SELECT * FROM Employee");
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
            }
        }
    }

    public static void executeQuery(Connection con, String query) throws SQLException {

        Statement stmt = null;
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }
            System.out.println();

            // Get the attribute values
            while (rs.next()) {
                // NB! This is an example, -not- the preferred way to retrieve data.
                // You should use methods that return a specific data type, like
                // rs.getInt(), rs.getString() or such.
                // It's also advisable to store each tuple (row) in an object of
                // custom type (e.g. Employee).
                for (int c = 1; c <= ccount; c++) {
                    System.out.print(rs.getObject(c) + "\t");
                }
                System.out.println();
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}

