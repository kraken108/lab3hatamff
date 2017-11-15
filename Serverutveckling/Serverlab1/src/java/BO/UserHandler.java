/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.User;
import antlr.collections.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

/**
 *
 * @author Jakob
 */
public class UserHandler {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public UserHandler() {

        emf = Persistence.createEntityManagerFactory("Serverlab1PU");
        em = emf.createEntityManager();
    }

    public Boolean login(String username, String password) {

        String testname = username;
        String testpass = password;

        try {
            Query q = em.createQuery(
                    "SELECT u FROM User u WHERE u.username LIKE :username AND u.password LIKE :password");
            q.setParameter("username", testname);
            q.setParameter("password", testpass);
            q.setMaxResults(1);
            q.getSingleResult();
        } catch (NoResultException e) {
            testname = "";
            testpass = "";
        }

        if ((testname.equals(username)) && (testpass.equals(password))) {
            return true;
        } else {
            return false;

        }
    }

    /*
    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList();

        try {
            Query q = em.createQuery(
                    "SELECT * FROM User :usersList");
            q.setParameter("usersList", users);
//            users = (ArrayList<User>) q.getResultList();

        } catch (NoResultException e) {
            return null;
        }
        return (ArrayList<User>) users.clone();

    }*/

 
    
    public java.util.List getAllUsers(){
        
        try{
            Query q = em.createQuery("SELECT u FROM User u");
            return q.getResultList();
            
        }catch(NoResultException e){
            return null;
        }
    } 
    
    public User checkIfAlreadyExists(String username){
        
        
        String testname = username;
        User tempUser = new User();
        try {
            tempUser = (User) em.createQuery(
                    "SELECT u FROM User u WHERE u.username LIKE :username")
                    .setParameter("username", testname)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            testname = "";
            return null;
        }
        tempUser.setUsername(testname);

        return tempUser;
    }

    public String createUser(String username, String password) {

        User tempUser = checkIfAlreadyExists(username);

        if (tempUser == null) {
            try {
                em.getTransaction().begin();
                User userToInsert = new User(username, password);
                em.persist(userToInsert);
                em.flush();
                em.getTransaction().commit();
                em.close();
                emf.close();
                return "Successfully created account!";
            }catch(Exception e){
                return "fack error on createUser server side";
            }

        }else{
            return "Username already exists!";
        }
              
    }
}