/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class MessageBean {

    private List<Message> messages;
    private Message currentMessage;
    private String receiver;
    private String sendTopic;
    private String sendMessage;
    private String statusMessage;
    
    public MessageBean() {
        currentMessage = null;
        statusMessage = "";
        
        messages = new ArrayList<>();
        messages.add(new Message("Jubbe", "Michael", "topic 1", "Hej hej här är ett privat meddelande", new Date(),0));
        messages.add(new Message("Jubbe", "Michael", "topic 2", "Hej hej här är ett privat meddelande2", new Date(),1));
        messages.add(new Message("Jubbe", "Michael", "topic 3", "Hej hej här är ett privat meddelande3", new Date(),2));
        messages.add(new Message("Jubbe", "Michael", "topic 4", "Hej hej här är ett privat meddelande4", new Date(),3));
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    
    public String getSendTopic() {
        return sendTopic;
    }

    public void setSendTopic(String sendTopic) {
        this.sendTopic = sendTopic;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }
    
    
    
    public String loadReadMessage(Message message) {
        currentMessage = message;
        return "readmessage.xhtml";
    }
    
    public String loadSendMessage(String receiver){
        this.receiver = receiver;
        return "sendmessage.xhtml";
    }
    
    
    public String getSenderById(int id){
        for(Message m : messages){
            if(m.getId() == id){
                currentMessage = m;
            }
        }
        return currentMessage.getSender();
    }
    
    
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Message getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(Message currentMessage) {
        this.currentMessage = currentMessage;
    }
    
    public String sendMessage(){
        statusMessage = "Success!";
        return "sendmessage.xhtml";
    }

}
