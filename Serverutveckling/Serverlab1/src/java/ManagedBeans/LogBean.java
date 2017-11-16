/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.LogHandler;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class LogBean {


    private String user;

    public LogBean() {}

    public List<ViewModel.Post> getPostsByName(String username) {
        LogHandler ph = new LogHandler();
        List<ViewModel.Post> list = ph.getPostsByUser(user);
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public void setUser(String user) {
        if (user != null && !user.equals("")) {
            this.user = user;
        }
    }

    public String getUser(){
        return this.user;
    }
}
