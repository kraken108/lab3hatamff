package Facade;


import DBManager.*;
import java.sql.SQLException;
import javax.naming.NamingException;


/**
 *  Used to register new users towards the database.
 */
public class Register {
    

    private String passWord;
    private String userName;
    private DBRegister dbRegister;
    
    /**
     * Construct a new Register object with parameters the user types in.
     * @param passWord
     * @param userName
     * @throws NamingException
     * @throws SQLException 
     */
    public Register(String passWord, String userName) throws NamingException, SQLException{

        dbRegister=new DBRegister();
        this.userName=userName;
        this.passWord=passWord;
    }     
    
    /**
     * Creates a new Register object.
     * @throws NamingException
     * @throws SQLException 
     */
    public Register() throws NamingException, SQLException{                

        dbRegister=new DBRegister();       
    }
    
    /**
     * Inserts a new user with information from the end user.
     * @param userName
     * @param passWord
     * @throws SQLException 
     */
    public void insertUser(String userName, String passWord) throws SQLException, NamingException{
            
       dbRegister.insertUsers(userName, passWord);
       
    }
    
    /**
     * Check if password is correct format.
     * @param text
     * @return 
     */
    public boolean checkPassWord(String text){
        
        if(!(checkCorrectFormat(text)))
            return false;
               
        return true;
    }
    
    
    /**
     * Checks if username is correct format.
     * @param text
     * @return 
     */
    public boolean checkUserName(String text){
        
        if(!(checkForCharacters(text)))
            return false;
        
        if(!(checkCorrectFormat(text)))
            return false;
               
        return true;
    }
    
    /**
     * Checks if username contains anything else than alphabetical letters.
     * @param text
     * @return 
     */
    private boolean checkForCharacters(String text){
        
        char testChar[] = text.toCharArray();
                
        for (char c : testChar){
            if(!Character.isAlphabetic(c))
                return false;
        }  
        return true;
    }
    
    /**
     * Cjecls of the text is correct format.
     * @param text
     * @return 
     */
    private boolean checkCorrectFormat(String text){
        
        if(!((text.length()>1) && (text.length() < 10)))
            return false;
        
        if(text.contains(" "))
            return false;
        
        return true;
    }
    
    /**
     * Returns the username.
     * @return 
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Returns the password.
     * @return 
     */
    public String getPassWord() {
        return passWord;
    }
   
    /**
     * Constructs a string with the username and password.
     * @return 
     */
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

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
