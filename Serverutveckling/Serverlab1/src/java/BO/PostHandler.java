/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Post;
import Model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jakob
 */
public class PostHandler {
    
    private final EntityManager em;
    private final EntityManagerFactory emf;
    
    public PostHandler(){
    
        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
    }
    
    public Boolean createNewPost(String newPost, User user){
        //TODO:
        //Create new post add to database, return some response if successful or not
        try{
            em.getTransaction().begin();
            Post postToInsert = new Post(newPost, user);
            em.persist(postToInsert);
            em.flush();
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}