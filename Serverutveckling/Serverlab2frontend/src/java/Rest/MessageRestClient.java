/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:MessageFacadeREST
 * [messagefacade]<br>
 * USAGE:
 * <pre>
 *        MessageRestClient client = new MessageRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jakob
 */
public class MessageRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:1124";

    public MessageRestClient(){
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("api/messages");
    }

    public <T> T getMessagesByUser_XML(GenericType<T> responseType, String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{user}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getMessagesByUser_JSON(GenericType<T> responseType, String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{user}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response createNewMessage_XML(Object requestEntity) throws ClientErrorException {
        WebTarget newTarget = client.target(BASE_URI).path("api");
        return newTarget.path("createnewmessage").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response createNewMessage_JSON(Object requestEntity) throws ClientErrorException {
        WebTarget newTarget = client.target(BASE_URI).path("api");
        return newTarget.path("createnewmessage").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public void close() {
        client.close();
    }
    
}
