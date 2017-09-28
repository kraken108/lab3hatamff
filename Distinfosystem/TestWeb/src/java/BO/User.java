/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class User {

    private String name;
    private ArrayList<Rights> rights;
    
    public User(String name, ArrayList<Rights> rights){
        this.name = name;
        this.rights = new ArrayList<>();
        this.rights = rights;
    }
    
    public Boolean isAdministrator(){
        return rights.contains(Rights.ADMINISTRATOR);
    }
    
    public Boolean isCustomer(){
        return rights.contains(Rights.CUSTOMER);
    }
    
    public Boolean isStock(){
        return rights.contains(Rights.STOCK);
    }
    
    public String getName(){
        return name;
    }
    
}
