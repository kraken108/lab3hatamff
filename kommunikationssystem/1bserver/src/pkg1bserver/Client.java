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
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class Client implements Runnable {

    private Socket socket;
    private Thread thread;
    private BufferedReader in;
    private PrintWriter out;
    private String threadName;
    private Client[] clients;
    private int id;
    private String clientName;
    private Boolean running;

    public Client(Socket socket, Client[] clients, int id) throws IOException {

        this.id = id;
        this.clients = clients;
        this.clientName = "Client " + id;
        this.threadName = socket.getInetAddress().toString();
        this.socket = socket;
        thread = null;

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome to the chat, Client " + id + "!");
            sendAvailableCommands();
            out.println("Enjoy!");
        } catch (IOException ie) {
            terminateSession();
            throw (ie);
        }
    }

    private void sendAvailableCommands() {
        out.println("The following commands are supported:");
        out.println("/quit - disconnect from the chat");
        out.println("/who - list all connected clients");
        out.println("/nick <nickname> - give yourself a nickname");
        out.println("/help - list all available commands");
    }

    private void sendToOtherClients(String message) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null && i != id) {
                clients[i].send(message);
            }
        }
    }

    public String getClientName() {
        return clientName;
    }

    private String getAllClients() {
        String s = "Connected clients: ";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null) {
                s += clients[i].getClientName();
                if (!(i == clients.length - 1)) {
                    s += ", ";
                }
            }

        }
        return s;
    }

    private Boolean nameAlreadyExists(String name) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null && i != id) {
                if (clients[i].getClientName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getTheName(String message) {
        String s = "";
        for (int i = 6; i < message.length(); i++) {
            s += message.charAt(i);
        }
        return s;
    }

    
    private String removeBackSpaces(String message){
        String newMessage = "";
        
        for(int i = 0; i<message.length(); i++){
            if(message.charAt(i) == '\b'){
                
            }else{
                newMessage += message.charAt(i);
            }
        }
        
        return newMessage;
    }
    
    private void handleMessage(String message) {
        
        message = removeBackSpaces(message);
        
        if (message.length() <= 0) {
            return;
        }
        
        removeBackSpaces(message);
        if (message.equals("/quit")) {
            out.println("Goodbye!");
            terminateSession();
        } else if (message.equals("/who")) {
            out.println(getAllClients());
        } else if (message.startsWith("/nick ")) {
            if (!nameAlreadyExists(getTheName(message))) {
                clientName = getTheName(message);
                out.println("Hello " + clientName + "!");
            } else {
                out.println("Name already exists!");
            }
        } else if (message.equals("/help")) {
            sendAvailableCommands();
        } else if (message.charAt(0) == '/') {
            out.println("Error: unknown command");
        } else {
            sendToOtherClients(clientName + ": " + message);
        }
    }

    @Override
    public void run() {
        //do stuff

        while (running) {
            try {
                String incoming = in.readLine();
                if (incoming == null) {
                    terminateSession();
                } else {
                    handleMessage(incoming);
                }
            } catch (IOException ex) {
                terminateSession();
                return;
            }
        }
    }

    private void terminateSession() {

        sendToOtherClients(clientName + " has disconnected from the chat.");

        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            clients[id] = null;
            running = false;
        }
    }

    //säkerställer att inga andra trådar kan använda send samtidigt, orden kan blandas
    private synchronized void send(String message) {
        out.println(message);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            System.out.println("Starting new client thread");
            running = true;
            thread.start();
        }
    }

}
