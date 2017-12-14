/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Jakob
 */
public class DataSetSender extends AbstractVerticle {

    int value = 10;

    @Override
    public void start() {
        EventBus eb = vertx.eventBus();

        vertx.setPeriodic(2000, t -> {
            // Create a timestamp string
            Random rand = new Random();
            int val = rand.nextInt(200) + 10;

            eb.send("feed", Integer.toString(val));
        });
    }
}
