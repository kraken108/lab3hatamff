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
    private ServerSocket serverSocket;
    private Boolean inSession;
    private DatagramSocket ds;
    
    public NewSkype() throws IOException{
        callController = new CallController();
        //serverSocket = new ServerSocket(5678);
        inSession = false;
        ds = new DatagramSocket(5678);
    }
    
    private void handleMessage(String message){
        if(message.equals(""));
    }
    public void start(){
        Thread t = new Thread(new KeyboardListener(this));
        t.start();
        Thread t2 = new Thread(new UDPListener(ds,this));
        t2.start();
    }
    
    public Boolean isInSession(){
        return inSession;
    }
    
    public void handleInput(String message){
        System.out.println("öh new input lol:");
        System.out.println(message);
    }
    
    public void handleMessage(DatagramPacket p){
        String message = new String(p.getData());
        
        if(message.equals("INVITE")){
            callController.processNextEvent(Signal.INVITE,p);
        }else if(message.equals("BUSY")){
            callController.processNextEvent(Signal.BUSY,p);
        }else if(message.equals("TRO")){
            callController.processNextEvent(Signal.TRO,p);
        }else if(message.equals("OK")){
            callController.processNextEvent(Signal.OK,p);
        }else if(message.equals("BYE")){
            callController.processNextEvent(Signal.BYE,p);
        }else if(message.equals("ERROR")){
            callController.processNextEvent(Signal.ERROR,p);
        }else{
            //do nothing?
        }
    }
}
