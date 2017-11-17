/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */
@XmlRootElement
public class Post implements Comparable<Post>  {
    private String message;
    
    private User user;
    
    private String date;
    
    private Long id;
    
    public Post(){
        
    }
    
    public Post(String date,String message, User user, Long id) {
        this.message = message;
        this.user = user;
        this.date = date;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Post o) {
        return id.compareTo(o.id);
    }
    
    
}
