/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Controller;
import lab2b.StateMachine.State;
import lab2b.StateMachine.Idle;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Anders
 */
public class CallController {
    
    public enum Signal{
        INITIATE_INVITE,INVITE
        ,BUSY,TRO,OK,REQUEST_HANGUP
        ,BYE,ERROR,ACK;
    }
    private State currentState;
    
    public CallController(){
        currentState = new Idle();
    }
    
    public void processNextEvent(Signal signal,DatagramPacket p,DatagramSocket s){
        switch(signal){
            case INITIATE_INVITE: invokeInitiateCall(p,s);
            case INVITE: invokeReceivedInvite(p,s);
            case BUSY: invokeReceivedBusy();
            case TRO: invokeReceivedTRO(p,s);
            case OK: invokeReceivedOK();
            case REQUEST_HANGUP: invokeRequestHangUp(p,s);
            case BYE: invokeReceivedBYE(p,s);
            case ERROR: invokeReceivedError();
            case ACK: invokeReceivedACK();
        }
    }
    
    public void getState(){
       // return currentState.getStateName();
    }

    public void invokeInitiateCall(DatagramPacket p, DatagramSocket s){
        currentState = currentState.initiateCALL(p, s);
    }
    
    public void invokeReceivedInvite(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedINVITE(p,s);
    }
    
    public void invokeReceivedBusy(){
        currentState = currentState.receivedBUSY();
    }
    
    public void invokeReceivedTRO(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedTRO(p,s);
    }

    public void invokeReceivedACK(){
        currentState = currentState.receivedACK();
    }
    
    public void invokeReceivedBYE(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedBYE(p, s);
    }
    
    public void invokeRequestHangUp(DatagramPacket p, DatagramSocket s){
        currentState = currentState.requestHANGUP(p,s);
    }
    
    public void invokeReceivedError(){
        currentState = currentState.receivedERROR();
    }
    
    public void invokeReceivedOK(){
        currentState = currentState.receivedOK();
    }
   
}
