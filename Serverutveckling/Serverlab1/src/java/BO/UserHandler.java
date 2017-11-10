/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jakob
 */
public class UserHandler {
    
    private final EntityManager em;
    private final EntityManagerFactory emf;
    
    public UserHandler(){
    
        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
    }
        
    public Boolean login(String username, String password){
        //TODO:
        //Check database and return true if user and pw is correct
        
        return true;
    }
    
    public void insertUser(String username){
        Query q = em.createQuery("");
    }
    
    /*
    public List findWithName(String name) {
        return em.createQuery(
        "SELECT DISTINCT user FROM User user WHERE user.username LIKE '" + name + "'");
        .setParameter("custName", name)
        .setMaxResults(10)
        .getSingleResult()).longValue();
        return null
    }
    */
    
    
    public boolean createUser(String username, String password) throws Exception{
            
            
            em.getTransaction().begin();
            User userToInsert= new User(username, password);
            em.persist(userToInsert);
            em.flush();
            em.getTransaction().commit();
            em.close();
            emf.close();       
            
            return false;
    }
    
    
    public List<User> getAllUsers(){
        //TODO:
        //Return a list of all users
        
        return null;
    }
}
