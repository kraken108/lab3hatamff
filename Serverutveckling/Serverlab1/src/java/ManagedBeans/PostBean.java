/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.PostHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class PostBean {
    private String newPost;
    private String statusMessage;
    private PostHandler postHandler;
    
    public PostBean(){
        postHandler = new PostHandler();
    }
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public String getNewPost() {
        return newPost;
    }

    public void setNewPost(String newPost) {
        this.newPost = newPost;
    }
    
    public String createPost(){
        statusMessage = "Post successful!";
        String toReturn = postHandler.createNewPost(newPost);
        newPost = "";
        return toReturn;
    }
    
}
