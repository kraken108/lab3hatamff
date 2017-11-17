/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Model.User;
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
public class UserHandlerTest {

    public UserHandlerTest() {
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
     * Test of login method, of class UserHandler.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        UserHandler instance = new UserHandler();
        Boolean expResult = false;
        Boolean result = instance.login(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserHandler.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserHandler instance = new UserHandler();
        String expResult = "Jubbe";
        List<User> result = instance.getAllUsers();
        assertEquals(expResult, result.get(0).getUsername());
    }

    /**
     * Test of checkIfAlreadyExists method, of class UserHandler.
     */
    @Test
    public void testCheckIfAlreadyExists() {
        System.out.println("checkIfAlreadyExists");
        String username = "Jubbe";
        UserHandler instance = new UserHandler();
        Boolean expResult = true;
        Boolean result = instance.checkIfAlreadyExists(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckIfAlreadyExists2() {
        System.out.println("checkIfAlreadyExists");
        String username = "";
        UserHandler instance = new UserHandler();
        Boolean expResult = false;
        Boolean result = instance.checkIfAlreadyExists(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of createUser method, of class UserHandler.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        String username = "DummyUser";
        String password = "DummyPass";
        UserHandler instance = new UserHandler();
        String expResult = "Username already exists!";
        String result = instance.createUser(username, password);
        assertEquals(expResult, result);
    }

}
