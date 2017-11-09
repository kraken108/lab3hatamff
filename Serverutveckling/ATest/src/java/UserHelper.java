

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jakob
 */
public class UserHelper {

    Session session = null;

    public UserHelper() {
        System.out.println("meep");
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void addNewUser(String name) throws Exception {
        System.out.println("meep meep");
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("ATestPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User test = new User(name);
        em.persist(test);
        em.flush();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
