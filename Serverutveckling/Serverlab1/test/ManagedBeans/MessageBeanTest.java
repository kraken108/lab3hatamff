/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ViewModel.Message;
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
public class MessageBeanTest {
    
    public MessageBeanTest() {
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
     * Test of setUserBean method, of class MessageBean.
     */
    @Test
    public void testSetUserBean() {
        System.out.println("setUserBean");
        UserBean userBean = new UserBean();
        MessageBean instance = new MessageBean();
        instance.setUserBean(userBean);
    }

    /**
     * Test of getStatusMessage method, of class MessageBean.
     */
    @Test
    public void testGetStatusMessage() {
        System.out.println("getStatusMessage");
        MessageBean instance = new MessageBean();
        instance.setStatusMessage("TestMessage");
        String expResult = "TestMessage";
        String result = instance.getStatusMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatusMessage method, of class MessageBean.
     */
    @Test
    public void testSetStatusMessage() {
        System.out.println("setStatusMessage");
        String statusMessage = "Test";
        MessageBean instance = new MessageBean();
        instance.setStatusMessage(statusMessage);
    }

    /**
     * Test of getSendTopic method, of class MessageBean.
     */
    @Test
    public void testGetSendTopic() {
        System.out.println("getSendTopic");
        MessageBean instance = new MessageBean();
        instance.setSendTopic("TestTopic");
        String expResult = "TestTopic";
        String result = instance.getSendTopic();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSendTopic method, of class MessageBean.
     */
    @Test
    public void testSetSendTopic() {
        System.out.println("setSendTopic");
        String sendTopic = "Test";
        MessageBean instance = new MessageBean();
        instance.setSendTopic(sendTopic);
    }

    /**
     * Test of getSendMessage method, of class MessageBean.
     */
    @Test
    public void testGetSendMessage() {
        System.out.println("getSendMessage");
        MessageBean instance = new MessageBean();
        instance.setSendMessage("TestMessage");
        String expResult = "TestMessage";
        String result = instance.getSendMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSendMessage method, of class MessageBean.
     */
    @Test
    public void testSetSendMessage() {
        System.out.println("setSendMessage");
        String sendMessage = "Test";
        MessageBean instance = new MessageBean();
        instance.setSendMessage(sendMessage);
    }

    /**
     * Test of loadReadMessage method, of class MessageBean.
     */
    @Test
    public void testLoadReadMessage() {
        System.out.println("loadReadMessage");
        Message message = null;
        MessageBean instance = new MessageBean();
        String expResult = "readmessage.xhtml";
        String result = instance.loadReadMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadSendMessage method, of class MessageBean.
     */
    @Test
    public void testLoadSendMessage() {
        System.out.println("loadSendMessage");
        MessageBean instance = new MessageBean();
        instance.setReceiver("testReceiver");
        String expResult = "sendmessage?faces-redirect=true&receiver=" + instance.getReceiver();
        String result = instance.loadSendMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadMessageById method, of class MessageBean.
     */
    @Test
    public void testLoadMessageById() {
        System.out.println("loadMessageById");
        int id = 0;
        MessageBean instance = new MessageBean();
        Boolean expResult = false;
        Boolean result = instance.loadMessageById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessages method, of class MessageBean.
     */
    @Test(expected=NullPointerException.class)
    public void testGetMessages() {
        System.out.println("getMessages");
        MessageBean instance = new MessageBean();
        instance.getMessages();
    }

    /**
     * Test of getCurrentMessage method, of class MessageBean.
     */
    @Test
    public void testGetCurrentMessage() {
        System.out.println("getCurrentMessage");
        MessageBean instance = new MessageBean();
        Message expResult = null;
        Message result = instance.getCurrentMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentMessage method, of class MessageBean.
     */
    @Test
    public void testSetCurrentMessage() {
        System.out.println("setCurrentMessage");
        Message currentMessage = null;
        MessageBean instance = new MessageBean();
        instance.setCurrentMessage(currentMessage);
    }

    /**
     * Test of sendMessage method, of class MessageBean.
     */
    @Test(expected=NullPointerException.class)
    public void testSendMessage() {
        System.out.println("sendMessage");
        MessageBean instance = new MessageBean();
        instance.sendMessage();
    }

    /**
     * Test of getReceiver method, of class MessageBean.
     */
    @Test
    public void testGetReceiver() {
        System.out.println("getReceiver");
        MessageBean instance = new MessageBean();
        instance.setReceiver("TestReceiver");
        String expResult = "TestReceiver";
        String result = instance.getReceiver();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReceiver method, of class MessageBean.
     */
    @Test
    public void testSetReceiver() {
        System.out.println("setReceiver");
        String receiver = "";
        MessageBean instance = new MessageBean();
        instance.setReceiver(receiver);
    }
    
}
