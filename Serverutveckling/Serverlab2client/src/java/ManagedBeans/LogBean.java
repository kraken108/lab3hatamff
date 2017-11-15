/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Model.Post;
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

    public LogBean() {

    }

    public List<Post> getPostsByName(String username){
        //TODO:
        //Get the posts from loghandler instead of these hardcoded posts
        return null;
    }
/*
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    public void addPost(Post p){
        posts.add(0, p);
    }*/
}
