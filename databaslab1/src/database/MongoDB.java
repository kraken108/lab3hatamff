package database;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import static com.mongodb.client.model.Projections.fields;
import static java.lang.Integer.parseInt;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import model.Media;
import model.Person;
import model.Profession;
import org.bson.types.ObjectId;

/**
 *
 * @author Jakob
 */
public class MongoDB implements Queries{
    private Mongo mongo;
    private DB db;
    private DBCollection mediaTable;
    private DBCollection artistTable;
    
    public MongoDB(){
        
        mongo=null;
        db=null;
        mediaTable=null;
        artistTable=null;
        
        
         // Connection to the MongoDB-Server
         try{
             mongo = new Mongo("localhost", 27017);
         }
         catch(Exception e){
             System.out.println(e);
         }
         
         db = mongo.getDB("theDB");
         mediaTable = db.getCollection("theMedia");
         artistTable = db.getCollection("theArtists");
         
         //table.drop();
    }
    @Override
    public void addAlbum(Media media) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addArtist(Person artist) throws Exception {
        BasicDBObject document = new BasicDBObject();
        document.put("name", artist.getName());
        document.put("nationality", artist.getCountry());
        document.put("age", artist.getAge());
        document.put("profession", "artist");
        artistTable.insert(document);
    }

    @Override
    public void submitReview(float rating, String comment, Media media) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Media> searchAlbums(String searchWord, String searchBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public ArrayList<Person> getAllArtists() {
        ArrayList<Person> tempPersons = new ArrayList<>();
        DBCursor cursor = artistTable.find();
                
        while(cursor.hasNext()){
            BasicDBObject obj = (BasicDBObject) cursor.next();
            Person p = new Person();
            p.setName(obj.getString("name"));
            p.setAge(obj.getInt("age"));
            p.setCountry(obj.getString("nationality"));
            p.setProfession(Profession.ARTIST);
            String theString = obj.getObjectId("_id").toHexString();
            System.out.println(theString);
            p.setPersonId (theString);
            tempPersons.add(p);
        }        
        return (ArrayList<Person>) tempPersons.clone();
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
