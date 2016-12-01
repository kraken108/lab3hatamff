/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.SQLException;
import java.util.ArrayList;
import model.MusicAlbum;


/**
 *
 * @author Jakob
 */
public interface sqlqueries {
    public ArrayList<MusicAlbum> searchAlbums(String query) throws SQLException;
    
}
