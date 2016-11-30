/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Jakob
 */
public class DatabaseCommunicator {
    private String database;
    private String server;
    private String user, pwd;
    private Connection con;
    
    public DatabaseCommunicator() throws Exception{
        database = "Company";
        server = "jdbc:mysql://localhost:3306/" + database + 
                "?UseClientEnc=UTF8";
        user = "clientapp";
        pwd = "qwerty";
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //java.sql.Driver d=new com.mysql.jdbc.Driver();
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");
        } catch(Exception e){
            throw(e);
        }
    }
    
    private String createQuery(String searchWord,String searchBy){
        String query = "SELECT * FROM MusicAlbum,ArtistAlbum WHERE " 
                        + searchBy + " = '" + searchWord +"';";
        
        return query;
    }
    

    public ArrayList<MusicAlbum> searchAlbums(String searchWord,String searchBy) throws SQLException{
        String query = createQuery(searchWord,searchBy);
        Statement stmt = null;
        ArrayList<MusicAlbum> musicAlbums = new ArrayList<>();
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get the attribute names
            /*
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }
            System.out.println();
            */
            
            ResultSetMetaData metaData = rs.getMetaData();
            // Get the attribute values
            while (rs.next()) {
                // NB! This is an example, -not- the preferred way to retrieve data.
                // You should use methods that return a specific data type, like
                // rs.getInt(), rs.getString() or such.
                // It's also advisable to store each tuple (row) in an object of
                // custom type (e.g. Employee).
                musicAlbums.add((MusicAlbum)rs.getObject(1));
                    //System.out.print(rs.getObject(c) + "\t");
                //System.out.println();
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        } 
        return musicAlbums; 
    }
}
