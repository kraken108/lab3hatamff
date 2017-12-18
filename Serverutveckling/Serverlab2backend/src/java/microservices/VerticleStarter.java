/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 *
 * @author Jakob
 */
public class VerticleStarter extends AbstractVerticle{

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new UserService());
        vertx.deployVerticle(new PostService());
        vertx.deployVerticle(new MessageService());
        vertx.deployVerticle(new DataSetSender());
        vertx.deployVerticle(new DataSetService());
        vertx.deployVerticle(new DataSetAdder());
    }
}
