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
public class InCall extends State{
    
    
   
    public State requestHangUpBye(){
        return new HangingUp();
    }    
    
    public State error(){
        return new Idle();
    }
    
    public State byeOK(){
        return new Idle();
    }    

    @Override
    public String getStatename() {
        return "InCall";
    }
}


