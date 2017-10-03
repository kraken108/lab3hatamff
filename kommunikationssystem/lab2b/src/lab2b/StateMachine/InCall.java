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
public class InCall extends StateUncallable{
    

    @Override
    public String getStatename() {
        return "InCall";
    }
    
    @Override
    public State requestHANGUP(DatagramPacket dp, DatagramSocket ds){
        
        sendBye(dp,ds);
        return new HangingUp();        
    }
    
    
    public void sendBye(DatagramPacket dp, DatagramSocket ds){
        
        String bye="Bye";
        dp.setData(bye.getBytes());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void sendOk(DatagramPacket dp, DatagramSocket ds){
        
        String ok = "OK";
        dp.setData(ok.getBytes());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public State receivedBye(DatagramPacket dp, DatagramSocket ds){
        
        sendOk(dp,ds);
        return new Idle();        
    }
    
    public State receivedError(DatagramPacket dp, DatagramSocket ds){
        
        return new Idle();        
    }
}


