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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */
@Entity
@Table(name = "message")
@XmlRootElement
public class Message implements Serializable {
    
    @Column(name="topic")
    private String topic;
    
    @Column(name="message")
    private String message;
    
    @Column(name="date")
    private String date;
    
    @ManyToOne
    private User sender;
    
    @ManyToOne
    private User receiver;
    
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
   
    
    public Message(User receiver, User sender, String topic,String message, Date date){
        this.topic = topic;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date.toString();
    }
    
    public Message(){
        
    }     
   
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
    
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
}