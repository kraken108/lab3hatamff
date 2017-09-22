/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2b.StateMachine;
/**
 *
 * @author Michael
 */
public class CallingOut extends StateUncallable{
    
    public State Busy(){
        return new Idle();
    }
    
    public State troAck(){
        
        return new InCall();
    }  

    @Override
    public String getStatename() {
        return "CallingOut";
    }
    
}
