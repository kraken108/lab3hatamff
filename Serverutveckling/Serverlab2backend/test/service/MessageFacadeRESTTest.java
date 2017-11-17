/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Model.Message;
import Model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jakob
 */
public class MessageFacadeRESTTest {
    
    public MessageFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createNewMessage method, of class MessageFacadeREST.
     */
    @Test
    public void testCreateNewMessage() {
        System.out.println("createNewMessage");
        Message entity = new Model.Message();
        User u = new User();
        u.setUsername("DummyUser");
        entity.setReceiver(u);
        entity.setSender(u);
        entity.setMessage("Testmessage");
        entity.setTopic("Testtopic");
        MessageFacadeREST instance = new MessageFacadeREST();
        String expResult = "Created";
        Response result = instance.createNewMessage(entity);
        assertEquals(expResult, result.getStatusInfo().toString());
    }
   
    /**
     * Test of getMessagesByUser method, of class MessageFacadeREST.
     */
    @Test
    public void testGetMessagesByUser() {
        System.out.println("getMessagesByUser");
        String user = "Jubbe";
        MessageFacadeREST instance = new MessageFacadeREST();
        String expResult = "Testmeddelande";
        List<Message> result = instance.getMessagesByUser(user);
        assertEquals(expResult, result.get(0).getMessage());
    }
    
    
}
