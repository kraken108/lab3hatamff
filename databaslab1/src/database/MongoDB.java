package database;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import static com.mongodb.client.model.Projections.fields;
import static java.lang.Integer.parseInt;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
         
    }
    
    @Override
    public void addAlbum(Media media) throws Exception {
        BasicDBObject document = new BasicDBObject();
        ArrayList<Person> theArtists = media.getThePersons();
        List<BasicDBObject> objList = new ArrayList<>();
        List<BasicDBObject> ratingList = new ArrayList<>();
        
        document.put("Title",media.getTitle());
        document.put("Genre",media.getGenre());
        document.put("release_date",media.getPublishDate());
        document.put("avgRating","0");
        System.out.println(media.toString());
        
        
        for(Person p : theArtists){
            BasicDBObject tempDocument = new BasicDBObject();
            tempDocument.put("name",p.getName());
            tempDocument.put("nationality",p.getCountry());
            tempDocument.put("age",p.getAge());
            tempDocument.put("profession","artist");
            objList.add(tempDocument);
        }
        document.put("theArtists",objList);
        document.put("ratings",ratingList);
        try{
            mediaTable.insert(document);
            System.out.println("lyckades");
        }catch(Exception e){
            throw(e);
        }
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
        ObjectId id = new ObjectId(media.getMediaId());
        System.out.println(id);
        BasicDBObject findQuery = new BasicDBObject("_id",id);
        BasicDBObject listItem = new BasicDBObject("ratings",new BasicDBObject("rating",rating).append("comment",comment));
        BasicDBObject updateQuery = new BasicDBObject ("$push",listItem);
        mediaTable.update(findQuery, updateQuery);
        DBCursor cursor = mediaTable.find(findQuery);
        while(cursor.hasNext()){
            int counter = 0;
            float total = 0;
            BasicDBObject obj = (BasicDBObject) cursor.next();
            BasicDBList list = (BasicDBList) obj.get("ratings");
            BasicDBObject[] listArray = list.toArray(new BasicDBObject[0]);
            for(BasicDBObject bdo : listArray){
                counter++;
                total+=bdo.getDouble("rating");
            }
            
            
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            formatter.setMaximumFractionDigits(1);
            formatter.setMinimumFractionDigits(1);
            formatter.setRoundingMode(RoundingMode.HALF_UP); 
            Float formatedFloat = new Float(formatter.format(total/counter));
            String s = formatedFloat.toString();
            System.out.println(total/counter);
            BasicDBObject doc = new BasicDBObject();
            System.out.println(formatedFloat);
            doc.put("avgRating",s);
            
            mediaTable.update(findQuery, new BasicDBObject("$set",doc));
        }
        
        
        
    }

    
    @Override
    public ArrayList<Media> searchAlbums(String searchWord, String searchBy) {

        DBCursor cursor = null;
        ArrayList<Media> theMedia = new ArrayList<>();
        BasicDBObject fields = new BasicDBObject();
        
        if(searchBy.equals("Title") || searchBy.equals("Genre")){
            fields.put(searchBy,searchWord);
        }
        else if(searchBy.equals("Artist")){
            fields.put("theArtists.name",java.util.regex.Pattern.compile(searchWord));
        }
        else{ 
            fields.put("avgRating",java.util.regex.Pattern.compile(searchWord));
        }
        
        cursor = mediaTable.find(fields);
        
        while(cursor.hasNext()){
            //System.out.println(cursor.next());
            Media media = new Media();
            BasicDBObject obj = (BasicDBObject) cursor.next();
            media.setTitle(obj.getString("Title"));
            media.setGenre(obj.getString("Genre"));
            media.setPublishDate(obj.getString("release_date"));
            
            media.setRating(Float.parseFloat(obj.getString("avgRating")));

            BasicDBList list = (BasicDBList) obj.get("theArtists");
            BasicDBObject[] listArray = list.toArray(new BasicDBObject[0]);
            for(BasicDBObject bdo : listArray){
                Person tempArtist = new Person();
                tempArtist.setName(bdo.getString("name"));
                media.addPerson(tempArtist);
            }
            String theString = obj.getObjectId("_id").toHexString();
            media.setMediaId(theString);
            theMedia.add(media);
        }
        return theMedia;
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
        try{
            mongo.close();
        }catch(Exception e){
            throw(e);
        }
    }
    
}
