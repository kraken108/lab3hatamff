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
public class HangingUp extends StateUncallable{
    
    public State hangUp(){
        
        return new Idle();
    }

    @Override
    public String getStatename() {
        return "HangingUp";
    }
    
}