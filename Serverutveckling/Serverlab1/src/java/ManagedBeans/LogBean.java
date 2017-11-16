/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import BO.LogHandler;
import BO.PostHandler;
import Model.Post;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
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

    private List<Post> posts;
    private String user;

    public LogBean() {
        User tempUser = new User("Jubbe");
        posts = new ArrayList<Post>();
        posts.add(new Post("Meddelande 1", new Date(), tempUser));
        posts.add(new Post("Meddelande 2", new Date(), tempUser));
        posts.add(new Post("Meddelande 3", new Date(), tempUser));

    }

    public List<Post> getPostsByName(String username) {
        //TODO:
        //Get the posts from loghandler instead of these hardcoded posts
        LogHandler ph = new LogHandler();
        return ph.getPostsByUser(user);
        //return posts;
    }

    public void setUser(String user) {
        if (user != null && !user.equals("")) {
            this.user = user;
        }
    }

    public String getUser(){
        return this.user;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post p) {
        posts.add(0, p);
    }
}
