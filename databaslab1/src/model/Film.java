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
public class Film {
    
    private String filmId;
    private String title;
    private String publishDate;
    private String genre;
    private String rating;
    private ArrayList<Director> theDirectors;
    
    public Film(String filmId, String title, String publishDate, String genre, String rating){
        this.filmId=filmId;
        this.title=title;
        this.publishDate=publishDate;
        this.genre=genre;
        this.rating=rating;
    }

    /**
     * @return the filmId
     */
    public String getFilmId() {
        return filmId;
    }

    /**
     * @param filmId the filmId to set
     */
    public void setFilmId(String filmId) {
        this.filmId = filmId;
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
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }    
}
