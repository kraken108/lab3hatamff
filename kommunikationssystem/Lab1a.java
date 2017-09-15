/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1a;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author micke1
 */
public class Lab1a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        int arg;
        arg=Integer.parseInt(args[1]);
        
        Client c = new Client(args[0], arg);
        
        if(c.start()==-1){
            System.exit(0);
            System.out.println("KRASH!!!!!!!!");
        }
        

        
        // TODO code application logic here
    }
    
}
