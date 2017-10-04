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
        ,BYE,ERROR,ACK,PORT;
    }
    private State currentState;
    
    public CallController(){
        currentState = new Idle();
    }
    
    public void processNextEvent(Signal signal,DatagramPacket p,DatagramSocket s){
        System.out.println("processNextEvent()");
        switch(signal){
            case INITIATE_INVITE: invokeInitiateCall(p,s);break;
            case INVITE: invokeReceivedInvite(p,s);break;
            case BUSY: invokeReceivedBusy();break;
            case TRO: invokeReceivedTRO(p,s);break;
            case OK: invokeReceivedOK();break;
            case REQUEST_HANGUP: invokeRequestHangUp(p,s);break;
            case BYE: invokeReceivedBYE(p,s);break;
            case ERROR: invokeReceivedError();break;
            case ACK: invokeReceivedACK(p,s);break;
            case PORT: invokeReceivedPORT(p,s);break;
            default: break;
        }
    }
    
    public void getState(){
       // return currentState.getStateName();
    }

    public void invokeReceivedPORT(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedPORT(p, s);
    }
    public void invokeInitiateCall(DatagramPacket p, DatagramSocket s){
        currentState = currentState.initiateCALL(p, s);
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedInvite(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedINVITE(p,s);
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedBusy(){
        currentState = currentState.receivedBUSY();
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedTRO(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedTRO(p,s);
        System.out.println(currentState.getStatename());
    }

    public void invokeReceivedACK(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedACK(p,s);
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedBYE(DatagramPacket p, DatagramSocket s){
        currentState = currentState.receivedBYE(p, s);
        System.out.println(currentState.getStatename());
    }
    
    public void invokeRequestHangUp(DatagramPacket p, DatagramSocket s){
        currentState = currentState.requestHANGUP(p,s);
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedError(){
        currentState = currentState.receivedERROR();
        System.out.println(currentState.getStatename());
    }
    
    public void invokeReceivedOK(){
        currentState = currentState.receivedOK();
        System.out.println(currentState.getStatename());
    }
   
}
