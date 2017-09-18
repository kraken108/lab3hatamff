/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitestserver;

/**
 *
 * @author Jakob
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PowerImpl extends UnicastRemoteObject implements Power {

    public PowerImpl() throws RemoteException {
    }

    @Override
    public String say(String message) throws RemoteException {
        return "Server says: " + message;
    }
    
}
