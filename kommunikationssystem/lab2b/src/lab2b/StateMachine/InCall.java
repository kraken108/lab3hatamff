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
    private AudioStreamUDP audioSocket;
    
    public InCall(DatagramPacket p, DatagramSocket s) throws IOException{
        packet = p;
        socket = s;
        audioSocket = new AudioStreamUDP();
        System.out.println("Audio socket opened on: " + audioSocket.getLocalPort());
        sendAudioPortInfo();
    }
    
    private void sendAudioPortInfo(){
        String s = "PORT " + audioSocket.getLocalPort();
        byte[] data = s.getBytes();
        packet.setData(data);
        packet.setLength(data.length);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public State receivedPORT(DatagramPacket p, DatagramSocket s){
        String message = new String(p.getData());
        String[] splitted = message.split(" ");
        int port = Integer.parseInt(splitted[1]);
        try {
            System.out.println("Connecting audio");
            audioSocket.connectTo(p.getAddress(), port);
            audioSocket.startStreaming();
            System.out.println("Started streaming");
        } catch (IOException ex) {
            System.out.println("Couldnt connect to other audiosocket");
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return this;
        }
        
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


