/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Rest.PostRestClient;
import ViewModel.Post;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jakob
 */
@ManagedBean
@SessionScoped
public class LogBean {
    
    private String user;
    
    public LogBean() {
    }
    
    public List<ViewModel.Post> getPostsByName(String username) throws Exception {
        PostRestClient client = new PostRestClient();
        
        GenericType<List<Post>> gType = new GenericType<List<Post>>() {
        };
        List<Post> list = (List<Post>) client.getPostsByUser_JSON(gType, username);
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }
    
    public void setUser(String user) {
        if (user != null && !user.equals("")) {
            this.user = user;
        }
    }
    
    public String getUser() {
        return this.user;
    }
}
