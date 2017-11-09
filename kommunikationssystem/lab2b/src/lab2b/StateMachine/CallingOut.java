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
public class CallingOut extends StateUncallable {

    public CallingOut(DatagramPacket p) {
        super(p);
    }

    @Override
    public String getStatename() {
        return "CallingOut";
    }

    @Override
    public State receivedBUSY(){
        return new Idle();
    }

    
    //tar emot TRO, skickar ACK
    @Override
    public State receivedTRO(DatagramPacket dp, DatagramSocket ds) {

        if (super.isCorrectClient(dp)) {
            try {
                sendACK(dp, ds);
                return new InCall(dp, ds);
            } catch (IOException ex) {
                Logger.getLogger(CallingOut.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void sendACK(DatagramPacket dp, DatagramSocket ds) throws IOException {
        System.out.println("Sending ACK");
        String ack = "ACK";
        dp.setData(ack.getBytes());
        dp.setLength(ack.length());
        try {
            ds.send(dp);
        } catch (IOException ex) {
           throw(ex);
        }
    }

}
