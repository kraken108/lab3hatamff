/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import BO.UserHandler;
import Model.*;
import ViewModel.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import io.vertx.ext.web.handler.StaticHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakob
 */
public class UserService extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);

        //testa om servicen fungerar
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        //skapa bodyhandler för att kunna läsa body i requests
        router.route(HttpMethod.POST, "/*").handler(BodyHandler.create());

        router.post("/api/checkalreadyexists").handler(this::checkalreadyexists);
        router.get("/api/user").handler(this::getAllUsers);
        router.get("/api/images/:user").handler(this::getUserImages);
        router.post("/api/login").handler(this::login);
        router.post("/api/createuser").handler(this::createuser);
        router.post("/api/savediagram").handler(this::saveDiagram);
        vertx.createHttpServer().requestHandler(router::accept).listen(1122);
        System.out.println("User service started");
    }

    private void getAllUsers(RoutingContext routingContext) {

        UserHandler uh = new UserHandler();
        List<User> users = uh.getAllUsers();
        List<UserView> viewUsers = new ArrayList<>();
        for (User u : users) {
            u.setPosts(null);
            u.setReceivedMessages(null);
            u.setSentMessages(null);
            viewUsers.add(new UserView(u.getUsername(),""));
        }
        
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(viewUsers));

        System.out.println("eh ok?");
    }

    private void login(RoutingContext rc) {
        UserView u = Json.decodeValue(rc.getBodyAsString(), UserView.class);
        UserHandler uh = new UserHandler();
        if (uh.login(u.getUsername(), u.getPassword())) {
            System.out.println("Successful login!");
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successful login!");
        } else {
            System.out.println("Unsuccessful login");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Unsuccessful login");
        }
    }

    private void createuser(RoutingContext rc) {
        UserView user = Json.decodeValue(rc.getBodyAsString(), UserView.class);
        System.out.println("Försöker skapa konto");
        try {
            UserHandler uh = new UserHandler();
            String result = uh.createUser(user.getUsername(), user.getPassword());

            if (result.equals("Successfully created account!")) {
                rc.response().setStatusCode(201).putHeader("content-type", "text/html").end("Successful login!");
            } else if (result.equals("fack error on createUser server side")) {
                rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Successful login!");

            } else if (result.equals("Username already exists!")) {
                rc.response().setStatusCode(409).putHeader("content-type", "text/html").end("Successful login!");

            } else {
                rc.response().setStatusCode(417).putHeader("content-type", "text/html").end("Successful login!");
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            rc.response().setStatusCode(503).putHeader("content-type", "text/html").end("Successful login!");
        }
    }

    private void checkalreadyexists(RoutingContext rc) {
        UserView u = Json.decodeValue(rc.getBodyAsString(), UserView.class);
        UserHandler uh = new UserHandler();
        if (uh.checkIfAlreadyExists(u.getUsername())) {
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Username already exists");
        } else {
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Username is available");
        }
    }
    
    private void saveDiagram(RoutingContext rc){
        ViewImage im = Json.decodeValue(rc.getBodyAsString(), ViewImage.class);
        System.out.println("Received imagedata:");
        System.out.println(im.getImageData());
        UserHandler uh = new UserHandler();
        System.out.println("Image length: " + im.getImageData().length());
        try{
            if(uh.saveImage(im.getImageData(), im.getUsername())){
                rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successful login!");
            }else{
                rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Unsuccessful login");
            }
            
        }catch(Exception e){
            rc.response().setStatusCode(409).putHeader("content-type", "text/html").end("Successful login!");
        }
    }
    
    private void getUserImages(RoutingContext rc){
                String user = rc.request().getParam("user");
        System.out.println("Get all posts by: " + user);
        UserHandler uh = new UserHandler();
        List<Image> list = (List<Image>) uh.getUserImages(user);
        List<ViewImage> viewList = new ArrayList<>();

        for (Image i : list) {
            viewList.add(new ViewImage(new String(i.getImageData()),i.getUser().getUsername()));
        }

        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(viewList));
    }

}
