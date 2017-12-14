/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import BO.MessageHandler;
import Model.Message;
import ViewModel.MessageView;
import ViewModel.UserView;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jakob
 */
public class MessageService extends AbstractVerticle {

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
        router.post("/api/createnewmessage").handler(this::createnewmessage);
        router.get("/api/messages/:user").handler(this::getmessages);
        //router.post("/api/createnewpost").handler(this::createnewpost);
        //router.get("/api/posts/:user").handler(this::getposts);

        vertx.createHttpServer().requestHandler(router::accept).listen(1124);
        System.out.println("Messages service started");
    }

    private void createnewmessage(RoutingContext rc) {
        MessageView entity = Json.decodeValue(rc.getBodyAsString(), MessageView.class);
        try {
            MessageHandler mh = new MessageHandler();
            if (mh.sendMessage(entity.getReceiver().getUsername(),
                    entity.getSender().getUsername(), entity.getTopic(),
                    entity.getMessage(), new Date())) {
                rc.response().setStatusCode(201).putHeader("content-type", "text/html").end("Successful login!");
            } else {
                rc.response().setStatusCode(409).putHeader("content-type", "text/html").end("Successful login!");
            }
        } catch (Exception e) {
            System.out.println("Error!!! :( " + e.toString());
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Successful login!");
        }
    }

    private void getmessages(RoutingContext rc) {
        String user = rc.request().getParam("user");
        MessageHandler mh = new MessageHandler();
        List<Message> messages = mh.getMessagesByReceiver(user);
        List<MessageView> viewList = new ArrayList<>();

        for (Message m : messages) {
            viewList.add(new MessageView(m.getDate(), m.getTopic(), m.getMessage(),
                    new UserView(m.getSender().getUsername(), ""),
                    new UserView(m.getReceiver().getUsername(), ""),
                    m.getId()));
        }

        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(viewList));
    }

}
