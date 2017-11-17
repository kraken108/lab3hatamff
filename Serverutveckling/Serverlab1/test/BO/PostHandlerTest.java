/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

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
public class PostHandlerTest {
    
    public PostHandlerTest() {
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
     * Test of createNewPost method, of class PostHandler.
     */
    @Test(expected=Exception.class)
    public void testCreateNewPost() throws Exception {
        System.out.println("createNewPost");
        String newPost = "";
        String user = "";
        PostHandler instance = new PostHandler();
        Boolean result = instance.createNewPost(newPost, user);
    }
    
}
