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
public class DatabaseCommunicator implements sqlqueries{
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
    
    private void addNewArtist(Artist a){
        Statement stmt = null;
        String query = "INSERT INTO Artist (name) VALUES('"+a.getName() +"');";
        try{
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("lagt till ny");
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
    }
    private void checkNewArtists(ArrayList<Artist> theArtists) throws SQLException{
        String query = null;
        Statement stmt = null;
        for(Artist a : theArtists){
            query = "SELECT * FROM Artist WHERE name LIKE '" + a.getName() +"';";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()){
                addNewArtist(a);
            }
        }
    }
    
    public void newAlbumRequest(MusicAlbum album){
        try{
            checkNewArtists(album.getArtists());
            addAlbum(album);
        }catch(SQLException e){
            
        }
        
    }
    
    public ArrayList<MusicAlbum> searchRequest(String searchWord,String searchBy){
        
        if(searchBy=="Title" || searchBy=="Genre" || searchBy=="Rating"){
            try{
                return searchAlbums(searchAlbumQuery(searchWord,searchBy));
            }catch(Exception E){} 
        }
        else if(searchBy=="Artist"){
            try{
                return searchAlbums(searchAuthorQuery(searchWord,searchBy));
                
            }catch(Exception E){}
        }                      
        return null;
    }
    
    private String searchAlbumQuery(String searchWord,String searchBy){
        String query = "SELECT * FROM MusicAlbum WHERE " 
                        + searchBy + " LIKE '%" + searchWord +"%';";
        
        System.out.println(query);
        return query;
    }

    private String searchAuthorQuery(String searchWord,String searchBy){
        String query = "SELECT * FROM ArtistAlbum NATURAL JOIN MusicAlbum "
                + "WHERE name IN (SELECT name "
                + "FROM Artist WHERE name "
                + "LIKE '%" + searchWord + "%');";
        return query;
    }
    
    @Override
    public ArrayList<MusicAlbum> searchAlbums(String query) throws SQLException{
        Statement stmt = null;
        Statement tmpstmt = null;
        ArrayList<MusicAlbum> musicAlbums = new ArrayList<>();
        try {
            // Execute the SQL statement
            stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            
            // Get the attribute values
            while (rs.next()) {
                MusicAlbum m = new MusicAlbum();   
                m.setAlbumId(rs.getInt("albumId"));
                System.out.println(m.getAlbumId());
                m.setTitle(rs.getString("title"));
                System.out.println(m.getTitle());
                m.setPublishDate(rs.getString("releaseDate"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                tmpstmt = con.createStatement();
                ResultSet tmp = tmpstmt.executeQuery("SELECT * FROM ArtistAlbum "
                        + "WHERE albumId LIKE '"+m.getAlbumId()+"';");
                while(tmp.next()){
                    System.out.println(tmp.getString("name"));
                    //a.seteMail(tmp.getString("email"));
                    //a.setPhoneNumber(tmp.getString("phoneNo"));
                    m.addArtist(new Artist(tmp.getString("name")));
                }
                System.out.println(m.toString());
                Boolean exists = false;
                for(MusicAlbum temp : musicAlbums){
                    if(temp.getAlbumId() == m.getAlbumId())
                        exists = true;
                }
                if(!exists)musicAlbums.add(m);
            }

        } catch(SQLException e){
            System.out.println(e);
        }
        finally {
            if (stmt != null) {
                stmt.close();
                tmpstmt.close();
            }
        } 
        
        return musicAlbums; 
    }
    
    public void addAlbum(MusicAlbum album) throws SQLException{
        Statement stmt = null;
        String query = "INSERT INTO MusicAlbum(title,releaseDate,genre) "
                + "VALUES('" + album.getTitle() + "','" 
                + album.getPublishDate() + "','"+album.getGenre() + "');";
        try{
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            
            query = "SELECT LAST_INSERT_ID();";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                album.setAlbumId(rs.getInt(1));
            }
            
            
            for(Artist a : album.getArtists()){
                System.out.println(a.getName());
                query = "INSERT INTO ArtistAlbum VALUES('"
                        + a.getName() +"','" + album.getAlbumId() + "');";
                stmt.executeUpdate(query);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
       
    }
}
