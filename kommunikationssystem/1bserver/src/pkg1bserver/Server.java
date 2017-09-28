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

//lyssnar på serversocketen, skapar nytt klientobjekt om den får ny klient

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
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            throw(ex);           
        }finally{
            running = true;
        }
    }
    
    public void startServer() throws IOException{
        try{
            acceptNewConnections();
        }catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            serverSocket.close();
        }
    }
    
    private void acceptNewConnections() throws IOException{
        System.out.println("Server started successfully!");
    
        while(running){
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection!");
                initiateNewClient(clientSocket);
            } catch (IOException ex){
                throw(ex);
            }
        }
    }
    
    private void initiateNewClient(Socket clientSocket){
        PrintWriter pw;
        for(int i = 0; i < maxClients; i++){
            if(clients[i] == null){
                try{
                    clients[i] = new Client(clientSocket,clients,i);
                    clients[i].start();
                }catch(IOException ex){
                    clients[i] = null;
                }finally{
                    break;
                }
            }
            if(i == maxClients-1){
                try {
                    pw = new PrintWriter(clientSocket.getOutputStream(), true);
                    pw.println("Server is full. Please try again later!");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    pw = null;
                }
            }
        }
        
    }
       
}
