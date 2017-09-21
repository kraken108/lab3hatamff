/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Controller;
import java.net.DatagramPacket;
import lab2b.States.*;

/**
 *
 * @author Anders
 */
public class CallController {
    
    public enum Signal{
        INITIATE_INVITE,INVITE
        ,BUSY,TRO,OK,REQUEST_HANGUP
        ,BYE,ERROR;
    }
    private State currentState;
    
    public CallController(){
        currentState = new Idle();
    }
    
    public void processNextEvent(Signal signal,DatagramPacket p){
        switch(signal){
            case INITIATE_INVITE: break;
            case INVITE: break;
            case BUSY: break;
            case TRO: break;
            case OK: break;
            case REQUEST_HANGUP: break;
            case BYE: break;
            case ERROR: break;
        }
    }
    
    public void getState(){
       // return currentState.getStateName();
    }

    public void invokeInitiateCall(){
        
    }
    
    public void invokeReceivedInvite(){
        
    }
    
    public void invokeReceivedBusy(){
        
    }
    
    public void invokeReceivedTRO(){
        
    }
    
    
    public void invokeReceivedACK(){
        
    }
    
    public void invokeReceivedBYE(){
        
    }
    
    public void invokeRequestHangUp(){
        
    }
    
    public void invokeReceivedError(){
        
    }
    
    public void invokeReceivedOK(){
        
    }
   
}
