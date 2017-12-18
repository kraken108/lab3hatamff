/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class UserInfo {
    private String name;
    private ArrayList<RightsInfo> rights;
    
    /**
     * Constructs a new user.
     * @param name
     * @param rights 
     */
    public UserInfo(String name, ArrayList<RightsInfo> rights){
        this.name = name;
        this.rights = new ArrayList<>();
        this.rights = rights;
    }
    
    /**
     * Returns true if the user has the administration rights.
     * @return 
     */
    public Boolean isAdministrator(){
        return rights.contains(RightsInfo.ADMINISTRATOR);
    }
    
    /**
     * Returns true if the user has customer rights.
     * @return 
     */
    public Boolean isCustomer(){
        return rights.contains(RightsInfo.CUSTOMER);
    }
    
    /**
     * Returns true if the user has stock staff rights.
     * @return 
     */
    public Boolean isStock(){
        return rights.contains(RightsInfo.STOCK);
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
}
