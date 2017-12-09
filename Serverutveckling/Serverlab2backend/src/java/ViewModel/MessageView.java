/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import microservices.*;

/**
 *
 * @author Jakob
 */
public class MessageView {
    private String date;
    private String topic;
    private String message;
    private UserView sender;
    private UserView receiver;
    private Long id;
    
    
    public MessageView(){
        
    }
    
    public MessageView(String date, String topic, String message, UserView sender, UserView receiver,Long id) {
        this.date = date;
        this.topic = topic;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
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

    public UserView getSender() {
        return sender;
    }

    public void setSender(UserView sender) {
        this.sender = sender;
    }

    public UserView getReceiver() {
        return receiver;
    }

    public void setReceiver(UserView receiver) {
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
