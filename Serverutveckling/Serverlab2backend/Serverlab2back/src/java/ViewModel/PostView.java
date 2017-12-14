/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import microservices.*;

/**
 *
 * @author Jakob
 */
public class PostView {
    private String message;
    private UserView user;
    private String date;
    private Long id;
    
    public PostView(){
        
    }
    
    public PostView(String date,String message, UserView user, Long id) {
        this.message = message;
        this.user = user;
        this.date = date;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
