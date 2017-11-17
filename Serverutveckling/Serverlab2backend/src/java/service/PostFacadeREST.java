/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BO.LogHandler;
import BO.PostHandler;
import Model.Post;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jakob
 */
@Path("postfacade")
public class PostFacadeREST extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "Serverlab2backendPU")
    private EntityManager em;

    public PostFacadeREST() {
        super(Post.class);
    }

    @POST
    @Path("createnewpost")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createNewPost(Post entity)  {
        try {
            PostHandler ph = new PostHandler();
            if (ph.createNewPost(entity.getMessage(), entity.getUser().getUsername())) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            try {
                PrintWriter writer = new PrintWriter("D:/Loggfil/logfil.txt","UTF-8");
                writer.println(e.toString());
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                //Logger.getLogger(PostFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> getPostsByUser(@PathParam("user") String user) {
        LogHandler lh = new LogHandler();
        List<Post> list = (List<Post>) lh.getPostsByUser(user);
        return list;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
