/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aktakurvanmavenserver;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            FileInputStream serviceAccount = new FileInputStream("D:\\Firebase\\mobilapp-67b55-firebase-adminsdk-ijhds-43eb2c5271.json");
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mobilapp-67b55.firebaseio.com")
                    .build();
            FirebaseApp initializeApp = FirebaseApp.initializeApp(options);

            FirebaseAuth defaultAuth = FirebaseAuth.getInstance(initializeApp);
            try {
                String s = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImVmMzE0M2MyZmM4ZWE2MWQ0ODI5Y2FjMDVjMjcyM2MyNmExZTFmYjgifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbW9iaWxhcHAtNjdiNTUiLCJhdWQiOiJtb2JpbGFwcC02N2I1NSIsImF1dGhfdGltZSI6MTUxMzg2Mzg5MiwidXNlcl9pZCI6IlZLTkVEWVp1VmxXMVJ3Q2gwRnFybmlQaHVSaTIiLCJzdWIiOiJWS05FRFladVZsVzFSd0NoMEZxcm5pUGh1UmkyIiwiaWF0IjoxNTEzODY4NDY5LCJleHAiOjE1MTM4NzIwNjksImVtYWlsIjoia3Jha3RhcmRAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImtyYWt0YXJkQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.W8S_0q044Q1A7M0cwg0jKUDVGk0RGws_yL-LpKCSLf6jjiRNE2FT2s8cWBsZidU9AQardTJnnp9su3htF7pgHqmUDoBeXjcDvph0oes4Z2HRZn688E1uimapTgAT_7CvE5v0QxH5iU8NRGhRF9qO1PiFV9IpzTxk2zTIFmqGhxvROGjyAXlB25WJ0iIbNKh9WmLWE6r3Kp5_42h7qFgdIDxd39LNekykcO5YVYEPO8uJNJEg3I3DwL3F3B5-HxlDi2WygEcWcrmDALOAyaDKX4TwYRV7Yygp5X6MCJ_oYSdkmpYBpzzQx4eAfcUOLWDrJ_TNrB-krmxOpbS5E1iYgQ";
                FirebaseToken decodedToken = defaultAuth.verifyIdTokenAsync(s).get();
                String uid = decodedToken.getUid();
                
                if (uid == null) {
                    System.out.println("Är ju null!!!!");

                } else {
                    System.out.println("Da uid: " + uid);
                }
            } catch (Exception e) {
                System.out.println("Fack error");
                System.out.println(e.toString());
            }
            
            System.out.println("Great success!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
