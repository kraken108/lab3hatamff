/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import BO.DatasetHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class DataSetAdder extends AbstractVerticle {

    private int nextInt;
    @Override
    public void start() {
        DatasetHandler datasetHandler = new DatasetHandler();
        nextInt = 0;
        ArrayList<String> strings = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\datavalues.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                strings.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataSetAdder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataSetAdder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataSetAdder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        ArrayList<Integer> daInts = new ArrayList<>();

        for (String s : strings) {
            try {
                daInts.add(new Integer(s));
            } catch (Exception e) {
                System.out.println(e);
            }
        }


        EventBus eb = vertx.eventBus();
        
        vertx.setPeriodic(2000, t -> {
            // Create a timestamp string
           // Random rand = new Random();
            //int val = rand.nextInt(200) + 10;
            if(nextInt<daInts.size()){
              // eb.send("feed", Integer.toString(daInts.get(nextInt)));
               nextInt++;
            }
        });

    }
}
