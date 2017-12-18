package Facade;

import Model.User;
import DBManager.*;
import Model.Rights;
import ViewModel.RightsInfo;
import ViewModel.UserInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * Login is used to communicate with the classes that retrieves login information.
 */
public class Login {


    /**
     * Try to login by providing a username and a password.
     * @param username
     * @param password
     * @return String explaining the result, if it's successful or not.
     * @throws SQLException
     * @throws NamingException 
     */
    public String tryLogin(String username, String password) throws SQLException, NamingException {

        try {
            DBLogin dbLogin = new DBLogin();
            Boolean success = dbLogin.tryLogin(username,password);

            if (success) {
                return "SUCCESSFUL";
            }else{
                return "UNSUCCESSFUL";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        } catch (NamingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            throw(ex);
        }
    }
    
    /**
     * getUserInfo takes a username and returns a User object with all information about the user.
     * @param username
     * @return User object
     * @throws SQLException
     * @throws NamingException 
     */
    public UserInfo getUserInfo(String username) throws SQLException, NamingException{
        try{
            DBLogin dbLogin = new DBLogin();
            User user = dbLogin.getUserInfo(username);
            ArrayList<RightsInfo> rightsInfo = new ArrayList<>();
            for(Rights r : user.getRights()){
                switch(r){
                    case ADMINISTRATOR: rightsInfo.add(RightsInfo.ADMINISTRATOR);
                    case CUSTOMER: rightsInfo.add(RightsInfo.CUSTOMER);
                    case STOCK: rightsInfo.add(RightsInfo.STOCK);
                    default: break;
                }
            }
            
            UserInfo userInfo = new UserInfo(user.getName(),rightsInfo);
            
            return userInfo;
            
        }catch(SQLException ex){
            throw(ex);
        } catch (NamingException ex) {
            throw(ex);
        }
    }
}
