/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2b.NewSkype;

/**
 *
 * @author Anders
 */
public class Listener implements Runnable {

    private ServerSocket serverSocket;
    private NewSkype newSkype;

    public Listener(ServerSocket serverSocket, NewSkype newSkype) {
        this.serverSocket = serverSocket;
        this.newSkype = newSkype;
    }

    @Override
    public void run() {
        //do stuff
        try {
            Socket newConnection = serverSocket.accept();
            if(newSkype.isInSession()){
                //send busy
            }else{
                //initiate new session
            }
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
