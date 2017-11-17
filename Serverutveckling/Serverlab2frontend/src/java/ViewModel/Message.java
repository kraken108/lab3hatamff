/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */
@XmlRootElement
public class Message implements Comparable<Message>{
    private String date;
    private String topic;
    private String message;
    private User sender;
    private User receiver;
    private Long id;
    
    
    public Message(){
        
    }
    
    public Message(String date, String topic, String message, User sender, User receiver,Long id) {
        this.date = date;
        this.topic = topic;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public int compareTo(Message o) {
        return id.compareTo(o.id);
    }

    
    
}
