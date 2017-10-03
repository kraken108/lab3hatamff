package lab2b.StateMachine;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Michael
 */
public abstract class State {
    
    public abstract String getStatename();
      
    
    public State receivedInvite(DatagramPacket p, DatagramSocket s){
        return this;
    }
    
    public State receivedBusy(){
        return this;
    }
    
    public State receivedACK(){
        return this;
    }
    
    public State receivedOK(){
        return this;
    }
    
    public State requestHangUp(DatagramPacket p, DatagramSocket s){
        return this;
    }
    
    public State receivedBYE(DatagramPacket p, DatagramSocket s){
        return this;
    }
    
    public State receivedError(){
        return this;
    }
    
    public State receivedTRO(DatagramPacket p, DatagramSocket s){
        return this;
    }
        
    public State initiateCall(DatagramPacket p, DatagramSocket s){
        return this;
    }

}
