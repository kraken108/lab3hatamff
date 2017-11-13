/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jakob
 */
public class Message {
    private String sender;
    private String receiver;
    private String topic;
    private String message;
    private String date;
    private long id;
    
    public Message(String sender, String receiver, String topic,String message,String date){
        this.topic = topic;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
