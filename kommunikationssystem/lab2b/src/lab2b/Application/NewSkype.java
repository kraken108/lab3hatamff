/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Application;

import lab2b.Application.KeyboardListener;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2b.Controller.CallController;
import lab2b.Controller.CallController.Signal;
import lab2b.Network.UDPListener;

/**
 *
 * @author Anders
 */
public class NewSkype {

    private CallController callController;
    private Boolean inSession;
    private DatagramSocket ds;

    private Boolean acceptTRO = true;
    private Boolean noHangup = false;
    private Boolean noAck = false;

    public NewSkype(int port, String param) throws IOException {
        callController = new CallController();
        inSession = false;
        ds = new DatagramSocket(port);
        if (param.equals("NOACK")) {
            noAck = true;
        } else if (param.equals("NOHANGUP")) {
            noHangup = true;
        }
    }

    public void start() throws Exception{
        Thread t = new Thread(new KeyboardListener(this));
        t.start();
        Thread t2 = new Thread(new UDPListener(ds, this));
        t2.start();
    }

    public Boolean isInSession() {
        return inSession;
    }

    private DatagramPacket constructInvite(String message) throws Exception {
        String[] strings = message.split(" ");
        if (strings.length < 3) {
            throw new Exception("Please enter an ip and a port");
        }

        byte[] data = "INITIATE_INVITE".getBytes();
        DatagramPacket p = new DatagramPacket(data, data.length);
        p.setAddress(InetAddress.getByName(strings[1]));
        p.setPort(Integer.parseInt(strings[2]));
        return p;
    }

    public void handleInput(String message) throws UnknownHostException, Exception {
        if (message.startsWith("CALLE")) {
            acceptTRO = false;
            handleMessage(constructInvite(message), ds);
            sendSecondInvite(constructInvite(message));
        } else if (message.startsWith("HANGUP")) {
            byte[] data = "HANGUP".getBytes();
            DatagramPacket p = new DatagramPacket(data, data.length);
            //handleMessage(p, ds);
            callController.processNextEvent(Signal.REQUEST_HANGUP, p, ds);
        } else if (message.startsWith("CALL")) {
            handleMessage(constructInvite(message), ds);

        } else {
            System.out.println("Unknown command");
        }
    }

    private void sendSecondInvite(DatagramPacket p) throws IOException {
        String s = "INVITE";
        byte[] data = s.getBytes();
        p.setData(data);
        p.setLength(data.length);
        ds.send(p);
    }

    public void handleMessage(DatagramPacket p, DatagramSocket s) {
        String message = new String(p.getData());
        if (message.startsWith("INVITE")) {
            callController.processNextEvent(Signal.INVITE, p, s);
        } else if (message.startsWith("INITIATE_INVITE")) {
            p.setData("INVITE".getBytes());
            p.setLength("INVITE".getBytes().length);
            callController.processNextEvent(Signal.INITIATE_INVITE, p, s);
        } else if (message.startsWith("BUSY")) {
            callController.processNextEvent(Signal.BUSY, p, s);
        } else if (message.startsWith("TRO")) {
            if (acceptTRO && !noAck) {
                callController.processNextEvent(Signal.TRO, p, s);
            }
        } else if (message.startsWith("OK")) {
            callController.processNextEvent(Signal.OK, p, s);
        } else if (message.startsWith("BYE")) {
            if(!noHangup){
                callController.processNextEvent(Signal.BYE, p, s);
            }
        } else if (message.startsWith("ERROR")) {
            System.out.println("Received ERROR");
            callController.processNextEvent(Signal.ERROR, p, s);
        } else if (message.startsWith("HANGUP")) {
            if (!noHangup) {
                callController.processNextEvent(Signal.REQUEST_HANGUP, p, s);
            }
        } else if (message.startsWith("ACK")) {
            callController.processNextEvent(Signal.ACK, p, s);
        } else if (message.startsWith("PORT")) {
            callController.processNextEvent(Signal.PORT, p, s);
        } else {
            System.out.println("Okänt paket :P");
            //do nothing?
        }
    }
}
