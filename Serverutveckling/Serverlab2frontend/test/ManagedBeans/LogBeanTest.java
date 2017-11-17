/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ViewModel.Post;
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
public class LogBeanTest {
    
    public LogBeanTest() {
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
     * Test of getPostsByName method, of class LogBean.
     */
    @Test
    public void testGetPostsByName() throws Exception {
        System.out.println("getPostsByName");
        String username = "Jubbe";
        LogBean instance = new LogBean();
        String expResult = "Hej hej";
        List<Post> result = instance.getPostsByName(username);
        assertEquals(expResult, result.get(0).getMessage());
    }

    /**
     * Test of setUser method, of class LogBean.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String user = "";
        LogBean instance = new LogBean();
        instance.setUser(user);
    }

    /**
     * Test of getUser method, of class LogBean.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        LogBean instance = new LogBean();
        instance.setUser("Jubbe");
        String expResult = "Jubbe";
        String result = instance.getUser();
        assertEquals(expResult, result);
    }
    
}
