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
<<<<<<< HEAD
 *
* @author Jakob Danielsson & Michael Hjälmö
=======
 * DatabaseCommunicator is an object for communicating with a SQL database
 * @author Jakob Danielsson & Michael Hjälmö
>>>>>>> 8c1acd9aabb1b0de6387e57a5e51cc8858d26309
 */
public class DatabaseCommunicator implements Queries{
    private String database;
    private String server;
    private String user, pwd;
    private Connection con;
    private PreparedStatement searchByArtist,searchByRating,searchByTitle,searchByGenre;
    
    /**
     * Constructor creating the connection
     * @throws Exception if something fails
     */
    public DatabaseCommunicator() throws Exception{
        database = "Company";
        server = "jdbc:mysql://localhost:3306/" + database + 
                "?UseClientEnc=UTF8";
        user = "clientapp";
        pwd = "qwerty";
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(server, user, pwd);
        } catch(SQLException e){
            if(con!=null) con.close();
            throw(new Exception(e));
        }
    }
    
    /**
     * Closes the connection to the database
     */
    @Override
    public void closeConnection(){
        try{
            con.close();
        }catch(SQLException e){}
    }
    
    /**
     * Execute query to search for all artists in the database
     * @return an arraylist of all artists in the database
     * @throws SQLException 
     */
    @Override
    public ArrayList<Person> getAllArtists(){
        String query = "SELECT * FROM Person WHERE profession LIKE 'Artist';";
        ArrayList<Person> theArtists = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                //Person p = new Person(rs.getInt("personId"),rs.getString("name"),rs.getString("country"),Profession.ARTIST,rs.getInt("age"));
                //theArtists.add(p);
            }
        }catch(SQLException e){
            return null;
        }
        return theArtists;
    }
    
    /**
     * Execute search query in database
     * @param query The query to execute
     * @return Arraylist of query results
     * @throws SQLException 
     */
    @Override
    public ArrayList<Media> searchAlbums(String searchWord, String searchBy){
        Statement stmt = null;
        Statement tmpstmt = null;
        ResultSet rs = null;
        String query = "";
        switch(searchBy){ 
            case "Rating": query = "SELECT * FROM Media WHERE avgRating LIKE '%"+searchWord+"%';"; 
            case "Title": query = "SELECT * FROM Media WHERE Title LIKE '%"+searchWord+"%';";
            case "Genre": query = "SELECT * FROM Media WHERE Genre LIKE '%"+searchWord+"%';";
            case "Artist": query = "SELECT * FROM MediaPerson NATURAL JOIN Media "
                                    + "WHERE personId IN (SELECT personId "
                                    + "FROM Person WHERE name "
                                    + "LIKE '%"+searchWord+"%');";
        }
        
        
        ArrayList<Media> musicAlbums = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
           
           
            while (rs.next()) {
                Media m = new Media();   
                //m.setMediaId(rs.getInt("mediaId"));
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
            try{
                if(con!=null) con.rollback();
            }catch(SQLException ex){
                
            }
            
            return null;
        }
        finally {
            if (stmt != null) {
                try{
                    stmt.close();
                }catch(SQLException ex2){
                    
                }
                
            }
            if(tmpstmt != null)
                try{
                    tmpstmt.close();
                }catch(SQLException ex3){
                    
                }
                
            if(rs != null)
                try{
                    rs.close();
                }catch(SQLException ex4){
                    
                }
            try{
                con.setAutoCommit(true);
            }catch(SQLException ex5){
                
            }
        }  
    }
    
    /**
     * Execute add review update
     * @param rating Rating of review
     * @param comment Comment of review
     * @param media The media to add the review to
     * @throws SQLException 
     */
    @Override
    public void submitReview(float rating,String comment,Media media) throws SQLException{
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
    
    /**
     * Execute add artist update
     * @param artist The artist to add
     * @throws SQLException 
     */
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
    
    /**
     * Execute add album update
     * @param media The album to add
     * @throws SQLException 
     */
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
                //media.setMediaId(rs.getInt(1));
            }
 
            for(Person p : media.getThePersons()){
                query = "INSERT INTO MediaPerson VALUES('"
                        + p.getPersonId() +"','" + media.getMediaId() + "');";
                stmt.executeUpdate(query);
            }
            
            con.commit();
        }catch(SQLException e){
            if(con!=null)con.rollback();
            throw e;
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
