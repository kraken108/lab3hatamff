/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.States;
/**
 *
 * @author Michael
 */
public class CallingIn extends State{
    
 
    public State ACK(){
        
        return new InCall();
    }

    @Override
    public String getStatename() {
        return ("CallingIn");
    }
    
    
    
    
}
