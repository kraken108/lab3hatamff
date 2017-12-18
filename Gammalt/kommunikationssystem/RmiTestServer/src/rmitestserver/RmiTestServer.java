/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitestserver;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Jakob
 */
public class RmiTestServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            Registry registry = LocateRegistry.createRegistry(1099);
            // Create the distributed object
            PowerImpl pow = new PowerImpl();
            // Register the object in the RMI registry
            registry.rebind("power", pow);
            System.out.println("Power initialized");
        } catch (Exception e) {
            System.err.println("Error initializing power: " + e.toString());
        }
    }

}
