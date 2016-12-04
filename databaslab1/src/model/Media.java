/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static oracle.jrockit.jfr.events.Bits.floatValue;


/**
 *
 * @author Jakob
 */
public class Media {
    
    private int albumId;
    private String title;
    private String publishDate;
    private String genre;
    private ArrayList<Person> thePersons;
    private float rating;
    private String theArtistsString;


    private Person person;
    private MediaType mediaType;

    
    public Media(){
        thePersons = new ArrayList<>();
        this.albumId=0;
        this.title=null;
        this.publishDate=null;
        this.genre=null;
        this.rating=0;

        theArtistsString = "";

        mediaType=MediaType.UNKNOWN;

    }

    

    public Media(String title, String publishDate, String genre, MediaType mediaType){
        thePersons = new ArrayList<>();
        this.title=title;
        this.publishDate=publishDate;
        this.genre=genre;
        this.rating=rating;
        this.rating=0;


        this.mediaType=mediaType;

    }

    /**
     * @return the albumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId the albumId to set
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
    * @param artist the artist to set
    */

    
    public String getTheArtistsString(){
        return theArtistsString;
    }
    

    public void setPerson(Person person) {
        this.person = person;
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
     * @param thePersons the thePersons to set
     */
    public void addPerson(Person p) {
        if(!thePersons.contains(p))
            thePersons.add(p);
    }
}
