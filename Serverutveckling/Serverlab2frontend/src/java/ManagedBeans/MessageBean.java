/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Rest.MessageRestClient;
import Rest.PostRestClient;
import ViewModel.Message;
import ViewModel.Post;
import ViewModel.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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

    private List<Message> loadedMessages;
    
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

    public Boolean loadMessageById(int id) {
        for(Message m : loadedMessages){
            if(m.getId() == id){
                currentMessage = m;
                return true;
            }
        }
        currentMessage = null;
        return false;
    }

    public List<ViewModel.Message> getMessages() {
        MessageRestClient client = new MessageRestClient();

        GenericType<List<Message>> gType = new GenericType<List<Message>>() {
        };
        loadedMessages = (List<Message>) client.getMessagesByUser_JSON(gType, userBean.getUsername());
        Collections.sort(loadedMessages);
        Collections.reverse(loadedMessages);
        return loadedMessages;
    }

    public ViewModel.Message getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(ViewModel.Message currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String sendMessage() {
        User senderUser = new User();
        senderUser.setUsername(userBean.getUsername());
        
        User receiverUser = new User();
        receiverUser.setUsername(receiver);
        
        
        MessageRestClient client = new MessageRestClient();
        Message m = new Message();
        m.setMessage(sendMessage);
        m.setReceiver(receiverUser);
        m.setSender(senderUser);
        m.setTopic(sendTopic);

        Response response = client.createNewMessage_JSON(m);
        if (response.getStatusInfo().toString().equals("Created")) {
            sendTopic = "";
            sendMessage = "";
            statusMessage = "Success!";
            return "sendmessage.xhtml";
        } else {
            sendTopic = "";
            sendMessage = "";
            statusMessage = response.getStatusInfo().toString();
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
