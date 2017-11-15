/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */

public class MessageHandler {
    
    private final EntityManager em;
    private final EntityManagerFactory emf;
    
    public MessageHandler(){
      
        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
    }
    
    public Boolean createMessage(){
        
        return false;   
    }   
    
    public ArrayList<Message> getMessagesByReceiver(String receiver){
        /*
        //TODO:
        //Return a list of all messages where the receiver is named receiver
        //ArrayList<Message> messages;
        //messages = new ArrayList();
        
    Query q = em.createQuery(
        "SELECT m FROM Message m WHERE m.receiver LIKE :receiver");
    return (ArrayList<Message>)  q.getResultList();               
        
        */
        return null;
        
    }
    
    public Message getMessageById(long id){
        /*
        //TODO get message from database with the stated id and return it
        //Message message = null;
        
        Message tempMessage = new Message();
        
        try{
            tempMessage = (Message) em.createQuery(
            "SELECT m FROM Message m WHERE m.id LIKE :id")
            .setParameter("id", tempMessage)
            .setMaxResults(1)
            .getSingleResult();
        }catch(NoResultException e){
            return null;
        }    
        return tempMessage;
        */
        return null;

    }
    
    public Boolean sendMessage(String receiver, String sender, String topic, String text,Date date){
        //TODO
        //Create new message in database and return true if successful
        
        try{
            em.getTransaction().begin();
            Message messageToInsert = new Message(receiver, sender, topic, text, date);
            em.persist(messageToInsert);
            em.flush();
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }catch(Exception e){
            return false;
        }       
        //return null;
    }
}