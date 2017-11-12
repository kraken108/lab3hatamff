/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Message;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michael
 */
public class MessageHandler {
    
    public MessageHandler(){
        
    }
    
    public Boolean createMessage(){
        
     return false;   
    }   
    
    public List<Message> getMessagesByReceiver(String receiver){
        //TODO:
        //Return a list of all messages where the receiver is named receiver
        
        return null;
    }
    
    public Message getMessageById(int id){
        //TODO get message from database with the stated id and return it
        
        return null;
    }
    
    public Boolean sendMessage(String receiver, String sender, String topic, String text,Date date){
        //TODO
        //Create new message in database and return true if successful
        return true;
    }
}
