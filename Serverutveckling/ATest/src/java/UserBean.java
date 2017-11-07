/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    String str = null;
    UserHelper userHelper;

    public void setUsername(String name) throws Exception {
        userHelper = new UserHelper();
        userHelper.addNewUser(name);
        this.str = name;
    }

    public String getUsername() {
        return str;
    }

    public String getResponse() {
        return str;
    }
}
