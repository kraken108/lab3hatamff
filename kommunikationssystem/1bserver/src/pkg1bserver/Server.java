/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1bserver;

import java.io.IOException;
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
    private ArrayList<Client> clients;
    private Boolean running;
    
    public Server(int port) throws IOException{
        clients = new ArrayList<>();
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
        Client c = new Client(clientSocket,"Hasse");
        clients.add(c);
        c.start();
    }
    
}
