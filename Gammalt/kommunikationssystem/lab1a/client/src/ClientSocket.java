/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class ClientSocket {
    
    public ClientSocket(){
        
        try {
            DatagramSocket ds = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int byteSize=8;
        byte[] b = (byteSize+"").getBytes(); 
        DatagramPacket dp = new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp);
        
        
    }  
    
    
}
