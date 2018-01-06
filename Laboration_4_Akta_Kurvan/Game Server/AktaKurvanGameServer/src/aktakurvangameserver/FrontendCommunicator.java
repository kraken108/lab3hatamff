/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktakurvangameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jakob
 */
public class FrontendCommunicator implements Runnable {

    private ServerSocket serverSocket;
    private final int port = 8212;
    private BufferedReader in;
    private PrintWriter out;

    public FrontendCommunicator() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket newConnection = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}
