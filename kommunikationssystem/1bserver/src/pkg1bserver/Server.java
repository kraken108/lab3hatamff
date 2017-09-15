/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1bserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class Server {
    private ServerSocket serverSocket;
    private Client[] clients;
    private Boolean running;
    private int maxClients;
    
    public Server(int port) throws IOException{
        maxClients = 10;
        clients = new Client[maxClients];
        for(int i = 0; i<maxClients; i++){
            clients[i] = null;
        }
        
        running = false;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            throw(ex);           
        }
    }
    
    public void startServer(){
        running = true;
        acceptNewConnections();
    }
    
    private void acceptNewConnections(){
        System.out.println("Waiting for new connection");
    
        while(running){
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection!");
                initiateNewClient(clientSocket);
            } catch (IOException ex){
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void initiateNewClient(Socket clientSocket){
        
        for(int i = 0; i < maxClients; i++){
            if(clients[i] == null){
                clients[i] = new Client(clientSocket,clients,i);
                clients[i].start();
                break;
            }
            if(i == maxClients-1){
                try {
                    PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
                    pw.println("Server is full. Please try again later!");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    private void welcomeMessage(Socket clientSocket){

    }
    
}
