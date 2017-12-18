/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 * Data represenation of a user from the database.
 */
public class User {

    private String name;
    private ArrayList<Rights> rights;
    
    /**
     * Constructs a new user.
     * @param name
     * @param rights 
     */
    public User(String name, ArrayList<Rights> rights){
        this.name = name;
        this.rights = new ArrayList<>();
        this.rights = rights;
    }
    
    /**
     * Returns true if the user has the administration rights.
     * @return 
     */
    public Boolean isAdministrator(){
        return getRights().contains(Rights.ADMINISTRATOR);
    }
    
    /**
     * Returns true if the user has customer rights.
     * @return 
     */
    public Boolean isCustomer(){
        return getRights().contains(Rights.CUSTOMER);
    }
    
    /**
     * Returns true if the user has stock staff rights.
     * @return 
     */
    public Boolean isStock(){
        return getRights().contains(Rights.STOCK);
    }
    
    /**
     * Returns the name of the user.
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * Constructs a string with the users rights.
     * @return 
     */
    public String getRightsString(){
        String s = "";
        if(isAdministrator()){
            s+="Administrator, ";
        }
        if(isCustomer()){
            s+="Customer, ";
        }
        if(isStock()){
            s+="Stock, ";
        }
        
        return s;
    }

    /**
     * @return the rights
     */
    public ArrayList<Rights> getRights() {
        return rights;
    }
    
    
}
