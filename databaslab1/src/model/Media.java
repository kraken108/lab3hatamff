package model;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Media is an object that holds information about an Album, Movie, etc. 
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class Media {
    
    private int mediaId;
    private String title;
    private String publishDate;
    private String genre;
    private ArrayList<Person> thePersons;
    private float rating;
    private String thePersonsString;
    private MediaType mediaType;

    
    /**
    * Empty constructor
    */
    public Media(){
        thePersons = new ArrayList<>();
        this.mediaId=0;
        this.title=null;
        this.publishDate=null;
        this.genre=null;
        this.rating=0;

        thePersonsString = "";

        mediaType=MediaType.UNKNOWN;

    }

    /**
     * Constructor
     * @param title The title of the media
     * @param publishDate The date the media was published
     * @param genre The genre of the media
     * @param mediaType The type of the media
     */
    public Media(String title, String publishDate, String genre, MediaType mediaType){
        thePersons = new ArrayList<>();
        this.title=title;
        this.publishDate=publishDate;
        this.genre=genre;
        this.rating=0;
        this.rating=0;
        this.mediaType=mediaType;
    }

    /**
     * @return the mediaId
     */
    public int getMediaId() {
        return mediaId;
    }

    /**
     * @param mediaId the mediaId to set
     */
    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }
 
    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {

        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
        formatter.setRoundingMode(RoundingMode.HALF_UP); 
        Float formatedFloat = new Float(formatter.format(rating));
        this.rating=formatedFloat;
    }   
    

    /**
     * Returns a string of all creators
     * @return the thePersonsString
     */
    public String getThePersonsString(){
        return thePersonsString;
    }
    

    /**
     * @return the mediaType
     */
    public MediaType getMediaType() {
        return mediaType;
    }

    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }    

    @Override
    public String toString(){
        String artists = "";
        for(Person p : getThePersons()){
            if(p!=null)
                artists += p.getName() + ", ";
        }
        String tmp = "Album: " + getTitle() + "\t|\tRelease date: " 
                    + getPublishDate() + "\t|\tGenre: " + getGenre() 
                + "\t|\tRating: " + getRating() + "\t|\tArtists: " + artists;
        return tmp;
    }

    /**
     * @return the thePersons
     */
    public ArrayList<Person> getThePersons() {
        return (ArrayList<Person>) thePersons.clone();
    }

    /**
     * Adds a person to the media and fixes thePersonsString with commas.
     * @param thePersons the thePersons to set
     */
    public void addPerson(Person p) {
        if(!thePersons.contains(p)){
            if(thePersons.size()>0){
                thePersonsString += ", " + p.getName();
            }
            else
                thePersonsString += p.getName();
            thePersons.add(p);
        }
    }    
}