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
public class InCall extends StateUncallable{
    
    private DatagramPacket packet;
    private DatagramSocket socket;

    public InCall(DatagramPacket p, DatagramSocket s){
        packet = p;
        socket = s;
    }
    
    @Override
    public String getStatename() {
        return "InCall";
    }
    
    @Override
    public State requestHANGUP(DatagramPacket dp, DatagramSocket ds){
        
        sendBye(dp,ds);
        return new HangingUp();        
    }
    
    
    public void sendBye(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Sending BYE");
        String bye="BYE";
        dp.setData(bye.getBytes());
        dp.setLength(bye.length());
        dp.setAddress(packet.getAddress());
        dp.setPort(packet.getPort());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            System.out.println("Exception");
            System.out.println(ex);
        }catch(Exception e){
            System.out.println("Exception 2");
            System.out.println(e);
        }     
    }
    
    public void sendOk(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Sending OK");
        String ok = "OK";
        dp.setData(ok.getBytes());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Override
    public State receivedBYE(DatagramPacket dp, DatagramSocket ds){
        System.out.println("Received BYE");
        sendOk(dp,ds);
        return new Idle();        
    }
    
}


