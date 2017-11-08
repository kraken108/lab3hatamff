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
    public MessageBean() {
        currentMessage = null;

        messages = new ArrayList<>();
        messages.add(new Message("Jubbe", "Michael", "topic 1", "Hej hej här är ett privat meddelande", new Date()));
        messages.add(new Message("Jubbe", "Michael", "topic 2", "Hej hej här är ett privat meddelande2", new Date()));
        messages.add(new Message("Jubbe", "Michael", "topic 3", "Hej hej här är ett privat meddelande3", new Date()));
        messages.add(new Message("Jubbe", "Michael", "topic 4", "Hej hej här är ett privat meddelande4", new Date()));
    }

    public String loadReadMessage(Message message) {
        currentMessage = message;
        return "readmessage.xhtml";
    }
    
    public String loadSendMessage(String receiver){
        this.receiver = receiver;
        return "sendmessage.xhtml";
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

}
