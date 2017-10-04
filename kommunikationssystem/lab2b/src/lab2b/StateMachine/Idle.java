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
        try {
            sendInvite(p,s);
            return new CallingOut(p);
        } catch (IOException ex) {
            System.out.println("Failed to send invite: " + ex);
            return this;
        }
        
    }

    private void sendInvite(DatagramPacket p, DatagramSocket s) throws IOException{
        try {
            System.out.println("Sending INVITE");
            s.send(p);
        } catch (IOException ex) {
            throw(ex);
        }
    }
    
    @Override
    public State receivedINVITE(DatagramPacket p, DatagramSocket s) {
        try {
            sendTRO(p,s);
            return new CallingIn(p,s);
        } catch (IOException ex) {
            Logger.getLogger(Idle.class.getName()).log(Level.SEVERE, null, ex);
            return this;
        }
        
    }

    private void sendTRO(DatagramPacket p, DatagramSocket s) throws IOException {
        String tro = "TRO";
        System.out.println("Sending TRO");
        System.out.println("Sending to: " + p.getAddress().toString() + " " + p.getPort());
        DatagramPacket returnPacket = new DatagramPacket(tro.getBytes(),tro.getBytes().length,p.getAddress(),p.getPort());
        try {
            s.send(returnPacket);
        } catch (IOException ex) {
            System.out.println("Gick inte att svara TRO: ");
            throw(ex);
        }
    }

    @Override
    public String getStatename() {
        return "Idle";
    }

}
