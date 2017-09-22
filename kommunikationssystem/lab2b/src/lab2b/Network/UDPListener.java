/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2b.Application.NewSkype;

/**
 *
 * @author Jakob
 */
public class UDPListener implements Runnable{

    private DatagramSocket socket;
    private NewSkype newSkype;
    
    public UDPListener(DatagramSocket socket,NewSkype newSkype){
        this.socket = socket;
        this.newSkype = newSkype;
    }
    
    @Override
    public void run() {
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket p = new DatagramPacket(data,data.length);
            
            try {
                socket.receive(p);
                newSkype.handleMessage(p);
            } catch (IOException ex) {
                System.out.println("couldnt receive datagram packet xD: "+ex);
            }
            
        }
    }
    
}
