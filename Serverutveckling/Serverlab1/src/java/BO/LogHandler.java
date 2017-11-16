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

    private final static String PERSISTENCE_NAME = "Serverlab1PU";

    public LogHandler() {
    }

    public List<ViewModel.Post> getPostsByUser(String user) {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        List<Post> list = (List<Post>) em.createQuery(
                "SELECT p FROM Post p WHERE p.user.username LIKE :user")
                .setParameter("user", user).getResultList();
        em.close();
        emf.close();
        
        
        List<ViewModel.Post> viewList = new ArrayList<>();
        for(Post p : list){
            viewList.add(new ViewModel.Post(p.getMessage(), 
                    p.getUser().getUsername(), p.getDate(),p.getId()));
        }
        return viewList;

    }
}
