/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Model.Post;
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
public class PostFacadeRESTTest {
    
    public PostFacadeRESTTest() {
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
     * Test of createNewPost method, of class PostFacadeREST.
     */
    @Test
    public void testCreateNewPost() {
        System.out.println("createNewPost");
        Post entity = new Post();
        entity.setMessage("Testmeddelande");
        User u = new User();
        u.setUsername("DummyUser");
        entity.setUser(u);
        
        PostFacadeREST instance = new PostFacadeREST();
        String expResult = "Created";
        Response result = instance.createNewPost(entity);
        assertEquals(expResult, result.getStatusInfo().toString());

    }

    /**
     * Test of getPostsByUser method, of class PostFacadeREST.
     */
    @Test
    public void testGetPostsByUser() {
        System.out.println("getPostsByUser");
        String user = "Jubbe";
        PostFacadeREST instance = new PostFacadeREST();
        String expResult = "Meddelande 1";
        List<Post> result = instance.getPostsByUser(user);
        assertEquals(expResult, result.get(0).getMessage());
    }
    
}
