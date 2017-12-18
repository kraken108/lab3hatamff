/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.InetAddress;

/**
 *
 * @author Jakob
 */
public class ConnectedClient {
    
    private int nrAttempts;
    private InetAddress ip;
    private int port;
    
    public ConnectedClient(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
        nrAttempts = 0;
    }

    /**
     * @return the ip
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the nrAttempts
     */
    public int getNrAttempts() {
        return nrAttempts;
    }

    /**
     * @param nrAttempts the nrAttempts to set
     */
    public void setNrAttempts(int nrAttempts) {
        this.nrAttempts = nrAttempts;
    }
}
