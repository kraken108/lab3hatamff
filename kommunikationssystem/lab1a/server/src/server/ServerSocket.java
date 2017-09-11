/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class ServerSocket {

    private DatagramSocket socket;
    private Boolean running;
    private Boolean hasConnection;
    private String word;
    private String wordStatus;
    private int maxAttempts;
    private Client currentClient;

    public ServerSocket(String word) throws SocketException {
        socket = new DatagramSocket(1234);
        running = false;
        hasConnection = false;
        this.word = word;
        wordStatus = "";
        maxAttempts = word.length() * 3;
        setupWordStatus();
    }

    private void setupWordStatus() {
        for (int i = 0; i < word.length(); i++) {
            wordStatus += "*";
        }
    }

    public void startServer() {
        running = true;
        acceptNewConnection();
    }

    private void acceptNewConnection() {
        byte[] data = new byte[1024];

        while (running) {
            System.out.println("Waiting for new client");
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                String message = new String(packet.getData());
                switch (message) {
                    case "HELLO":
                        initiateNewSession(packet);
                    default:
                        break;
                }
            } catch (IOException ex) {
                System.out.println(ex);
                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initiateNewSession(DatagramPacket packet) {
        currentClient = new Client(packet.getAddress(), packet.getPort());
        long connectionTime = System.currentTimeMillis();

        while (System.currentTimeMillis() < connectionTime + 20000) {//Give client time to respond
            try {
                socket.receive(packet);
                if (correctClient(packet)) {
                    if (isStartMessage(packet)) {
                        startNewGame();
                        return;
                    } else {
                        sendMessage("WRONG MESSAGE", packet);
                    }
                } else {
                    sendMessage("BUSY", packet);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void startNewGame() {
        String s = "READY " + word.length();
        byte[] data = s.getBytes();
        DatagramPacket response = new DatagramPacket(data, data.length);
        response.setAddress(currentClient.getIp());
        response.setPort(currentClient.getPort());
        try {
            socket.send(response);
            gameLoop();
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Something happened");
            terminateSession();
        }
    }

    private void gameLoop() {
        long lastMessageTime = System.currentTimeMillis();
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        //** GAME LOOP **//
        while (System.currentTimeMillis() < lastMessageTime + 30000) {//30 seconds timeout value
            try {
                socket.receive(packet);
                lastMessageTime = System.currentTimeMillis();

                if (correctClient(packet)) {
                    String message = new String(packet.getData());
                    if (messageFormCorrect(message)) {
                        currentClient.setNrAttempts(currentClient.getNrAttempts() + 1);
                        compareWithWord(message);
                        sendMessage(wordStatus, packet);

                        if (checkComplete()) {
                            sendMessage("Thank you for playing!", packet);
                            terminateSession();
                            return;
                        } else if (outOfAttempts()) {
                            sendMessage("No more tries, thank you for playing!", packet);
                            terminateSession();
                            return;
                        }

                    } else {
                        sendMessage("MAX 1 CHARACTER, A-Z", packet);
                    }
                } else {
                    sendMessage("BUSY", packet);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //** TIMES UP, TERMINATE SESSION **//
        packet.setAddress(currentClient.getIp());
        packet.setPort(currentClient.getPort());
        sendMessage("Times up! Terminating your session.", packet);
        terminateSession();
    }

    private Boolean outOfAttempts() {
        if (currentClient.getNrAttempts() > maxAttempts) {
            return true;
        }
        return false;
    }

    private Boolean checkComplete() {
        if (wordStatus.contains("*")) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean correctClient(DatagramPacket packet) {
        if (currentClient.getIp() != packet.getAddress() || currentClient.getPort() != packet.getPort()) { //if wrong client

            return false;
        } else {
            return true;
        }
    }

    private Boolean isStartMessage(DatagramPacket packet) {
        String message = new String(packet.getData());
        if (message.equals("START")) {
            return true;
        } else {
            return false;
        }
    }

    private void sendMessage(String message, DatagramPacket packet) {

        byte[] data = message.getBytes();
        DatagramPacket response = new DatagramPacket(data, data.length);
        response.setAddress(packet.getAddress());
        response.setPort(packet.getPort());
        try {
            socket.send(response);
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void compareWithWord(String letter) {
        if (word.contains(letter)) {
            wordStatus = wordStatus.replace('*', letter.charAt(0));
        }
    }

    private Boolean messageFormCorrect(String message) {
        if (!(message.length() > 1 || message.length() < 1)) {
            if (!((message.charAt(0) >= 'a' && message.charAt(0) <= 'z')
                    || message.charAt(0) >= 'A' && message.charAt(0) <= 'Z')) {
                return true;
            }
        }
        return false;
    }

    private void terminateSession() {
        currentClient = null;
        wordStatus = "";
        setupWordStatus();
    }
}
