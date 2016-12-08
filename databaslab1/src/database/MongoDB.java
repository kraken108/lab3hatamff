package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import model.Media;
import model.Person;

/**
 *
 * @author Jakob
 */
public class MongoDB implements Queries{
    private Mongo mongo;
    private DB db;
    private DBCollection artistTable,mediaTable;

    public MongoDB(){
        mongo = null;
        db = null;
        artistTable = null;
        mediaTable = null;
         // Connection to the MongoDB-Server
         try{
             mongo = new Mongo("localhost", 27017);
         }catch(Exception e){
             System.out.println(e);
         }
         db = mongo.getDB("theDB");
         artistTable = db.getCollection("theArtists");
         mediaTable = db.getCollection("theMedia");
         
    }
    
    @Override
    public void addAlbum(Media media) throws Exception {
        BasicDBObject document = new BasicDBObject();
        ArrayList<Person> theArtists = media.getThePersons();
        
        document.put("Title",media.getTitle());
        document.put("genre",media.getGenre());
        document.put("release_date",media.getPublishDate());

        System.out.println(media.toString());
        try{
            mediaTable.insert(document);
            System.out.println("lyckades");
        }catch(Exception e){
            throw(e);
        }
    }

    @Override
    public void addArtist(Person artist) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void submitReview(float rating, String comment, Media media) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Media> searchAlbums(String searchWord, String searchBy) {
        ArrayList<Media> theMedia = new ArrayList<>();
        
        BasicDBObject fields = new BasicDBObject();
        
        fields.put(searchBy,searchWord);
        
        DBCursor cursor = mediaTable.find(fields);
        
        while(cursor.hasNext()){
            Media media = new Media();
            BasicDBObject obj = (BasicDBObject) cursor.next();
            media.setTitle(obj.getString("title"));
            media.setGenre(obj.getString("genre"));
            media.setPublishDate(obj.getString("release_date"));
            theMedia.add(media);
        }
        return theMedia;
    }
    

    @Override
    public ArrayList<Person> getAllArtists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
