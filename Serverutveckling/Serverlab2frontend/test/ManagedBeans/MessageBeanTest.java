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
     * Test of getMessages method, of class MessageBean.
     */
    @Test(expected=NullPointerException.class)
    public void testGetMessages() {
        System.out.println("getMessages");
        MessageBean instance = new MessageBean();
        String expResult = "meddelande";
        List<Message> result = instance.getMessages();
        assertEquals(expResult, result.get(0).getMessage());
    }


}
