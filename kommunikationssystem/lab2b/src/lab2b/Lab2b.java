/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b;

import java.io.IOException;
import static java.lang.System.exit;

/**
 *
 * @author Anders
 */
public class Lab2b {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            NewSkype newSkype = new NewSkype();
            newSkype.start();
        }catch(IOException e){
            System.out.println("Something went wrong :PPPP");
            exit(1);
        }
    }
}
