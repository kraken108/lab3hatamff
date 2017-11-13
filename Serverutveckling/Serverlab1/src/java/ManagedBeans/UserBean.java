/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.UserHandler;
import Model.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

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
    private List<User> users;

    public List<User> getUsers(){
        //TODO:
        //Get users from userhandler instead of the hardcoded list from here
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }
    
    public Boolean isLoggedInUser(String username){
        if(this.username.equals(username)){
            return true;
        }else{
            return false;
        }
    }
    public UserBean(){
        users = new ArrayList<>();
        users.add(new User("Jubbe"));
        users.add(new User("Michael"));
        users.add(new User("Alex"));
        users.add(new User("Ponny"));
    }
    
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    
    public void setUsername(String name) throws Exception {
        this.username = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }

    public String redirectLoginPage() {
        return "login.xhtml";
    }
    
    
    public Boolean getIsLoggedIn(){
        return authorized;
    }
    
    
    public String getStatusMessage(){
        return statusMessage;
    }
    
    public String logout(){
        username = "";
        password = "";
        authorized = false;
        return "login.xhtml";
    }
    
    public String doLogin(){
        statusMessage = "";
        userHelper = new UserHandler();
        if(userHelper.login(username, password)){
            authorized = true;
            return "index.xhtml";
        }else{
            statusMessage = "Wrong password or username";
            return "login.xhtml";
        }
    }
    
    public void createAccount(){
        statusMessage = "";
        userHelper = new UserHandler();
        try{
            if(userHelper.createUser(username, password)){
                statusMessage = "Successfully created account!";
            }
            else{
                statusMessage = "Didn't create account, i tink kanske redan finns jåå";
            }
        }catch(Exception e){
            statusMessage = e.toString();
        }
    }
    
    public String nameAlreadyExists(){
        if(username.equals("")){
            return "";
        }else{
            return "Username is available";
            
        }
    }
}
