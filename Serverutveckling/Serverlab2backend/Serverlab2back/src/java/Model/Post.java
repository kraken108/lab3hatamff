/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */
@Entity
@Table(name = "post")
@XmlRootElement
public class Post implements Serializable {
    
    @Column(name="message")
    private String message;
    
    @XmlElement(name="date")
    @Column(name="date")
    private String date;
    
    @ManyToOne
    private User user;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Post() {
        this.date = new Date().toString();
    }
    
    public String getMessage() {
        return message;
    }
    
    public Post(String message,Date date, User user){
        this.message = message;
        this.date = date.toString();
        this.user = user;
    }
    
    public Post(String message, User user){
        this.date = new Date().toString();
        this.user = user;
        this.message = message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}