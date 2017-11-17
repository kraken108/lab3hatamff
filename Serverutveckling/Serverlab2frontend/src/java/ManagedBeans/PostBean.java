/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Rest.PostRestClient;
import ViewModel.Post;
import ViewModel.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class PostBean implements Serializable {

    private String newPost;
    private String statusMessage;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public PostBean() {
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

    public void resetStatusMessage() {
        statusMessage = "";
    }

    public String createPost() throws Exception {
        statusMessage = "";

        if (!newPost.equals("")) {
            PostRestClient client = new PostRestClient();
            Post p = new Post();
            p.setMessage(newPost);
            User u = new User();
            u.setUsername(userBean.getUsername());
            p.setUser(u);
            if(client.createNewPost_JSON(p).getStatusInfo().toString().equals("Created")){
                statusMessage = "Success!";
            }else{
                statusMessage = "An error occurred :< ";
            }
        }
        return "profile?faces-redirect=true&user="+userBean.getUsername();
    }

}
