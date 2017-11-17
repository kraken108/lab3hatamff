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
     * Test of isLoggedInUser method, of class UserBean.
     */
    @Test
    public void testIsLoggedInUser() {
        System.out.println("isLoggedInUser");
        String username = "";
        
        UserBean instance = new UserBean();
        instance.setUsername("Test");
        Boolean expResult = false;
        Boolean result = instance.isLoggedInUser(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsLoggedInUser2() {
        System.out.println("isLoggedInUser");
        String username = "Jubbe";
        UserBean instance = new UserBean();
        instance.setUsername("Jubbe");
        Boolean expResult = true;
        Boolean result = instance.isLoggedInUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatusMessage method, of class UserBean.
     */
    @Test
    public void testSetStatusMessage() {
        System.out.println("setStatusMessage");
        String statusMessage = "";
        UserBean instance = new UserBean();
        instance.setStatusMessage(statusMessage);
    }

    /**
     * Test of getPassword2 method, of class UserBean.
     */
    @Test
    public void testGetPassword2() {
        System.out.println("getPassword2");
        UserBean instance = new UserBean();
        instance.setPassword2("Test");
        String expResult = "Test";
        String result = instance.getPassword2();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword2 method, of class UserBean.
     */
    @Test
    public void testSetPassword2() {
        System.out.println("setPassword2");
        String password2 = "";
        UserBean instance = new UserBean();
        instance.setPassword2(password2);
    }

    /**
     * Test of setUsername method, of class UserBean.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String name = "";
        UserBean instance = new UserBean();
        instance.setUsername(name);
    }

    /**
     * Test of setPassword method, of class UserBean.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        UserBean instance = new UserBean();
        instance.setPassword(password);
    }

    /**
     * Test of getUsername method, of class UserBean.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        UserBean instance = new UserBean();
        instance.setUsername("Jubbe");
        String expResult = "Jubbe";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class UserBean.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        UserBean instance = new UserBean();
        instance.setPassword("Test");
        String expResult = "Test";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of redirectLoginPage method, of class UserBean.
     */
    @Test
    public void testRedirectLoginPage() {
        System.out.println("redirectLoginPage");
        UserBean instance = new UserBean();
        String expResult = "login.xhtml";
        String result = instance.redirectLoginPage();
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
     * Test of getStatusMessage method, of class UserBean.
     */
    @Test
    public void testGetStatusMessage() {
        System.out.println("getStatusMessage");
        UserBean instance = new UserBean();
        instance.setStatusMessage("Test");
        String expResult = "Test";
        String result = instance.getStatusMessage();
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

    @Test
    public void testDoLogin2() {
        System.out.println("doLogin");
        UserBean instance = new UserBean();
        instance.setUsername("");
        instance.setPassword("");
        Boolean expResult = false;
        instance.doLogin();
        assertEquals(expResult, instance.getIsLoggedIn());
    }

    /**
     * Test of createAccount method, of class UserBean.
     */
    @Test(expected=NullPointerException.class)
    public void testCreateAccount() {
        System.out.println("createAccount");
        UserBean instance = new UserBean();
        instance.createAccount();
    }

    /**
     * Test of nameAlreadyExists method, of class UserBean.
     */
    @Test
    public void testNameAlreadyExists() {
        System.out.println("nameAlreadyExists");
        UserBean instance = new UserBean();
        instance.setUsername("Jubbe");
        String expResult = "Username already exists!";
        String result = instance.nameAlreadyExists();
        assertEquals(expResult, result);
    }

}
