/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

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
public class PostBeanTest {
    
    public PostBeanTest() {
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
     * Test of setUserBean method, of class PostBean.
     */
    @Test
    public void testSetUserBean() {
        System.out.println("setUserBean");
        UserBean userBean = null;
        PostBean instance = new PostBean();
        instance.setUserBean(userBean);
    }

    /**
     * Test of getStatusMessage method, of class PostBean.
     */
    @Test
    public void testGetStatusMessage() {
        System.out.println("getStatusMessage");
        PostBean instance = new PostBean();
        instance.setStatusMessage("Test");
        String expResult = "Test";
        String result = instance.getStatusMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatusMessage method, of class PostBean.
     */
    @Test
    public void testSetStatusMessage() {
        System.out.println("setStatusMessage");
        String statusMessage = "";
        PostBean instance = new PostBean();
        instance.setStatusMessage(statusMessage);
    }

    /**
     * Test of getNewPost method, of class PostBean.
     */
    @Test
    public void testGetNewPost() {
        System.out.println("getNewPost");
        PostBean instance = new PostBean();
        instance.setNewPost("Test");
        String expResult = "Test";
        String result = instance.getNewPost();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNewPost method, of class PostBean.
     */
    @Test
    public void testSetNewPost() {
        System.out.println("setNewPost");
        String newPost = "";
        PostBean instance = new PostBean();
        instance.setNewPost(newPost);
    }

    /**
     * Test of resetStatusMessage method, of class PostBean.
     */
    @Test
    public void testResetStatusMessage() {
        System.out.println("resetStatusMessage");
        PostBean instance = new PostBean();
        instance.resetStatusMessage();
    }

    /**
     * Test of createPost method, of class PostBean.
     */
    @Test(expected=NullPointerException.class)
    public void testCreatePost() throws Exception {
        System.out.println("createPost");
        PostBean instance = new PostBean();
        String expResult = "";
        String result = instance.createPost();
        assertEquals(expResult, result);
    }
    
}
