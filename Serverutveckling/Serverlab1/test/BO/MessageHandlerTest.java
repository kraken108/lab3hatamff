/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import ViewModel.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class MessageHandlerTest {
    
    public MessageHandlerTest() {
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
     * Test of getMessagesByReceiver method, of class MessageHandler.
     */
    @Test
    public void testGetMessagesByReceiver() {
        System.out.println("getMessagesByReceiver");
        String receiver = "";
        MessageHandler instance = new MessageHandler();
        List<Message> expResult = new ArrayList<>();
        List<Message> result = instance.getMessagesByReceiver(receiver);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageById method, of class MessageHandler.
     */
    @Test
    public void testGetMessageById() {
        System.out.println("getMessageById");
        long id = 0;
        MessageHandler instance = new MessageHandler();
        Message expResult = null;
        Message result = instance.getMessageById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMessage method, of class MessageHandler.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String receiver = "";
        String sender = "";
        String topic = "testTopic";
        String text = "testText";
        Date date = new Date();
        MessageHandler instance = new MessageHandler();
        Boolean expResult = false;
        Boolean result = instance.sendMessage(receiver, sender, topic, text, date);
        assertEquals(expResult, result);
    }
    
}
