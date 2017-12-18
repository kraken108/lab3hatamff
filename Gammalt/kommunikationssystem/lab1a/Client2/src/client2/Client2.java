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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author micke1
 */
public class Client2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        int arg;
        arg = Integer.parseInt(args[1]);

        try {
            Client c = new Client(args[0], arg);
            if (c.start() == -1) {
                System.exit(0);
                System.out.println("KRASH!!!!!!!!");
            }
        } catch (SocketException ex) {
            System.out.println(ex);
            System.exit(1);
        }catch(UnknownHostException ex){
            System.out.println(ex);
            System.exit(1);
        }catch(IOException ex){
            System.out.println(ex);
            System.exit(1);
        }

        // TODO code application logic here
    }

}
