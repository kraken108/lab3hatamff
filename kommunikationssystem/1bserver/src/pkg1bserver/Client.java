/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1bserver;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class Client implements Runnable{
    private Socket socket;
    private Thread thread;
    private BufferedReader in;
    private PrintWriter out;
    private String threadName;
    private String clientName;
    
    public Client(Socket socket,String clientName){

        this.threadName = socket.getInetAddress().toString();
        this.socket = socket;
        thread = null;


        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ie) {
            System.out.println(ie);
        }
    }

    @Override
    public void run() {
        //do stuff
        
        while(true){
            try {
                String incoming = in.readLine();
                if(incoming == null){
                    terminateSession();
                    System.out.println("Terminating");
                    return;
                }
                System.out.println(incoming);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                terminateSession();
                return;
            }
        }
    }
    
    private void terminateSession(){
       // exit(1);
    }
    
    
    public void start(){
        if (thread == null) {
            thread = new Thread(this, threadName);
            System.out.println("Starting new client thread");
            try{
                out.println("WELCOME");
            }catch(Exception e){
                terminateSession();
                return;
            }
            thread.start();
        }
    }
    
    
}
