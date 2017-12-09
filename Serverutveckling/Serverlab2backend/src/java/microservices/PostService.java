/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import BO.LogHandler;
import BO.PostHandler;
import Model.Post;
import ViewModel.PostView;
import ViewModel.UserView;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakob
 */
public class PostService extends AbstractVerticle {

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
        router.post("/api/createnewpost").handler(this::createnewpost);
        router.get("/api/posts/:user").handler(this::getposts);

        vertx.createHttpServer().requestHandler(router::accept).listen(1123);
        System.out.println("Posts service started");
    }

    private void getposts(RoutingContext rc) {
        String user = rc.request().getParam("user");
        System.out.println("Get all posts by: " + user);
        LogHandler lh = new LogHandler();
        List<Post> list = (List<Post>) lh.getPostsByUser(user);
        List<PostView> viewList = new ArrayList<>();

        for (Post p : list) {
            viewList.add(new PostView(p.getDate(), p.getMessage(),
                    new UserView(p.getUser().getUsername(), ""),
                    p.getId()));
        }

        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(viewList));
    }

    private void createnewpost(RoutingContext rc) {
        PostView entity = Json.decodeValue(rc.getBodyAsString(), PostView.class);
        try {
            PostHandler ph = new PostHandler();
            if (ph.createNewPost(entity.getMessage(), entity.getUser().getUsername())) {
                rc.response().setStatusCode(201).putHeader("content-type", "text/html").end("Successful login!");
            } else {
                rc.response().setStatusCode(409).putHeader("content-type", "text/html").end("Successful login!");
            }
        } catch (Exception e) {
            System.out.println("Error!! :( " + e.toString());
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Successful login!");
        }
    }
}
