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

    private final static String PERSISTENCE_NAME = "Serverlab2backendPU";

    public PostHandler() {
    }

    public Boolean createNewPost(String newPost, String user) throws Exception {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User tempUser = new User();
            try {
                tempUser = (User) em.createQuery(
                        "SELECT u FROM User u WHERE u.username LIKE :username")
                        .setParameter("username", user)
                        .getSingleResult();
            } catch (NoResultException e) {
                throw(e);
            } catch (Exception e) {
                throw new Exception("wtf excccc: " + e.toString());
            }

            Post postToInsert = new Post(newPost, tempUser);
            em.persist(postToInsert);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch(NoResultException e){
            throw(e);
        }catch (Exception e) {
            throw new Exception("wtf exceptionerito" + e.toString());
            //return false;
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }
}
