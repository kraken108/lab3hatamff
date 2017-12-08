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
 * Jersey REST client generated for REST resource:PostFacadeREST
 * [postfacade]<br>
 * USAGE:
 * <pre>
 *        PostRestClient client = new PostRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jakob
 */
public class PostRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:1123";

    public PostRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("api/posts");
    }

    public Response createNewPost_XML(Object requestEntity) throws ClientErrorException {
        return webTarget.path("createnewpost").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response createNewPost_JSON(Object requestEntity) throws ClientErrorException {
        WebTarget newTarget = client.target(BASE_URI).path("api");
        return newTarget.path("createnewpost").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T getPostsByUser_XML(GenericType<T> responseType, String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{user}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getPostsByUser_JSON(GenericType<T> responseType, String user) throws ClientErrorException, Exception {
        try{
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{user}));
                       
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }catch(Exception e){
            throw(new Exception(e.toString() + "getposts" + java.text.MessageFormat.format("{0}", new Object[]{user})));
        }

    }

    public void close() {
        client.close();
    }
    
}
