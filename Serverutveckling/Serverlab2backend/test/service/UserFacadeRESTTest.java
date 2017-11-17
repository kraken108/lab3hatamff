/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
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
public class UserFacadeRESTTest {

    public UserFacadeRESTTest() {
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
     * Test of createUser method, of class UserFacadeREST.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        User user = new User();
        user.setUsername("DummyUser");
        user.setPassword("DummyPass");
        UserFacadeREST instance = new UserFacadeREST();
        String expResult = "Conflict";
        Response result = instance.createUser(user);
        assertEquals(expResult, result.getStatusInfo().toString());
    }

    /**
     * Test of login method, of class UserFacadeREST.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        User user = new User();
        user.setUsername("DummyUser");
        user.setPassword("DummyPass");
        UserFacadeREST instance = new UserFacadeREST();
        String expResult = "Found";
        Response result = instance.login(user);
        assertEquals(expResult, result.getStatusInfo().toString());
    }

    /**
     * Test of findAll method, of class UserFacadeREST.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserFacadeREST instance = new UserFacadeREST();
        String expResult = "Jubbe";
        List<User> result = instance.findAll();
        assertEquals(expResult, result.get(0).getUsername());
    }

    /**
     * Test of checkAlreadyExists method, of class UserFacadeREST.
     */
    @Test
    public void testCheckAlreadyExists() {
        System.out.println("checkAlreadyExists");
        User user = new User();
        user.setUsername("DummyUser");
        UserFacadeREST instance = new UserFacadeREST();
        String expResult = "Found";
        Response result = instance.checkAlreadyExists(user);
        assertEquals(expResult, result.getStatusInfo().toString());
    }

    @Test
    public void testCheckAlreadyExists2() {
        System.out.println("checkAlreadyExists");
        User user = new User();
        user.setUsername("");
        UserFacadeREST instance = new UserFacadeREST();
        String expResult = "Not Found";
        Response result = instance.checkAlreadyExists(user);
        assertEquals(expResult, result.getStatusInfo().toString());
    }

}
