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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Jakob
 */
public class PostHandler {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public PostHandler() {

        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
    }

    public Boolean createNewPost(String newPost, String user) throws Exception {
        //TODO:
        //Create new post add to database, return some response if successful or not
        try {
            em.getTransaction().begin();
            
            User tempUser = new User();
            try {
                tempUser = (User) em.createQuery(
                        "SELECT u FROM User u WHERE u.username LIKE :username")
                        .setParameter("username", user)
                        .getSingleResult();
            } catch (NoResultException e) {
                throw new Exception("couldnt find user");
            }catch(Exception e){
                throw new Exception("wtf excccc: " + e.toString());
            }
            
            Post postToInsert = new Post(newPost, tempUser);
            em.persist(postToInsert);

            em.flush();
            em.getTransaction().commit();
            //em.close();
            //emf.close();
            return true;
        } catch (Exception e) {
            throw new Exception("wtf exceptionerito" + e.toString());
            //return false;
        }
    }
}
