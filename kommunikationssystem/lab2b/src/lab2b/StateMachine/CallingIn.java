/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class CallingIn extends StateUncallable{
    
 
    public State ACK(){
        
        return new InCall();
    }

    @Override
    public String getStatename() {
        return ("CallingIn");
    }
    
    public State receivedACK(DatagramPacket dp, DatagramSocket ds){
        super.packet = dp;
        return new InCall();        
    }
    

    
    
}
