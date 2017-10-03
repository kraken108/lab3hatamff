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
public class Idle extends State {

    public State initiateCall(DatagramPacket p, DatagramSocket s) {
        sendInvite(p,s);
        return new CallingOut();
    }

    private void sendInvite(DatagramPacket p, DatagramSocket s){
        try {
            System.out.println("Skickar invite");
            s.send(p);
        } catch (IOException ex) {
            System.out.println("Gick inte att skicka invite: " + ex);
        }
    }
    
    public State receivedInvite(DatagramPacket p, DatagramSocket s) {
        sendTRO(p,s);
        return new CallingIn();
    }

    private void sendTRO(DatagramPacket p, DatagramSocket s) {
        String tro = "TRO";
        System.out.println("Skickar TRO");
        DatagramPacket returnPacket = new DatagramPacket(tro.getBytes(),tro.getBytes().length,p.getAddress(),p.getPort());
        try {
            s.send(returnPacket);
        } catch (IOException ex) {
            System.out.println("Gick inte att svara TRO: " + ex);
        }
    }

    @Override
    public String getStatename() {
        return "Idle";
    }

}
