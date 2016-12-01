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
    
    private String nickName;
    private ArrayList<MusicAlbum> theMusicAlbums;
    
    public Artist(){
        super();
        nickName = null;
    }
    public Artist(String SSN, String firstName, String lastName, String eMail, String phoneNumber, String nickName) {
        super(SSN, firstName, lastName, eMail, phoneNumber);
        this.nickName=nickName;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
    
    
    
    
       

