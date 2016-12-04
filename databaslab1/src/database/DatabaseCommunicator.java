/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private PreparedStatement searchByArtist,searchByOther;
    
    public DatabaseCommunicator() throws SQLException,Exception{
        database = "Company";
        server = "jdbc:mysql://localhost:3306/" + database + 
                "?UseClientEnc=UTF8";
        user = "clientapp";
        pwd = "qwerty";
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(server, user, pwd);
            createPreparedStatements();
            System.out.println("Connected!");
        } catch(SQLException e){
            if(con!=null) con.close();
            throw(e);
        }
    }
    
    public void closeConnection(){
        try{
            con.close();
        }catch(SQLException e){}
    }
    
    public int newAlbumRequest(MusicAlbum album){
        try{
            addAlbum(album);
            return 1;
        }catch(SQLException e){
            return -1;
        }
    }

    public int rateRequest(float rating,String comment,MusicAlbum album){
        try{
            submitReview(rating,comment,album);
            return 1;
        }catch(SQLException e){
            return -1;
        }
    }
    
    public ArrayList<MusicAlbum> searchRequest(String searchWord,String searchBy){
        ArrayList<MusicAlbum> theAlbums = null;
        if(searchBy.equals("Artist")){
            try{
                searchArtistQuery(searchWord);
                theAlbums = searchAlbums(searchBy);
            }finally{
                return theAlbums;
            }
        } 
        else{
            try{
                searchAlbumQuery(searchWord,searchBy);
                theAlbums = searchAlbums(searchBy);
            }finally{
                return theAlbums;
            }
        }
                             
    }
    
    private void searchAlbumQuery(String searchWord,String searchBy) throws SQLException{
        try {
            searchByOther.setString(1, searchBy);
            searchByOther.setString(2, "%"+searchWord+"%");
        } catch (SQLException ex) {
            throw(ex);
        }
    }

    private void searchArtistQuery(String searchWord) throws SQLException{
        try {
            searchByArtist.setString(1,"%"+searchWord+"%");
        } catch (SQLException ex) {
            throw(ex);
        }
    }
    
    @Override
    public ArrayList<MusicAlbum> searchAlbums(String searchBy) throws SQLException{
        Statement stmt = null;
        Statement tmpstmt = null;
        ResultSet rs = null;
        ArrayList<MusicAlbum> musicAlbums = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            if(searchBy.equals("Artist")){
                rs = searchByArtist.executeQuery();
            }
            else{
                rs = searchByOther.executeQuery();
                System.out.println("hej");
            }
           
                
            while (rs.next()) {
                System.out.println("resultat!");
                MusicAlbum m = new MusicAlbum();   
                m.setAlbumId(rs.getInt("albumId"));
                m.setTitle(rs.getString("title"));
                m.setPublishDate(rs.getString("releaseDate"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                tmpstmt = con.createStatement();
                ResultSet tmp = tmpstmt.executeQuery("SELECT * FROM ArtistAlbum "
                        + "WHERE albumId LIKE '"+m.getAlbumId()+"';");
                while(tmp.next()){
                    m.addArtist(new Artist(tmp.getString("name")));
                }
                Boolean exists = false;
                for(MusicAlbum temp : musicAlbums){
                    if(temp.getAlbumId() == m.getAlbumId())
                        exists = true;
                }
                if(!exists)musicAlbums.add(m);
            }
            con.commit();
            return musicAlbums;
        } catch(SQLException e){
            if(con!=null) con.rollback();
            throw(e);
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
            if(tmpstmt != null)
                tmpstmt.close();
            if(rs != null)
                rs.close();
            con.setAutoCommit(true);
        }  
    }
    
    
    @Override
    public void submitReview(float rating,String comment,MusicAlbum album) throws SQLException{
        String query = "INSERT INTO review(rating,reviewText) VALUES('" 
                + rating + "','" + comment + "');";
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            query = "SELECT LAST_INSERT_ID();";
            rs = stmt.executeQuery(query);
            int reviewId;
            if(rs.next()){
                reviewId = rs.getInt(1);
                query = "INSERT INTO ReviewedAlbum VALUES('" 
                        + reviewId + "','" + album.getAlbumId() + "');";
                stmt.executeUpdate(query);
            }
            query = "SELECT albumId, AVG(rating) FROM ReviewedAlbum,review "
                    + "GROUP BY albumId HAVING albumId LIKE '" 
                    + album.getAlbumId() + "';";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                float newRating = rs.getFloat(2);
                query = "UPDATE MusicAlbum SET rating = '" + newRating + "' WHERE albumId = '" + album.getAlbumId() + "';";
                stmt.executeUpdate(query);
            }
            con.commit();
        }catch(SQLException e){
            if(con!=null) con.rollback();
            throw(e);
        }
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            con.setAutoCommit(true);
        }
    }
    
    
    @Override
    public void addAlbum(MusicAlbum album) throws SQLException{
        String query = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Artist> theArtists = album.getArtists();
        try{
            con.setAutoCommit(false);
            for(Artist a : theArtists){
                query = "SELECT * FROM Artist WHERE name LIKE '" 
                        + a.getName() +"';";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                if(!rs.next()){
                    query = "INSERT INTO Artist (name) VALUES('"+a.getName() +"');";
                    stmt.executeUpdate(query);
                }
            }
            query = "INSERT INTO MusicAlbum(title,releaseDate,genre) "
                + "VALUES('" + album.getTitle() + "','" 
                + album.getPublishDate() + "','"+album.getGenre() + "');";
            
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            
            query = "SELECT LAST_INSERT_ID();";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                album.setAlbumId(rs.getInt(1));
            }
 
            for(Artist a : album.getArtists()){
                System.out.println(a.getName());
                query = "INSERT INTO ArtistAlbum VALUES('"
                        + a.getName() +"','" + album.getAlbumId() + "');";
                stmt.executeUpdate(query);
            }
            
            con.commit();
        }catch(SQLException e){
            if(con!=null)con.rollback();
            throw(e);
        }finally{
            if(stmt!=null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
            con.setAutoCommit(true);
        }
        
    }
    
    private void createPreparedStatements() throws SQLException{
        try{
            String query = "SELECT * FROM ArtistAlbum NATURAL JOIN MusicAlbum "
                + "WHERE name IN (SELECT name "
                + "FROM Artist WHERE name "
                + "LIKE ?);";
            searchByArtist = con.prepareStatement(query);
        
            query = "SELECT * FROM MusicAlbum WHERE ? LIKE ?;";
            searchByOther = con.prepareStatement(query);
            
            
        }catch(SQLException e){
            throw(e);
        }
            
        
    }

}
