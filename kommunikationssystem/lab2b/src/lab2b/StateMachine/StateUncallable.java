/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public abstract class StateUncallable extends State {

    DatagramPacket packet;
    DatagramSocket socket;
    
    @Override
    public String getStatename() {
        return "StateUncallable";
    }

    public State inviteBusy() {
        return this;
    }
    
    public DatagramPacket getPacket(){
        return packet;
    }

    public DatagramSocket getSocket(){
        return socket;
    }
    
    @Override
    public State receivedINVITE(DatagramPacket dp, DatagramSocket ds){
        
        sendBusy(dp,ds);
        return this; 
        
    }
    
    private void sendBusy(DatagramPacket dp, DatagramSocket ds){
        
        String busy = "BUSY";
        dp.setData(busy.getBytes());
        dp.setLength(busy.length());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(StateUncallable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
