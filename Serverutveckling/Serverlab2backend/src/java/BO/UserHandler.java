/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.*;
import Model.User;
import java.util.ArrayList;
import java.util.List;
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

    private final static String PERSISTENCE_NAME = "Serverlab2backendPU";

    public UserHandler() {
    }

    public Boolean login(String username, String password) {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            Query q = em.createQuery(
                    "SELECT u FROM User u WHERE u.username LIKE :username AND u.password LIKE :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            q.setMaxResults(1);
            q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    public List<User> getAllUsers() {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            Query q = em.createQuery("SELECT u FROM User u");
            List<User> list = (List<User>) q.getResultList();

            return list;

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }

    }

    public Boolean checkIfAlreadyExists(String username) {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            em.createQuery(
                    "SELECT u FROM User u WHERE u.username LIKE :username")
                    .setParameter("username", username)
                    .setMaxResults(1)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }

    public List<Image> getUserImages(String username) {

        EntityManager em;

        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            List<Image> list = (List<Image>) em.createQuery(
                    "SELECT i FROM Image i WHERE i.user.username LIKE :user")
                    .setParameter("user", username).getResultList();

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

    public Boolean saveImage(String imageData, String username) throws Exception {
        if (!checkIfAlreadyExists(username)) {
            return false;
        }

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
                        .setParameter("username", username)
                        .getSingleResult();
            } catch (NoResultException e) {
                throw (e);
            } catch (Exception e) {
                throw new Exception("wtf excccc: " + e.toString());
            }

            
            Image imageToInsert = new Image(imageData.getBytes(), tempUser);
            em.persist(imageToInsert);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (NoResultException e) {
            throw (e);
        } catch (Exception e) {
            throw new Exception("wtf exceptionerito" + e.toString());
            //return false;
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }

    public String createUser(String username, String password) {

        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            if (!checkIfAlreadyExists(username)) {

                em.getTransaction().begin();
                User userToInsert = new User(username, password);
                em.persist(userToInsert);
                em.flush();
                em.getTransaction().commit();
                return "Successfully created account!";
            } else {
                return "Username already exists!";
            }
        } catch (Exception e) {
            return "A server error occurred";
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }

    }
}
