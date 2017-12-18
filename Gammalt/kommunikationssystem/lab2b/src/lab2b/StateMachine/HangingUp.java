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
public class HangingUp extends StateUncallable {

    public HangingUp(DatagramPacket p) {
        super(p);
    }

    @Override
    public String getStatename() {
        return "HangingUp";
    }

    @Override
    public State receivedOK() {
        return new Idle();
    }


}
