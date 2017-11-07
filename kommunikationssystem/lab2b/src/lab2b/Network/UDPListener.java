/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
                String message = removeZeros(p);
                p.setData(message.getBytes());
                p.setLength(message.length());
                newSkype.handleMessage(p,socket);
                System.out.println(message);
            } catch (IOException ex) {
                System.out.println("Failed to receive packet: "+ex);
            }
            
        }
    }
    
    
    private String removeZeros(DatagramPacket p){
        String s = new String(p.getData());
        
        String newStr = "";
        
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i) != '\0'){
                newStr += s.charAt(i);
            }else{
                break;
            }
        }
        return newStr;
    }
    
}
