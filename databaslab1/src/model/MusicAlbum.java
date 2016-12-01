/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class MusicAlbum {
    
    private int albumId;
    private String title;
    private String publishDate;
    private String genre;
    private ArrayList<Artist> theArtists;
    private float rating;
    private Artist artist;

    
    public MusicAlbum(){
        theArtists = new ArrayList<>();
        this.albumId=0;
        this.title=null;
        this.publishDate=null;
        this.genre=null;
        this.rating=0;
    }

    

    public MusicAlbum(String title, String publishDate, String genre){
        theArtists = new ArrayList<>();
        this.title=title;
        this.publishDate=publishDate;
        this.genre=genre;
        this.rating=rating;
        this.rating=0;

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
        this.rating = rating;
    }   
   
    /**
    * @return the artist
    */
    public Artist getArtist() {
        return artist;
    }

    /**
    * @param artist the artist to set
    */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    
    public void addArtist(Artist a){
        
        if(!theArtists.contains(a)){
            theArtists.add(a);     
        }
       
    }
    
    public ArrayList<Artist> getArtists(){
        return (ArrayList<Artist>) theArtists.clone();
    }
    

    @Override
    public String toString(){
        String artists = "";
        for(Artist a : getArtists()){
            if(a!=null)
                artists += a.getName() + ", ";
        }
        String tmp = "Album: " + getTitle() + "\t|\tRelease date: " 
                    + getPublishDate() + "\t|\tGenre: " + getGenre() 
                + "\t|\tRating: " + getRating() + "\t|\tArtists: " + artists;
        return tmp;
    }



}
