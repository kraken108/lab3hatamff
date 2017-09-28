/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BO.Item;
import DBManager.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Michael
 */
public class Register {
    
    private String userName;
    private String passWord;
    
    public Register(String userName, String passWord){
        
        this.userName=userName;
        this.passWord=passWord;
    }   
    
    public Register(){                
        this.userName="";
        this.passWord="1234";               
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }   
    
    @Override
    public String toString(){
        return getUserName() + " " + getPassWord();
    }
    
    
}
