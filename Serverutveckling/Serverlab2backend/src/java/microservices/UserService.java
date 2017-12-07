/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import BO.UserHandler;
import Model.User;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import io.vertx.ext.web.handler.StaticHandler;
import java.util.List;

/**
 *
 * @author Jakob
 */
public class UserService extends AbstractVerticle {

    @Override
    public void start() {
        System.out.println("tjoooo");
        Router router = Router.router(vertx);

        //router.route("/assets/*").handler(StaticHandler.create("assets"));
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        router.route(HttpMethod.POST, "/api").handler(BodyHandler.create());
        router.post("/api/checkalreadyexists").consumes("application/json").handler(rc -> {
            System.out.println("Hej hej hej tjo");
            System.err.println("Kollar ifall det redan finns");
            //User u = Json.decodeValue(rc.getBodyAsString(), User.class);
            User u = new User();
            u.setUsername("Jubbe");
            UserHandler uh = new UserHandler();
            if (uh.checkIfAlreadyExists(u.getUsername())) {
                rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Tjaaa");
            } else {
                rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Tjaaa");
            }
        });

        router.get("/api/user").handler(this::getAllUsers);

        System.out.println("wazza");
        vertx.createHttpServer().requestHandler(router::accept).listen(1122);
    }

    private void getAllUsers(RoutingContext routingContext) {

        UserHandler uh = new UserHandler();
        List<User> users = uh.getAllUsers();
        for (User u : users) {
            System.out.println(u.getUsername());
            u.setPosts(null);
            u.setReceivedMessages(null);
            u.setSentMessages(null);
        }
        User u = new User();
        u.setUsername("Jacken");
        System.out.println(Json.encode(u));
        System.out.println("jaaaa: " + Json.encode(users));
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(users));

        System.out.println("eh ok?");
    }

}
