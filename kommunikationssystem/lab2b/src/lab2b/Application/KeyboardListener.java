/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Application;

import java.util.Scanner;

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
            newSkype.handleInput(input);
        }

    }

}
