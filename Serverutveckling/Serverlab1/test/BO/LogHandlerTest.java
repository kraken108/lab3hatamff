/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import ViewModel.Post;
import java.util.ArrayList;
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
public class LogHandlerTest {

    public LogHandlerTest() {
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
     * Test of getPostsByUser method, of class LogHandler.
     */
    @Test
    public void testGetPostsByUser() {
        System.out.println("getPostsByUser");
        String user = "";
        LogHandler instance = new LogHandler();
        List<Post> expResult = new ArrayList<>();
        List<Post> result = instance.getPostsByUser(user);
        assertEquals(expResult, result);
    }

}
