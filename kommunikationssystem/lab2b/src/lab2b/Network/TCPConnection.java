/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import lab2b.Controller.CallController;

/**
 *
 * @author Anders
 */
public class TCPConnection implements Runnable{

    private Thread t;
    private String threadName;
    private Socket socket;
    private CallController callController;
    private BufferedReader in;
    private PrintWriter out;
    
    public TCPConnection(Socket socket, CallController callController,String threadName){
        
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void start(){
        if(t == null){
            t.start();
        }
    }
}
