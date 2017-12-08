/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Rest.UserRestClient;
import ViewModel.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private String username;
    private String password;
    private Boolean authorized;
    private String statusMessage;
    private String password2;

    public List<User> getUsers() {
        UserRestClient client = new UserRestClient();
        GenericType<List<User>> gType = new GenericType<List<User>>() {
        };
        
        return (List<User>) client.findAll_JSON(gType);
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

    public UserBean() {
        authorized = false;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setUsername(String name) {
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
        UserRestClient client = new UserRestClient();
        User login = new User();
        login.setPassword(password);
        login.setUsername(username);
        if (client.login_JSON(login).getStatusInfo().toString().equals("Found")) {
            authorized = true;
            return "index.xhtml";
        } else {
            statusMessage = "Wrong password or username";
            return "login.xhtml";
        }

        
    }

    public void createAccount() {
        statusMessage = "";

        if (username.length() < 3 || username.length() > 15) {
            statusMessage = "Username length must be between 3 and 15 characters";
            return;
        }

        if (!password.equals(password2)) {
            statusMessage = "Passwords doesn't match!";
            return;
        }

        if ((password.length() < 5) || (password.length() > 20)) {
            statusMessage = "Password length must be between 5 and 20 characters";
            return;
        }
        try {
            UserRestClient client = new UserRestClient();
            User u = new User();
            u.setPassword(password);
            u.setUsername(username);
            

            statusMessage = client.createUser_JSON(u).getStatusInfo().toString();
            username = "";

        } catch (ClientErrorException e) {
            statusMessage = e.toString();
        }

    }

    public String nameAlreadyExists() {
        if (username != null && !username.equals("")) {
            try {
                UserRestClient client = new UserRestClient();
                User u = new User();
                u.setPassword(password);
                u.setUsername(username);
                
                return client.checkAlreadyExists_JSON(u).getStatusInfo().toString();
               /* if (client.checkAlreadyExists_JSON(u).getStatusInfo().toString().equals("Found")) {
                    return "Username already exists";
                } else {
                    return "Username is available";
                }*/
            } catch (ClientErrorException e) {
                statusMessage = "Error";
                return "";
            }
        }
        return "";

    }

}
