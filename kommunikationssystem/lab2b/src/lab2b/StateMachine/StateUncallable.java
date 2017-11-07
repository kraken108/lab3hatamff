/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public abstract class StateUncallable extends State {

    private final int TIMEOUT = 5000;
    private long initiationTime;
    private DatagramPacket packet;

    public StateUncallable() {
        initiationTime = -1;
    }

    public StateUncallable(DatagramPacket p) {
        initiationTime = System.currentTimeMillis();
        packet = p;
    }

    @Override
    public String getStatename() {
        return "StateUncallable";
    }

    public State inviteBusy() {
        return this;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    @Override
    public State initiateCALL(DatagramPacket p, DatagramSocket s) {
        if (initiationTime != -1 && timeoutExpired()) {
            try {
                sendInvite(p, s);
                return new CallingOut(p);
            } catch (IOException ex) {
                System.out.println("Failed to send invite: " + ex);
                return this;
            }
        }else{
            return this;
        }
    }

    private void sendInvite(DatagramPacket p, DatagramSocket s) throws IOException {
        try {
            System.out.println("Sending INVITE");
            s.send(p);
        } catch (IOException ex) {
            throw (ex);
        }
    }

    @Override
    public State receivedINVITE(DatagramPacket dp, DatagramSocket ds) {
        if (initiationTime != -1) {
            if (!isCorrectClient(dp)) {
                if (timeoutExpired()) {
                    //Starta nytt samtal
                    System.out.println("Timeout expired,starting new call");
                    CallingIn newState = new CallingIn(dp, ds);
                    try {
                        sendTRO(dp, ds);
                        return newState;
                    } catch (Exception ex) {
                        System.out.println(ex);
                        return new Idle();
                    }

                } else {
                    System.out.println("Busy!");
                    sendBusy(dp, ds);
                }
            } else {
                sendError(dp, ds);
                return new Idle();
            }
        } else {
            System.out.println("Busy!");
            sendBusy(dp, ds);
        }

        return this;

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

    private void sendTRO(DatagramPacket p, DatagramSocket s) throws Exception {
        String message = "TRO";
        byte[] data = message.getBytes();
        p.setData(data);
        p.setLength(data.length);
        try {
            s.send(p);
        } catch (Exception e) {
            throw (e);
        }
    }

    @Override
    public State receivedERROR() {
        return new Idle();
    }

    private void sendBusy(DatagramPacket dp, DatagramSocket ds) {

        String busy = "BUSY";
        dp.setData(busy.getBytes());
        dp.setLength(busy.length());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(StateUncallable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean timeoutExpired() {
        if (System.currentTimeMillis() > initiationTime + TIMEOUT) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isCorrectClient(DatagramPacket p) {
        if (packet == null) {
            return false;
        }
        if (p.getAddress().getHostAddress().equals(packet.getAddress().getHostAddress())) {
            if (p.getPort() == packet.getPort()) {
                return true;
            }
        }
        return false;
    }

}
