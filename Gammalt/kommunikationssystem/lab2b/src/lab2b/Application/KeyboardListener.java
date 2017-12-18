/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Application;

import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class KeyboardListener implements Runnable {

    private Scanner scanner;
    private NewSkype newSkype;

    public KeyboardListener(NewSkype newSkype) {
        scanner = new Scanner(System.in);
        this.newSkype = newSkype;
    }

    @Override
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            try {
                newSkype.handleInput(input);  //hanterar meddelandet beroende på användarens input
            } catch (UnknownHostException ex) {
                System.out.println("Couldn't find host: " + ex);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

}
