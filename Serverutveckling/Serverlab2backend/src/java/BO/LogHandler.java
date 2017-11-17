/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Post;
import java.util.ArrayList;
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

    private final static String PERSISTENCE_NAME = "Serverlab2backendPU";

    public LogHandler() {
    }

    public List<Post> getPostsByUser(String user) {

        EntityManager em;

        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            List<Post> list = (List<Post>) em.createQuery(
                    "SELECT p FROM Post p WHERE p.user.username LIKE :user")
                    .setParameter("user", user).getResultList();

            return list;
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }

    }
}
