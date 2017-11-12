/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.User;
import antlr.collections.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jakob
 */
public class UserHandler {
    
    private final EntityManager em;
    private final EntityManagerFactory emf;
    private Formatter formatter;
    
    public UserHandler(){
    
        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();

    }
        
    public Boolean login(String username, String password){
        //TODO:
        //Check database and return true if user and pw is correct
        //int counter=0;
        String testname = username;
        String testpass = password;
        User tempUser = new User();
        
        try{
            tempUser = (User) em.createQuery(
            "SELECT u FROM User u WHERE u.username LIKE :username AND u.password LIKE :password")
            .setParameter("username", testname)
            .setParameter("password", testpass)
            .setMaxResults(1)
            .getSingleResult();
        }catch(NoResultException e){
                testname = "";
                testpass = "";
        }        
        
        if((testname.equals(username)) && (testpass.equals(password)))
            return true;        
        else
            return false;
    }   
 
   
    public boolean createUser(String username, String password) throws Exception{
            
            
        String testname = username;
        User tempUser = new User();
        try{
             tempUser = (User) em.createQuery(
            "SELECT u FROM User u WHERE u.username LIKE :username")
            .setParameter("username", testname)
            .setMaxResults(1)
            .getSingleResult(); 
        }catch(NoResultException e){
            testname ="";
        }
        tempUser.setUsername(testname);
        
      
       if(!(tempUser.getUsername().equals(username))){
     
            em.getTransaction().begin();
            User userToInsert = new User(username, password);
            em.persist(userToInsert);
            em.flush();
            em.getTransaction().commit();
            em.close();
            emf.close();   
            return true;
        
       } 
        return false;        
    }   
}
    



