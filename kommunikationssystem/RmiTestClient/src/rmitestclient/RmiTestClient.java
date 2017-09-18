/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitestclient;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Jakob
 */
public class RmiTestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            
// Get a remote reference to the distributed object from the rmi registry

            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);

            Power pow = (Power) myRegistry.lookup("power");

            System.out.println(pow.say("Hello"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
