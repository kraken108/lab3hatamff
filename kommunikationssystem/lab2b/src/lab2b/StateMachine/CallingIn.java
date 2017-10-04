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
public class CallingIn extends StateUncallable {

    public CallingIn(DatagramPacket p, DatagramSocket s) {
        super(p);
    }

    @Override
    public String getStatename() {
        return ("CallingIn");
    }

    @Override
    public State receivedACK(DatagramPacket dp, DatagramSocket ds) {
        System.out.println("Received ACK");

        if (super.isCorrectClient(dp)) {
            try {
                return new InCall(dp, ds);
            } catch (IOException ex) {
                Logger.getLogger(CallingIn.class.getName()).log(Level.SEVERE, null, ex);
                return new Idle();
            }
        }else{
            sendError(dp,ds);
            return this;
        }

    }

    private void sendError(DatagramPacket p, DatagramSocket s) {
        String message = "ERROR";
        byte[] data = message.getBytes();
        p.setData(data);
        p.setLength(data.length);
        try {
            s.send(p);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
