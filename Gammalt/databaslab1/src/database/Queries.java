/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.util.ArrayList;
import model.*;
/**
 *
 * @author Jakob
 */
public interface Queries {
    public void addAlbum(Media media) throws Exception;
    public void addArtist(Person artist) throws Exception;
    public void submitReview(float rating,String comment,Media media) throws Exception;
    public ArrayList<Media> searchAlbums(String searchWord, String searchBy);
    public ArrayList<Person> getAllArtists();
    public void closeConnection();
}
