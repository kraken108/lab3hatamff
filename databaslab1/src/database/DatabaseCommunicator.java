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
import model.*;

/**
 *
* @author Jakob Danielsson & Michael Hjälmö
 */
public class DatabaseCommunicator implements sqlqueries{
    private String database;
    private String server;
    private String user, pwd;
    private Connection con;
    private PreparedStatement searchByArtist,searchByRating,searchByTitle,searchByGenre;
    
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
    
    public ArrayList<Person> allArtistsRequest(){
        try{
            return getAllArtists();
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    
    
    
    public int newAlbumRequest(Media album){
        try{
            addAlbum(album);
            return 1;
        }catch(SQLException e){
            System.out.println(e);
            return -1;
        }
    }

    public int addArtistRequest(Person artist){
        try{
            addArtist(artist);
            return 1;
        }catch(SQLException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public int rateRequest(float rating,String comment,Media album){
        try{
            submitReview(rating,comment,album);
            return 1;
        }catch(SQLException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public ArrayList<Media> searchRequest(String searchWord,String searchBy){
        ArrayList<Media> theAlbums = null;
        if(searchBy.equals("Artist")){
            try{
                theAlbums = searchAlbums(searchArtistQuery(searchWord));
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                return theAlbums;
            }
        } 
        else{
            try{
                theAlbums = searchAlbums(searchAlbumQuery(searchWord,searchBy));
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                return theAlbums;
            }
        }
                             
    }
    
    private String searchAlbumQuery(String searchWord,String searchBy) throws SQLException{

        switch(searchBy){ 
            case "Rating": return "SELECT * FROM Media WHERE avgRating LIKE '%"+searchWord+"%';"; 
            case "Title": return "SELECT * FROM Media WHERE Title LIKE '%"+searchWord+"%';";
            case "Genre": return "SELECT * FROM Media WHERE Genre LIKE '%"+searchWord+"%';";
        }
        return null;
    }

    private String searchArtistQuery(String searchWord) throws SQLException{

            return "SELECT * FROM MediaPerson NATURAL JOIN Media "
                + "WHERE personId IN (SELECT personId "
                + "FROM Person WHERE name "
                + "LIKE '%"+searchWord+"%');";
            
    }
    
    @Override
    public ArrayList<Person> getAllArtists() throws SQLException{
        String query = "SELECT * FROM Person WHERE profession LIKE 'Artist';";
        ArrayList<Person> theArtists = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                Person p = new Person(rs.getInt("personId"),rs.getString("name"),rs.getString("country"),Profession.ARTIST,rs.getInt("age"));
                theArtists.add(p);
            }
        }catch(SQLException e){
            throw(e);
        }
        return theArtists;
    }
    
    @Override
    public ArrayList<Media> searchAlbums(String query) throws SQLException{
        Statement stmt = null;
        Statement tmpstmt = null;
        ResultSet rs = null;
        ArrayList<Media> musicAlbums = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
           
           
            while (rs.next()) {
                System.out.println("resultat!");
                Media m = new Media();   
                m.setMediaId(rs.getInt("mediaId"));
                m.setTitle(rs.getString("title"));
                m.setPublishDate(rs.getString("releaseDate"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("avgRating"));
                tmpstmt = con.createStatement();
                ResultSet tmp = tmpstmt.executeQuery("SELECT * FROM MediaPerson NATURAL JOIN Person "
                        + "WHERE mediaId LIKE '"+m.getMediaId()+"';");
                while(tmp.next()){
                    m.addPerson(new Person(tmp.getString("name")));
                }
                Boolean exists = false;
                for(Media temp : musicAlbums){
                    if(temp.getMediaId() == m.getMediaId())
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
    public void submitReview(float rating,String comment,Media media) throws SQLException{
        String query = "INSERT INTO review(rating,reviewText) VALUES('" 
                + rating + "','" + comment + "');";
        System.out.println(rating);
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
                query = "INSERT INTO ReviewedMedia VALUES('" 
                        + reviewId + "','" + media.getMediaId() + "');";
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
    public void addArtist(Person artist) throws SQLException{
        String query = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.createStatement();
            con.setAutoCommit(false);
            query = "INSERT INTO Person(name,country,age,profession) VALUES ('"
                    + artist.getName() + "','" + artist.getCountry() 
                    + "','" + artist.getAge() + "','Artist');";
            stmt.executeUpdate(query);
            con.commit();
        }catch(SQLException e){
            con.rollback();
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
    
    @Override
    public void addAlbum(Media media) throws SQLException{
        String query = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            query = "INSERT INTO Media(title,releaseDate,genre,mediaType) "
                + "VALUES('" + media.getTitle() + "','" 
                + media.getPublishDate() + "','"+media.getGenre() + "','Album');";
            
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            
            query = "SELECT LAST_INSERT_ID();";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                System.out.println("next: "+rs.getInt(1));
                media.setMediaId(rs.getInt(1));
            }
 
            for(Person p : media.getThePersons()){
                System.out.println("persons: " + media.getMediaId());
                System.out.println(p.getName());
                query = "INSERT INTO MediaPerson VALUES('"
                        + p.getPersonId() +"','" + media.getMediaId() + "');";
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
            String query = "SELECT * FROM MediaPerson NATURAL JOIN Media "
                + "WHERE personId IN (SELECT personId "
                + "FROM Person WHERE name "
                + "LIKE ?);";
            searchByArtist = con.prepareStatement(query);
        
            query = "SELECT * FROM Media WHERE avgRating LIKE ?;";
            searchByRating = con.prepareStatement(query);
            
            query = "SELECT * FROM Media WHERE Title LIKE ?;";
            searchByTitle = con.prepareStatement(query);
            
            query = "SELECT * FROM Media WHERE Genre LIKE ?;";
            searchByGenre = con.prepareStatement(query);

        }catch(SQLException e){
            throw(e);
        }
            
        
    }
}
