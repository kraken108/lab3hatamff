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
    
    public State receivedIdle(){
        return this;
    };    
    
    public State receivedInvite(DatagramPacket p, DatagramSocket s){
        return this;
    }
    
    public State receivedInitiateInvite(){
        return this;
    }
    
    public State receivedRequestHangup(){
        return this;
    }
    
    public State receivedOkHangUp(){
        return this;
    }
    
    public State initiateCall(DatagramPacket p, DatagramSocket s){
        return this;
    }
    
    public State receivedCallingIn(){
        return this;
    }  
    
    public State isBusy(){
        return this;
    }
    
    public State sendACK(){
        return this;
    }
    
    public State sendByeOk(){
        return this;
    }
    
    public State sendError(){
        return this;
    }
    
    public State sendTROACK(){
        return this;
    }
    
    public State okHangUp(){
        return this;
    }   
}
