/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BO.LogHandler;
import BO.MessageHandler;
import BO.PostHandler;
import Model.Message;
import Model.Post;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
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
@Path("messagefacade")
public class MessageFacadeREST extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "Serverlab2backendPU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Path("createnewmessage")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createNewMessage(Message entity) {
        try {
            MessageHandler mh = new MessageHandler();
            if (mh.sendMessage(entity.getReceiver().getUsername(), 
                    entity.getSender().getUsername(), entity.getTopic(), 
                    entity.getMessage(), new Date())) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            try {
                PrintWriter writer = new PrintWriter("D:/Loggfil/logfil.txt", "UTF-8");
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
    public List<Message> getMessagesByUser(@PathParam("user") String user) {
        MessageHandler mh = new MessageHandler();

        return (List<Message>) mh.getMessagesByReceiver(user);
    }
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
