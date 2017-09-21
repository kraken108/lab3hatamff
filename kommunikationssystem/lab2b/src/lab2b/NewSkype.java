/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2b.Controller.CallController;

/**
 *
 * @author Anders
 */
public class NewSkype {
    private CallController callController;
    private ServerSocket serverSocket;
    private Boolean inSession;
    private DatagramSocket datagramSocket;
    
    public NewSkype() throws IOException{
        callController = new CallController();
        //serverSocket = new ServerSocket(5678);
        inSession = false;
        datagramSocket = new DatagramSocket(5678);
    }
    
    private void handleMessage(String message){
        if(message.equals(""));
    }
    public void start(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data,data.length);
            try {
                datagramSocket.receive(packet);
                String message = new String(packet.getData());
                handleMessage(message);
            } catch (IOException ex) {
                Logger.getLogger(NewSkype.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    public Boolean isInSession(){
        return inSession;
    }
}
