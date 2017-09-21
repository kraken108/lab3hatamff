package lab2b.States;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
;

/**
 *
 * @author Michael
 */
public abstract class State {
    
    public abstract String getStatename();
    
    public State receivedIdle(){
        return this;
    };
    
    public State receivedInCall(){
        return this;
    }
    
    public State receivedHangingUp(){
        return this;
    }
    
    public State receivedCallingOut(){
        return this;
    }
    
    public State receivedCallingIn(){
        return this;
    } 
    
    
    
}
