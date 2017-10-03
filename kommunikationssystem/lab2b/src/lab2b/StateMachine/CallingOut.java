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
    
    @Override
    public String getStatename() {
        return "CallingOut";
    }
    
    @Override
    public State receivedBUSY(){
        return new Idle();
    }
    
    @Override
    public State receivedTRO(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Received TRO");
        sendACK(dp,ds);

        return new InCall(dp,ds); 
    }
    
    private void sendACK(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Sending ACK");
        String ack = "ACK";
        dp.setData(ack.getBytes());
        dp.setLength(ack.length());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(CallingOut.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    

    
}    
    

