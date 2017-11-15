/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Post;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
public class LogHandler {
    
    
    public LogHandler(){}
    
    public List<Post> getPostsByUser(String user){
        //TODO:
        //Get all posts made by the user and return it in a list
        
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
        
       /* Query q = em.createQuery(
                "SELECT p FROM Post p WHERE p.user.username LIKE :user")
                .setParameter("user", user);*/
        
        return (List<Post>) em.createQuery(
                "SELECT p FROM Post p WHERE p.user.username LIKE :user")
                .setParameter("user", user).getResultList();
        //return (List<Message>) q.getResultList();
        //return null;
    }
}