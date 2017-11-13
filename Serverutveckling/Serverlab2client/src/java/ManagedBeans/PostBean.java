/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;


import Model.Post;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class PostBean implements Serializable{
    private String newPost;
    private String statusMessage;

    
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    
    
    public void setUserBean(UserBean userBean){
        this.userBean = userBean;
    }
    
    @ManagedProperty(value="#{logBean}")
    private LogBean logBean;
    
    public void setLogBean(LogBean logBean){
        this.logBean = logBean;
    }
    
    public PostBean(){
;
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
    
    public void resetStatusMessage(){
        statusMessage = "";
    }
    
    public String createPost(){
        /*statusMessage = "";
        Post p = new Post(newPost,new Date(),userBean.getUsername());
        logBean.addPost(p);
        if(postHandler.createNewPost(newPost,userBean.getUsername())){
            newPost = "";
            statusMessage = "Post was successful! user: " + userBean.getUsername();
            return "profile?faces-redirect=true&user="+userBean.getUsername();
        }else{
            newPost = "";
            statusMessage = "Couldnt create post :/";
            return "profile?faces-redirect=true&user="+userBean.getUsername();
        }*/
        return "";
    }
    
}
