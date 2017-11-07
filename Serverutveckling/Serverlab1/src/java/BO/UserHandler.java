/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

/**
 *
 * @author Jakob
 */
public class UserHandler {
    
    
    public UserHandler(){}
    
    
    public Boolean login(String username, String password){
        //TODO:
        //Check database and return true if user and pw is correct
        return true;
    }
    
    public Boolean createUser(String username, String password) throws Exception{
        //TODO:
        //Create new user in database if username doesnt already exists.
        //Throw exception if something happens
        return true;
    }
}
