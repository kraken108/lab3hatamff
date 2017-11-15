/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BO.UserHandler;
import Model.Login;
import Model.User;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.primefaces.component.log.Log;

/**
 *
 * @author Jakob
 */
@Path("sup")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Serverlab1PU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @GET
    @Path("mess")
    @Produces("text/plain")
    public String getMessage() {
        return "yo!";
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        UserHandler uh = new UserHandler();
        uh.createUser(entity.getUsername(), entity.getPassword());
        //super.create(entity);
    }
    
    

    @POST
    @Path("createuser")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        UserHandler uh = new UserHandler();
        String result = uh.createUser(user.getUsername(),user.getPassword());
        
        if(result.equals("Successfully created account!")){
            return Response.status(Response.Status.CREATED).build();
        }else if(result.equals("fack error on createUser server side")){
            return Response.status(Response.Status.FORBIDDEN).build();
        }else if(result.equals("Username already exists!")){
            return Response.status(Response.Status.CONFLICT).build();
        }else{
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response login(User user){
            System.err.println("Försöker logga in");
        UserHandler uh = new UserHandler();
        if(uh.login(user.getUsername(), user.getPassword())){
                    System.err.println("Lyckades");
            return Response.status(Response.Status.FOUND).build();
        }else{
            System.err.println("Misslyckades");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        System.err.println("Hej hej söker efter användare");
        UserHandler uh = new UserHandler();
        return new ArrayList(uh.getAllUsers());
        //return super.findAll();
    }

    @GET
    @Path("getallusers")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User[] getAllUsers(){
        UserHandler uh = new UserHandler();
        return (User[]) uh.getAllUsers().toArray(new User[]{});
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
