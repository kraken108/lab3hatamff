/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.*;
import java.util.ArrayList;
import java.util.Collections;
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


    private ViewModel.Message currentMessage;
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

    public String loadReadMessage(ViewModel.Message message) {
        currentMessage = message;
        return "readmessage.xhtml";
    }

    public String loadSendMessage() {

        return "sendmessage?faces-redirect=true&receiver=" + receiver;
    }


    public Boolean loadMessageById(int id){
        MessageHandler mh = new MessageHandler();
        ViewModel.Message m = mh.getMessageById(id);
        if(m == null){
            currentMessage = null;
            return false;
        }else{
            currentMessage = m;
            return true;
        }
    }
    
    
    public List<ViewModel.Message> getMessages() {
        MessageHandler ms = new MessageHandler();
        List<ViewModel.Message> list = ms.getMessagesByReceiver(userBean.getUsername());
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }



    public ViewModel.Message getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(ViewModel.Message currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String sendMessage() {
        MessageHandler mh = new MessageHandler();
        if(mh.sendMessage(receiver,userBean.getUsername(), sendTopic, sendMessage, new Date())){
            sendTopic = "";
            sendMessage = "";
            statusMessage = "Success!";
            return "sendmessage.xhtml";
        }else{
            sendTopic = "";
            sendMessage = "";
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