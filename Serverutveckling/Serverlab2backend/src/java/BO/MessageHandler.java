/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Message;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
public class MessageHandler {

    private final static String PERSISTENCE_NAME = "Serverlab2backendPU";

    public MessageHandler() {

    }

    public Boolean createMessage() {

        return false;
    }

    public List<Message> getMessagesByReceiver(String receiver) {

        EntityManager em;
        EntityManagerFactory emf;

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            Query q = em.createQuery(
                    "SELECT m FROM Message m WHERE m.receiver.username LIKE :receiver")
                    .setParameter("receiver", receiver);

            List<Message> list = (List<Message>) q.getResultList();

            return list;

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

    public Message getMessageById(long id) {

        EntityManager em;
        EntityManagerFactory emf;

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        Message tempMessage;

        try {
            tempMessage = (Message) em.createQuery(
                    "SELECT m FROM Message m WHERE m.id LIKE :id")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();

            return tempMessage;
        } catch (NoResultException e) {
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

    public Boolean sendMessage(String receiver, String sender, String topic, String text, Date date) throws Exception {

        EntityManager em;
        EntityManagerFactory emf;

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User senderUser = getUserByName(sender);
            User receiverUser = getUserByName(receiver);
            
            if (senderUser == null || receiverUser == null) {
                throw new Exception("couldnt find users: " + sender + " " + receiver);
            }

            Message messageToInsert = new Message(receiverUser, senderUser, topic, text, date);

            em.persist(messageToInsert);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (NoResultException e) {
            throw(e);
        } catch (Exception e) {
            throw (e);
        } finally {
            if (em != null) {
                em.close();
            }

            emf.close();

        }
    }

    private User getUserByName(String username) {
        User tempUser;

        EntityManager em;
        EntityManagerFactory emf;

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();

        try {
            tempUser = (User) em.createQuery(
                    "SELECT u FROM User u WHERE u.username LIKE :username")
                    .setParameter("username", username)
                    .getSingleResult();
            return tempUser;
        } catch (NoResultException e) {
            throw (e);
        } catch (Exception e) {
            throw (e);
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
