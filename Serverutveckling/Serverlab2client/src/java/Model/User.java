/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */

public class User implements Serializable {
    private String username;
    private Long id;
    private String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username){
        this.username=username;
    }
    
    public User(){
        
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }    

    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    /*
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
