/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.UserHandler;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    UserHandler userHelper;
    private String username;
    private String password;
    private Boolean authorized;
    private String statusMessage;
    private String password2;
    
    
    public List<ViewModel.User> getUsers() {
        return userHelper.getAllUsers();
    }

    public Boolean isLoggedInUser(String username) {
        if (this.username.equals(username)) {
            return true;
        } else {
            return false;
        }
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    
    public UserBean(){
    }

    
    
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setUsername(String name){
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String redirectLoginPage() {
        return "login.xhtml";
    }

    public Boolean getIsLoggedIn() {
        return authorized;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String logout() {
        username = "";
        password = "";
        authorized = false;
        return "login.xhtml";
    }

    public String doLogin() {
        statusMessage = "";
        userHelper = new UserHandler();
        if (userHelper.login(username, password)) {
            authorized = true;
            return "index.xhtml";
        } else {
            statusMessage = "Wrong password or username";
            return "login.xhtml";
        }
    }

    public void createAccount() {
        statusMessage = "";
        userHelper = new UserHandler();
        
        if(username.length() < 3 || username.length() > 15){
            statusMessage = "Username length must be between 3 and 15 characters";
            return;
        }
        
        if(!password.equals(password2)){
            statusMessage = "Passwords doesn't match!";
            return;
        }

        if((password.length() < 5)  || (password.length() > 20)){
            statusMessage = "Password length must be between 5 and 20 characters";
            return;
        }
        
        try {
            statusMessage = userHelper.createUser(username,password);
            username = "";
        } catch (Exception e) {
            statusMessage = e.toString();
        }
    }

    public String nameAlreadyExists() {
        userHelper = new UserHandler();
        if (username != null && !username.equals("")) {
            if (userHelper.checkIfAlreadyExists(username) != null) {
                return "Username already exists!";
            } else {
                return "Username is available";
            }
        }else{
            return "";
        }

    }

}