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
public class CallingOut extends StateUncallable{
    
    public State Busy(){
        return new Idle();
    }
    
    public State troAck(){
        
        return new InCall();
    }  

    @Override
    public String getStatename() {
        return "CallingOut";
    }
    
    public State receivedBusy(){
        return new Idle();
    }
    
    public State receivedTro(DatagramPacket dp, DatagramSocket ds){
        
        sendACK(dp,ds);
        return new InCall(); 
    }
    
    private void sendACK(DatagramPacket dp, DatagramSocket ds){
        
        String ack = "ACK";
        dp.setData(ack.getBytes());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(CallingOut.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
}    
    

