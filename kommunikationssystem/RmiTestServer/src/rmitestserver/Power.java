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
import java.rmi.Remote;

public interface Power extends Remote {

    public String say(String message) throws java.rmi.RemoteException;
}
