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
    
    private Connection connection;
    private String passWord;
    private String userName;
    private DBRegister dbRegister;
    
    
    public Register(String passWord, String userName) throws NamingException, SQLException{
        
        MysqlManager mysqlManager = new MysqlManager();
        connection = mysqlManager.getConnection();
        dbRegister=new DBRegister();
        this.userName=userName;
        this.passWord=passWord;
    }     
    
    public Register() throws NamingException, SQLException{                
        
        MysqlManager mysqlManager = new MysqlManager();
        connection = mysqlManager.getConnection();
        dbRegister=new DBRegister();       
    }
    
    public void insertUser(String userName, String passWord) throws SQLException{
            
       dbRegister.insertUsers(connection, userName, passWord);
       
    }
    
    public boolean checkPassWord(String text){
        
        if(!(checkCorrectFormat(text)))
            return false;
               
        return true;
    }
    
    
    public boolean checkUserName(String text){
        
        if(!(checkForCharacters(text)))
            return false;
        
        if(!(checkCorrectFormat(text)))
            return false;
               
        return true;
    }
    
    private boolean checkForCharacters(String text){
        
        char testChar[] = text.toCharArray();
                
        for (char c : testChar){
            if(!Character.isAlphabetic(c))
                return false;
        }  
        return true;
    }
    
    private boolean checkCorrectFormat(String text){
        
        if(!((text.length()>1) && (text.length() < 10)))
            return false;
        
        if(text.contains(" "))
            return false;
        
        return true;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getPassWord() {
        return passWord;
    }
   
    @Override
    public String toString(){
        return getUserName() + " " + getPassWord();
    }   

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;

    }   
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
