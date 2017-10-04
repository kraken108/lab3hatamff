/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class CallingIn extends StateUncallable{

    private long initiationTime; 
    private int timeOut;
    
    public CallingIn(){
        
        timeOut=20000;
        
    }
    
    
    @Override
    public String getStatename() {
        return ("CallingIn");
    }
    
    @Override
    public State receivedACK(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Received ACK");
        
        try {        
            return new InCall(dp,ds);
        } catch (IOException ex) {
            Logger.getLogger(CallingIn.class.getName()).log(Level.SEVERE, null, ex);
            return new Idle();
        }
    }
    

    
    
}
