/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;

import java.net.*;

/**
 *
 * @author Michael
 */
public abstract class StateUncallable extends State {

    private DatagramPacket packet;
    private DatagramSocket socket;
    
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
}
