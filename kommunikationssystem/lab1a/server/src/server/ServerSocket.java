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
    private String word;
    private char[] wordStatus;
    private int maxAttempts;
    private Client currentClient;

    public ServerSocket(String word) throws SocketException {
        try {
            socket = new DatagramSocket(1234);
        } catch (SocketException se) {
            throw (se);
        }
        running = false;
        this.word = word;
        maxAttempts = word.length() * 3;
        setupWordStatus();
    }

    public void startServer() throws IOException {
        running = true;
        acceptNewConnection();
    }

    private void acceptNewConnection() throws IOException {
        byte[] data = new byte[1024];

        try {
            while (running) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String message = new String(packet.getData());
                if (removeZeros(message).equals("HELLO")) {
                    initiateNewSession(packet);
                } else {
                    sendMessage("ERROR", packet);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            socket.close();
        }
    }

    private void initiateNewSession(DatagramPacket packet) throws IOException {
        sendMessage("OK", packet);
        System.out.println("New session!");
        currentClient = new Client(packet.getAddress(), packet.getPort());
        long connectionTime = System.currentTimeMillis();

        int timeout = 10000;
        while (true) {

            socket.receive(packet);

            if (correctClient(packet)) { //if correct client
                if (System.currentTimeMillis() < connectionTime + timeout) {
                    // connectionTime = System.currentTimeMillis(); //reset timeout
                    if (isStartMessage(packet)) {
                        startNewGame();
                        return;
                    } else {
                        sendMessage("ERROR! Disconnected", packet);
                        terminateSession();
                        return;
                    }
                } else {
                    sendTimeout();
                    terminateSession();
                    return;
                }

            } else //if other client
            {
                if (isHelloMessage(packet)) {
                    if (System.currentTimeMillis() < connectionTime + timeout) {
                        sendMessage("BUSY", packet);
                    } else { //timeout, initiate new session
                        sendTimeout();
                        terminateSession();
                        initiateNewSession(packet);
                        return;
                    }
                } else {
                    sendMessage("ERROR", packet);
                }
            }
        }
    }

    private void startNewGame() throws IOException {
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
            terminateSession();
            throw(ex);
        }
    }

    private String getWordStatus() {
        String s = "";
        for (int i = 0; i < word.length(); i++) {
            s += wordStatus[i];
        }
        return s;
    }

    private void gameLoop() throws IOException {
        long lastMessageTime = System.currentTimeMillis();

        int timeout = 20000;
        //** GAME LOOP **//
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                throw (ex);
            }

            if (correctClient(packet)) { //if correct client
                if (System.currentTimeMillis() < lastMessageTime + timeout) {
                    lastMessageTime = System.currentTimeMillis();
                    String message = new String(packet.getData());
                    message = removeZeros(message);
                    if (messageFormCorrect(message)) { //if "GUESS 'x'"
                        currentClient.setNrAttempts(currentClient.getNrAttempts() + 1);
                        compareWithWord(getLetter(message));

                        if (checkComplete()) {
                            sendMessage(getWordStatus() + "\nYou win! Thank you for playing.", packet);
                            terminateSession();
                            return;
                        } else if (outOfAttempts()) {
                            sendMessage(getWordStatus() + "\nNo more tries :( thank you for playing!", packet);
                            terminateSession();
                            return;
                        } else {
                            sendMessage(getWordStatus(), packet);
                        }

                    } else {
                        sendMessage("ERROR! Disconnected", packet);
                        terminateSession();
                        return;
                    }
                } else { //TIMEOUT
                    sendTimeout();
                    terminateSession();
                    return;
                }

            } else//if another client
            {
                if (isHelloMessage(packet)) {
                    if (System.currentTimeMillis() < lastMessageTime + timeout) {
                        sendMessage("BUSY", packet);
                    } else { //timeout!
                        sendTimeout();
                        terminateSession();
                        initiateNewSession(packet);
                        return;
                    }
                } else {
                    sendMessage("ERROR", packet);
                }
            }
        }
    }

    private String getLetter(String message) {
        message = removeZeros(message);
        String[] result = message.split(" ");
        return result[1];
    }

    private void sendTimeout() {
        byte[] d = "TIMEOUT".getBytes();
        DatagramPacket p = new DatagramPacket(d, d.length);
        p.setAddress(currentClient.getIp());
        p.setPort(currentClient.getPort());
        try {
            socket.send(p);
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupWordStatus() {
        wordStatus = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            wordStatus[i] = '*';
        }
    }

    private Boolean outOfAttempts() {
        if (currentClient.getNrAttempts() > maxAttempts) {
            return true;
        }
        return false;
    }

    private Boolean checkComplete() {
        for (int i = 0; i < word.length(); i++) {
            if (wordStatus[i] == '*') {
                return false;
            }
        }
        return true;
    }

    private Boolean correctClient(DatagramPacket packet) {
        if (!currentClient.getIp().getHostAddress().equals(packet.getAddress().getHostAddress()) || currentClient.getPort() != packet.getPort()) { //if wrong client

            return false;
        } else {
            return true;
        }
    }

    private Boolean isStartMessage(DatagramPacket packet) {
        String message = new String(packet.getData());
        if (removeZeros(message).equals("START")) {
            return true;
        } else {
            return false;
        }
    }

    private void sendMessage(String message, DatagramPacket packet) throws IOException {

        byte[] data = message.getBytes();
        DatagramPacket response = new DatagramPacket(data, data.length);
        response.setAddress(packet.getAddress());
        response.setPort(packet.getPort());
        try {
            socket.send(response);
        } catch (IOException ex) {
            throw(ex);
        }
    }

    private void compareWithWord(String letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                wordStatus[i] = letter.charAt(0);
            }
        }
    }

    private Boolean messageFormCorrect(String message) {

        System.out.println(message);
        String[] strings = message.split(" ");
        if (strings == null) {
            System.out.println("null");
            return false;
        }
        if (strings.length < 2) {
            System.out.println("ej 2");
            return false;
        }
        if (!strings[0].equals("GUESS")) {
            System.out.println("ej guess");
            return false;
        }
        if ((strings[1].length() != 1)) {
            System.out.println("ej 1");
            return false;
        }

        System.out.println("Rätt format: " + message);
        return true;
    }

    private void terminateSession() {
        currentClient = null;
        for (int i = 0; i < word.length(); i++) {
            wordStatus[i] = '*';
        }
        setupWordStatus();
    }

    private Boolean isHelloMessage(DatagramPacket p) {
        String s = new String(p.getData());

        if (removeZeros(s).equals("HELLO")) {
            return true;
        } else {
            return false;
        }
    }

    private String removeZeros(String message) {
        String s = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '\0') {
                break;
            } else if (message.charAt(i) == '\n') {
                break;
            } else {
                s += message.charAt(i);
            }
        }
        return s;
    }
}
