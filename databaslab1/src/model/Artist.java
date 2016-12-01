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
public class Artist extends Person{
    
    private String name;
    private ArrayList<MusicAlbum> theMusicAlbums;
    
    public Artist(){
        super();
        this.name = null;
    }
    public Artist(String name){
        super();
        this.name = name;
    }
    public Artist(String SSN, String firstName, String lastName, String eMail, String phoneNumber, String nickName) {
        super(SSN, firstName, lastName, eMail, phoneNumber);
        this.name=nickName;
    }

    /**
     * @return the nickName
     */
    public String getName() {
        return name;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
    
    
    
    
       

