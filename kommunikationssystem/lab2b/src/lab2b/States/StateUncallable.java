/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.States;

import java.net.DatagramPacket;

/**
 *
 * @author Michael
 */
public abstract class StateUncallable extends State{

    DatagramPacket p;
    
    @Override
    public String getStatename() {
        return "StateUncallable";
    }
    
    public State inviteBusy(){
        return this;
    } 
    
    
    
}
