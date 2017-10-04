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

    @Override
    public State initiateCALL(DatagramPacket p, DatagramSocket s) {
        sendInvite(p,s);
        System.out.println("initiating call and returning callingOut");
        return new CallingOut();
    }

    private void sendInvite(DatagramPacket p, DatagramSocket s){
        try {
            System.out.println("Sending INVITE");
            s.send(p);
        } catch (IOException ex) {
            System.out.println("Gick inte att skicka invite: " + ex);
        }
    }
    
    @Override
    public State receivedINVITE(DatagramPacket p, DatagramSocket s) {
        System.out.println("Received INVITE");
        sendTRO(p,s);
        return new CallingIn();
    }

    private void sendTRO(DatagramPacket p, DatagramSocket s) {
        String tro = "TRO";
        System.out.println("Sending TRO");
        System.out.println("Sending to: " + p.getAddress().toString() + " " + p.getPort());
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
