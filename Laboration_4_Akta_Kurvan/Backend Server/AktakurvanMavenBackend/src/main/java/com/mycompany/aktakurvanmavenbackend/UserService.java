/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aktakurvanmavenbackend;

import BO.GameInfo;
import BO.UserHandler;
import ViewModel.NetworkGame;
import ViewModel.VUser;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class UserService extends AbstractVerticle {

    private FirebaseApp firebaseApp;

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

        router.post("/api/friends").handler(this::getUsersFriends);
        router.post("/api/login").handler(this::login);
        router.post("/api/updatedevicetoken").handler(this::updateDeviceToken);
        router.post("/api/addfriend").handler(this::addFriend);
        router.post("/api/getpendingfriends").handler(this::getPendingFriends);
        router.post("/api/respondfriendrequest").handler(this::respondFriendRequest);
        router.post("/api/getfriends").handler(this::getFriends);
        router.post("/api/challenge").handler(this::challengeFriend);
        
        //Initiera firebase
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("D:\\Firebase\\mobilapp-67b55-firebase-adminsdk-ijhds-43eb2c5271.json");
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mobilapp-67b55.firebaseio.com")
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldnt start User service");
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldnt start User service");
        }

        vertx.createHttpServer().requestHandler(router::accept).listen(7932);
        System.out.println("User service started");

    }

    private void challengeFriend(RoutingContext rc){
        System.out.println("Challenging friend");
        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);
        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }
        UserHandler uh = new UserHandler();
        int response = uh.sendChallengeRequest(u);
        
        if(response >= 0){
            System.out.println("Successfully sent challenge request");
            rc.response().setStatusCode(302).putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(new NetworkGame(GameInfo.getInstance().getIp(),
                GameInfo.getInstance().getClientPort(),response)));
                
        }else{
            System.out.println("Failed to send challenge request :<");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Failed to send challenge request :<");
        }
        
        
        
    }
    
    private void addFriend(RoutingContext rc) {
        System.out.println("Adding fwend");
        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);
        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }

        UserHandler uh = new UserHandler();
        if (uh.addFriend(u)) {
            System.out.println("Successfully sent friend request!");
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successfully sent friend request!");
        } else {
            System.out.println("Couldnt send friend request");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Couldnt send friend request");
        }
    }

    private void login(RoutingContext rc) {
        System.out.println("Login attempt");
        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);
        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }
        UserHandler uh = new UserHandler();
        if (uh.login(u)) {
            System.out.println("Successful login!");
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successful login!");
        } else {
            System.out.println("Unsuccessful login");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Unsuccessful login");
        }
    }
    
    private void respondFriendRequest(RoutingContext rc){
        VUser u = Json.decodeValue(rc.getBodyAsString(),VUser.class);
        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }
        
        UserHandler uh = new UserHandler();
        if(uh.respondFriendRequest(u)){
            System.out.println("Successfully responded to friend request");
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successfully responded to friend request");
        } else {
            System.out.println("Failed to respond to friend request");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Failed to respond to friend request");
        }
    }
    
    private void getFriends(RoutingContext rc){
        VUser u = Json.decodeValue(rc.getBodyAsString(),VUser.class);
        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }
        
        UserHandler uh = new UserHandler();
        
        System.out.println("wait");
        List<VUser> list = (List<VUser>) uh.getFriends(u);

        System.out.println("done");
        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(list));
        System.out.println("oki");
    }

    private void updateDeviceToken(RoutingContext rc) {
        System.out.println("Attempting to update device token");
        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);

        if (!didVerify(u)) {
            System.out.println("Didnt verify");
            rc.response().setStatusCode(403).putHeader("content-type", "text/html").end("Unsuccessful login");
            return;
        } else {
            System.out.println("Verified");
        }
        UserHandler uh = new UserHandler();
        if (uh.updateDeviceToken(u)) {
            System.out.println("Successful device token update!");
            rc.response().setStatusCode(302).putHeader("content-type", "text/html").end("Successful device token update!");
        } else {
            System.out.println("Unsuccessful device token update");
            rc.response().setStatusCode(404).putHeader("content-type", "text/html").end("Unsuccessful device token update");
        }
    }

    private void getPendingFriends(RoutingContext rc) {
        System.out.println("Retrieving all posts");
        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);
        UserHandler uh = new UserHandler();

        List<VUser> list = (List<VUser>) uh.getPendingFriends(u);

        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(list));
    }

    private void getUsersFriends(RoutingContext rc) {

        VUser u = Json.decodeValue(rc.getBodyAsString(), VUser.class);
        UserHandler uh = new UserHandler();
        List<ViewModel.VUser> users = uh.getAllUsers(u);

        rc.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(users));
    }

    private boolean didVerify(VUser u) {
        FirebaseAuth defaultAuth = FirebaseAuth.getInstance(firebaseApp);
        try {
            String s = u.getTokenId();
            FirebaseToken decodedToken = defaultAuth.verifyIdTokenAsync(s).get();
            String email = decodedToken.getEmail();

            if (email == null) {
                return false;
            } else {
                if (email.equals(u.getEmail())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
