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
    
    private void sendAudioPortInfo() throws IOException{
        String s = "PORT " + audioSocket.getLocalPort();
        byte[] data = s.getBytes();
        packet.setData(data);
        packet.setLength(data.length);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            throw(ex);
        }
    }
    
    private void terminateAudioSocket(){
         try{
            audioSocket.stopStreaming();
            audioSocket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public State receivedERROR(){
        terminateAudioSocket();
        return new Idle();
    }
    
    @Override
    public State receivedPORT(DatagramPacket p, DatagramSocket s){
        String message = new String(p.getData());
        String[] splitted = message.split(" ");
        int port = Integer.parseInt(splitted[1]);
        try {
            audioSocket.connectTo(p.getAddress(), port);
            audioSocket.startStreaming();
        } catch (IOException ex) {
            System.out.println("Couldnt connect to other audiosocket: " + ex);
            terminateAudioSocket();
            return new Idle();
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
        try {
            sendBye(dp,ds);
            terminateAudioSocket();
            System.out.println("Hanging up ip: " + packet.getAddress().getHostAddress());
            System.out.println("Hanging up port: " + packet.getPort());
            return new HangingUp(packet);  
        } catch (Exception ex) {
            Logger.getLogger(InCall.class.getName()).log(Level.SEVERE, null, ex);
            return new Idle();
        }
              
    }
    
    
    public void sendBye(DatagramPacket dp, DatagramSocket ds) throws IOException{
        System.out.println("Sending BYE");
        String bye="BYE";
        dp.setData(bye.getBytes());
        dp.setLength(bye.length());
        dp.setAddress(packet.getAddress());
        dp.setPort(packet.getPort());
        try {
            ds.send(dp);
        } catch (IOException ex) {
            throw(ex);
        }catch(Exception e){
            throw(e);
        }     
    }
    
    ///svarar ok på bye och övergår i idle
    private void sendOk(DatagramPacket dp, DatagramSocket ds){
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
        terminateAudioSocket();
        return new Idle();        
    }
    
}


