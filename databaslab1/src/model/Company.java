/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author micke1
 */
public class Company {
    
    private ArrayList<Artist> theArtists;
    private ArrayList<Director> theDirectors;
    private ArrayList<Film> theFilms;
    private ArrayList<MusicAlbum> theMusicAlbums;
    private ArrayList<Review> theReviews;    
    
    public Company(){
        theArtists = new ArrayList<Artist>();
        theDirectors = new ArrayList<Director>();
        theFilms = new ArrayList<Film>();
        theMusicAlbums = new ArrayList<MusicAlbum>();
        theReviews = new ArrayList<Review>();
    }
    
    public void addArtist(Artist a){
        if(!theArtists.contains(a))
        theArtists.add(a);
    }
    
    public void removeArtist(Artist a){
        if(theArtists.contains(a))
            theArtists.remove(a);
    }
    
    public ArrayList<Artist> getTheArtists(){
        return (ArrayList<Artist>) theArtists.clone();
    }
    
    public void addDirector(Director d){
        if(!theDirectors.contains(d))
            theDirectors.add(d);
    }
    
    public void removeDirector(Director d){
        if(theDirectors.contains(d))
            theDirectors.remove(d);
    }
    
    public ArrayList<Director> getTheDirectors(){
        return (ArrayList<Director>) theDirectors.clone();
    }
    
    public void addFilm(Film f){
        if(!theFilms.contains(f))
            theFilms.add(f);
    }
    
    public void removeFilm(Film f){
        if(theFilms.contains(f))
            theFilms.remove(f);
    }
    
    public ArrayList<Film> getTheFilms(){
        return (ArrayList<Film>) theFilms.clone();
    }
    
    public void addMusicAlbum(MusicAlbum m){
        if(!theMusicAlbums.contains(m))
            theMusicAlbums.add(m);
    }
    
    public void removeMusicAlbum(MusicAlbum m){
        if(theMusicAlbums.contains(m))
            theMusicAlbums.remove(m);
    }
    
    public ArrayList<MusicAlbum> getTheMusicAlbums(){
        return (ArrayList<MusicAlbum>) theMusicAlbums.clone();
    }
    
    public void addReview(Review r){
        if(!theReviews.contains(r))
            theReviews.add(r);
    }
    
    public void removeReview(Review r){
        if(theReviews.contains(r))
            theReviews.remove(r);
    }
    
    public ArrayList<Review> getTheReviews(){
        return (ArrayList<Review>) theReviews.clone();
    }
    
    
    
    
    
    
    
    
    
    
}
