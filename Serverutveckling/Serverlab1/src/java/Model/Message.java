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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jakob
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Column(name="sender")
    private String sender;
    @Column(name="receiver")
    private String receiver;
    @Column(name="topic")
    private String topic;
    @Column(name="message")
    private String message;
    @Column(name="date")
    private String date;
    @ManyToOne(targetEntity=User.class)
    private Collection user;
    
    
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
   
    
    public Message(String sender, String receiver, String topic,String message, Date date){
        this.topic = topic;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date.toString();
    }
    
    public Message(){
        
    }     
   
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }  


    /**
     * @return the user
     */
    
    public Collection getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Collection user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
}