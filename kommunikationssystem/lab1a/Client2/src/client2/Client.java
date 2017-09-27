/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client2;

/**
 *
 * @author micke1
 */
import java.util.Scanner;
import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author micke1
 */
public class Client {

    private InetAddress ipAddress;
    private int portNumber;
    private DatagramSocket ds;
    private DatagramPacket dp;

    public Client(String theIPAddress, int portNumber) throws SocketException, UnknownHostException {

        try {
            ds = new DatagramSocket();
            ds.setSoTimeout(20000);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw (ex);
        }

        this.ds = ds;
        this.dp = dp;

        try {
            this.ipAddress = InetAddress.getByName(theIPAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw (ex);
        }
        this.portNumber = portNumber;

    }

    public int start() throws IOException {

        String stringToSend;

        System.out.println("Type HELLO to start");
        Scanner reader = new Scanner(System.in);
        stringToSend = reader.nextLine();

        send(stringToSend);

        if (receive() == -1) {
            return -1;
        }

        System.out.println("Type START to start");
        stringToSend = reader.nextLine();

        send(stringToSend);

        if (receive() == -1) {
            return -1;
        }

        gameLoop();

        return 0;
    }

    private int gameLoop() throws IOException {

        Scanner reader = new Scanner(System.in);

        for (;;) {

            System.out.println("Guess a letter: (GUESS + letter) ");
            send(reader.nextLine());
            if (receive() == -1) {
                return -1;
            }
        }
    }

    private void send(String wordToSend) throws IOException {

        byte[] byteOne = (wordToSend.getBytes());
        DatagramPacket dpOne = new DatagramPacket(byteOne, byteOne.length, ipAddress, portNumber);

        try {
            //  System.out.println(wordToSend);
            ds.send(dpOne);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw (ex);
        }
    }

    private int receive() {

        String errorCheck;
        byte[] byteTwo = new byte[1024];
        DatagramPacket dpTwo = new DatagramPacket(byteTwo, byteTwo.length);

        try {
            ds.receive(dpTwo);
        } catch (Exception e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            ds.close();
            return -1;
        }

        errorCheck = new String(dpTwo.getData());

        print(dpTwo);

        if (errorCheck.contains("NO MORE")) {

            System.out.println("Shutting down...");
            return -1;
        }

        if (errorCheck.contains("WIN")) {

            System.out.println("Shutting down...");
            return -1;
        }

        if (errorCheck.contains("TIMEOUT")) {

            System.out.println("Shutting down...");
            return -1;
        }

        if (errorCheck.contains("BUSY")) {

            System.out.println("Shutting down...");
            return -1;
        }

        if (errorCheck.startsWith("ERROR")) {
            System.out.println("Received error, shutting down...");
            return -1;
        }

        return 0;
    }

    private void print(DatagramPacket dp) {

        String str = new String(dp.getData());

        System.out.println("Response from server: " + removeZeros(str));

    }

    private String removeZeros(String str) {

        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\0') {
                break;
            } else {
                s += str.charAt(i);
            }
        }
        return s;
        //test
    }
}
