/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktakurvangameserver;

import java.net.Socket;

/**
 *
 * @author Jakob
 */
public class Player {
    private boolean connected;
    private Socket socket;
    private String email;
    
    public Player(String email){
        this.email = email;
        this.socket = null;
        this.connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
