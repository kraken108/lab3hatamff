/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.DatasetValues;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class DatasetHandler {
    
    private final static String PERSISTENCE_NAME = "Serverlab2backendPU";
    
    public DatasetHandler(){        
    }
    
    public String createNewDataset(int x, int y) throws Exception{
        
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            DatasetValues valuesToInsert = new DatasetValues(x,y);
            em.persist(valuesToInsert);
            em.flush();
            em.getTransaction().commit();
            return "Successful!";        
        }catch(Exception e){
            return "Unsuccessful creating dataset";
        }finally{
            if(em != null){
                em.close();
            }
            emf.close();
        }        
    }
    
    
}
