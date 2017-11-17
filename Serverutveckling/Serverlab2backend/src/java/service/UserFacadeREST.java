/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BO.UserHandler;
import Model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jakob
 */
@Path("userfacade")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Serverlab2backendPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Path("createuser")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        System.err.println("Hej hej försöker skapa konto");
        try {
            UserHandler uh = new UserHandler();
            String result = uh.createUser(user.getUsername(), user.getPassword());

            if (result.equals("Successfully created account!")) {
                return Response.status(Response.Status.CREATED).build();
            } else if (result.equals("fack error on createUser server side")) {
                return Response.status(Response.Status.FORBIDDEN).build();
            } else if (result.equals("Username already exists!")) {
                return Response.status(Response.Status.CONFLICT).build();
            } else {
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response login(User user) {
        UserHandler uh = new UserHandler();
        if (uh.login(user.getUsername(), user.getPassword())) {
            System.err.println("Lyckades");
            return Response.status(Response.Status.FOUND).build();
        } else {
            System.err.println("Misslyckades");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        try {
            UserHandler uh = new UserHandler();
            return uh.getAllUsers();
        }catch(Exception e){
            return null;
        }

        //return super.findAll();
    }

    @POST
    @Path("checkalreadyexists")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response checkAlreadyExists(User user) {
        System.err.println("Kollar ifall det redan finns");
        UserHandler uh = new UserHandler();
        if (uh.checkIfAlreadyExists(user.getUsername())) {
            return Response.status(Response.Status.FOUND).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
