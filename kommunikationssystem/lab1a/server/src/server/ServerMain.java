/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import static java.lang.System.exit;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class ServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        for(int i = 0; i < args[0].length(); i++){//checking letters
            if(!((args[0].charAt(i) >= 'a' && args[0].charAt(i) <= 'z') 
                    || args[0].charAt(i) >= 'A' && args[0].charAt(i) <= 'Z')){
                System.out.println("can only contain letters... terminating");
                exit(1);
            }
        }
        
        try {
            ServerSocket serverSocket = new ServerSocket(args[0].toUpperCase());
            System.out.println("Starting server with word: " + args[0].toUpperCase());
            serverSocket.startServer();
        } catch (SocketException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            exit(1);
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            exit(1);
        }finally{
            exit(0);
        }
    }
    
}
