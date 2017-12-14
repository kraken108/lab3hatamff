/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DataSetService extends AbstractVerticle {

    @Override
    public void start() {
        //blablabla

        ArrayList<SockJSSocket> sockets = new ArrayList<>();

        Router router = Router.router(vertx);

        SockJSHandlerOptions options = new SockJSHandlerOptions().setHeartbeatInterval(2000);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);

        sockJSHandler.socketHandler(sockJSSocket -> {
            System.out.println("New connection!");
            //sockJSSocket.handler(sockJSSocket::write);//echo
            sockets.add(sockJSSocket);
        });

        router.route("/myapp/*").handler(sockJSHandler);

        vertx.createHttpServer().requestHandler(router::accept).listen(5566);

        EventBus eb = vertx.eventBus();

        eb.consumer("feed", message -> {
            System.out.println("Received news on consumer 1: " + message.body());
            for(SockJSSocket s : sockets){
                s.write(Buffer.buffer(message.body().toString()));
            }
        });
                

        System.out.println("DataSet receiver ready!");

    }

    private class Sender implements Runnable {

        SockJSSocket socket;

        public Sender(SockJSSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            long lastUpdated = 0;

            while (true) {
                if (System.currentTimeMillis() > lastUpdated + 3000) {
                    socket.write(Buffer.buffer(Long.toString(lastUpdated)));
                    lastUpdated = System.currentTimeMillis();
                }
            }
        }

    }
}
