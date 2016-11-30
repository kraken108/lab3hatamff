/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jakob
 */
public class MusicAlbum {
    
    private int albumId;
    private String title;
    private String publishDate;
    private String genre;
    private float rating;
    
    public MusicAlbum(){
        this.albumId=0;
        this.title=null;
        this.publishDate=null;
        this.genre=null;
        this.rating=0;
    }
    public MusicAlbum(int albumId, String title, String publishDate, String genre, float rating){
        this.albumId=albumId;
        this.title=title;
        this.publishDate=publishDate;
        this.genre=genre;
        this.rating=rating;
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
    
    @Override
    public String toString(){
        String tmp = "Album: " + getTitle();
        return tmp;
    }
}
