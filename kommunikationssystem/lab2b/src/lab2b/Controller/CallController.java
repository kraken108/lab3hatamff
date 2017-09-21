/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Controller;
import lab2b.States.*;

/**
 *
 * @author Anders
 */
public class CallController {
    private State currentState;
    
    public CallController(){
        currentState = new InCall();
    }
    
    public String getState(){
        return currentState.getStateName();
    }
    
    public void invokeSendInvite(){
        
    }
    
    public void invokeReceivedInvite(){
        
    }
    
    public void invokeReceivedBusy(){
        
    }
    
    public void invokeReceivedTRO(){
        
    }
    
    public void invokeReceived(){
        
    }
    
    public void invokeSendTRO(){
        
    }
    
    public void invokeReceivedACK(){
        
    }
    
    public void invokeSendAck(){
        
    }
    
    
    
}
