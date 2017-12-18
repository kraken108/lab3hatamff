/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitestclient;

/**
 *
 * @author Jakob
 */
public interface Power extends java.rmi.Remote {

    public String say(String message) throws java.rmi.RemoteException;
}
