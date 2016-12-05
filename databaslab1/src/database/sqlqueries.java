/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.SQLException;
import java.util.ArrayList;
import model.Media;
import model.Person;


/**
 *
 * @author Jakob
 */
public interface sqlqueries {
    public ArrayList<Media> searchAlbums(String query) throws SQLException;
    public void submitReview(float rating,String comment,Media album) throws SQLException;
    public void addAlbum(Media album) throws SQLException;
    public void addArtist(Person artist) throws SQLException;
    public ArrayList<Person> getAllArtists() throws SQLException;
    
}
