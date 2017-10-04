/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.Application;

import java.io.IOException;
import static java.lang.System.exit;

/**
 *
 * @author Anders
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            NewSkype newSkype = null;
            if(args.length > 1){
                newSkype = new NewSkype(Integer.parseInt(args[0]),args[1]);
            }else{
                newSkype = new NewSkype(Integer.parseInt(args[0]),"");
            }
            newSkype.start();
            
        }catch(IOException e){
            System.out.println("Something went wrong :PPPP");
            exit(1);
        }
    }
}
