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
public class NavigationBeanTest {
    
    public NavigationBeanTest() {
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
     * Test of setUserBean method, of class NavigationBean.
     */
    @Test
    public void testSetUserBean() {
        System.out.println("setUserBean");
        UserBean userBean = null;
        NavigationBean instance = new NavigationBean();
        instance.setUserBean(userBean);
    }

    /**
     * Test of goToLogin method, of class NavigationBean.
     */
    @Test(expected=NullPointerException.class)
    public void testGoToLogin() {
        System.out.println("goToLogin");
        NavigationBean instance = new NavigationBean();
        String expResult = "login.xhtml";
        String result = instance.goToLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of goToCreateAccount method, of class NavigationBean.
     */
    @Test(expected=NullPointerException.class)
    public void testGoToCreateAccount() {
        System.out.println("goToCreateAccount");
        NavigationBean instance = new NavigationBean();
        String expResult = "createaccount.xhtml";
        String result = instance.goToCreateAccount();
        assertEquals(expResult, result);
    }

    /**
     * Test of goToInbox method, of class NavigationBean.
     */
    @Test
    public void testGoToInbox() {
        System.out.println("goToInbox");
        NavigationBean instance = new NavigationBean();
        String expResult = "inbox?faces-redirect=true";
        String result = instance.goToInbox();
        assertEquals(expResult, result);
    }
    
}
