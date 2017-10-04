/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Controller;

import java.net.InetAddress;

/**
 *
 * @author Jakob
 */
public class User {
    private InetAddress ip;
    private int port;
    
    public User(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }

    /**
     * @return the ip
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    
    
}
