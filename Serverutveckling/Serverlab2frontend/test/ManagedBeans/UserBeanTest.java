/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ViewModel.User;
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
public class UserBeanTest {
    
    public UserBeanTest() {
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
     * Test of getUsers method, of class UserBean.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        UserBean instance = new UserBean();
        String expResult = "Jubbe";
        List<User> result = instance.getUsers();
        assertEquals(expResult, result.get(0).getUsername());
    }

    /**
     * Test of isLoggedInUser method, of class UserBean.
     */
    @Test
    public void testIsLoggedInUser() {
        System.out.println("isLoggedInUser");
        
        String username = "Dummy";
        UserBean instance = new UserBean();
        instance.setUsername("Dummy");
        Boolean expResult = true;
        Boolean result = instance.isLoggedInUser(username);
        assertEquals(expResult, result);
    }


    /**
     * Test of getIsLoggedIn method, of class UserBean.
     */
    @Test
    public void testGetIsLoggedIn() {
        System.out.println("getIsLoggedIn");
        UserBean instance = new UserBean();
        Boolean expResult = false;
        Boolean result = instance.getIsLoggedIn();
        assertEquals(expResult, result);
    }


    /**
     * Test of logout method, of class UserBean.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        UserBean instance = new UserBean();
        Boolean expResult = false;
        instance.logout();
        assertEquals(expResult, instance.getIsLoggedIn());
    }

    /**
     * Test of doLogin method, of class UserBean.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        UserBean instance = new UserBean();
        instance.setUsername("Jubbe");
        instance.setPassword("abc123");
        Boolean expResult = true;
        instance.doLogin();
        assertEquals(expResult, instance.getIsLoggedIn());
    }



    /**
     * Test of nameAlreadyExists method, of class UserBean.
     */
    @Test
    public void testNameAlreadyExists() {
        System.out.println("nameAlreadyExists");
        UserBean instance = new UserBean();
        String expResult = "Username already exists";
        instance.setUsername("DummyUser");
        String result = instance.nameAlreadyExists();
        assertEquals(expResult, result);
    }
    
}
