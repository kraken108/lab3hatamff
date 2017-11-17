/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.Message;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
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
        String receiver = "Jubbe";
        MessageHandler instance = new MessageHandler();
        String expResult = "Testmeddelande";
        List<Message> result = instance.getMessagesByReceiver(receiver);
        assertEquals(expResult, result.get(0).getMessage());
    }

    /**
     * Test of sendMessage method, of class MessageHandler.
     */
    @Test(expected = NoResultException.class)
    public void testSendMessage() throws Exception {
        System.out.println("sendMessage");
        String receiver = "";
        String sender = "";
        String topic = "";
        String text = "";
        Date date = null;
        MessageHandler instance = new MessageHandler();
        instance.sendMessage(receiver, sender, topic, text, date);
    }

    @Test
    public void testSendMessage2() throws Exception {
        System.out.println("sendMessage");
        String receiver = "DummyUser";
        String sender = "DummyUser";
        String topic = "Test";
        String text = "Test";
        Date date = new Date();
        MessageHandler instance = new MessageHandler();
        Boolean expResult = true;
        Boolean result = instance.sendMessage(receiver, sender, topic, text, date);
        assertEquals(expResult, result);
    }

}
