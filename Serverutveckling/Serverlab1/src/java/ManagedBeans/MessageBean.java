/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.*;
import Model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public MessageBean() {
        currentMessage = null;
        //statusMessage = "";

        messages = new ArrayList<>();
        messages.add(new Message("Jubbe", "Michael", "topic 1", "Hej hej här är ett privat meddelande", new Date().toString()));
        messages.add(new Message("Jubbe", "Michael", "topic 2", "Hej hej här är ett privat meddelande2", new Date().toString()));
        messages.add(new Message("Jubbe", "Michael", "topic 3", "Hej hej här är ett privat meddelande3", new Date().toString()));
        messages.add(new Message("Jubbe", "Michael", "topic 4", "Hej hej här är ett privat meddelande4", new Date().toString()));
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

    public String loadSendMessage() {

        return "sendmessage?faces-redirect=true&receiver=" + receiver;
    }

    public String getSenderById(int id) {
        //TODO: get message by id and set currentMessage to it instead of one of these hardcoded ones.
        for (Message m : messages) {
            if (m.getId() == id) {
                currentMessage = m;
            }
        }
        return currentMessage.getSender();
    }


    public List<Message> getMessages() {
        //TODO: get messages from messagehandler where receiver is logged in user
        // Instead of the hardcoded message list.
        MessageHandler ms = new MessageHandler();
        ms.getMessagesByReceiver(userBean.getUsername());
        
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

    public String sendMessage() {
        MessageHandler mh = new MessageHandler();
        if(mh.sendMessage(receiver,userBean.getUsername(), sendTopic, sendMessage, new Date())){
            statusMessage = "Success!";
            return "sendmessage.xhtml";
        }else{
            statusMessage = "Something went wrong :(";
            return "sendmessage.xhtml";
        }
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    
    
}
