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
        String query = "SELECT * FROM MusicAlbum WHERE " 
                        + searchBy + " LIKE '%" + searchWord +"%';";
        
        System.out.println(query);
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
            ResultSetMetaData metaData = rs.getMetaData();
            int ccount = metaData.getColumnCount();
            for (int c = 1; c <= ccount; c++) {
                System.out.print(metaData.getColumnName(c) + "\t");
            }
            System.out.println("");
            
            
            // Get the attribute values
            while (rs.next()) {
                
                for (int c = 1; c <= ccount; c++) {
                    System.out.print(rs.getObject(c) + "\t");
                }
                MusicAlbum m = new MusicAlbum();
                System.out.println("hej");
                m.setAlbumId(rs.getInt("albumId"));
                System.out.println("album id: " + m.getAlbumId());
                m.setTitle(rs.getString("title"));
                m.setPublishDate(rs.getString("releaseDate"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                System.out.println(m.toString());
                
                ResultSet tmp = stmt.executeQuery("SELECT * FROM ArtistAlbum NATURAL JOIN Artist "
                        + "WHERE albumId LIKE '"+m.getAlbumId()+"';");
                while(tmp.next()){
                    Artist a = new Artist();
                    a.setFirstName(tmp.getString("firstName"));
                    a.setLastName(tmp.getString("lastName"));
                    a.setSSN(tmp.getString("ssn"));
                    a.setNickName(tmp.getString("nickname"));
                    a.seteMail(tmp.getString("email"));
                    a.setPhoneNumber(tmp.getString("phoneNo"));
                    m.addArtist(a);
                }
                musicAlbums.add(m);

            }

        } catch(SQLException e){
            System.out.println(e);
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        } 
        return musicAlbums; 
    }
}
