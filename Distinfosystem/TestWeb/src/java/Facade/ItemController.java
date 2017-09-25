/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import BO.Item;
import DBManager.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class ItemController {

    public ItemController() {
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            DBManager dbManager = new MysqlManager();
            items = dbManager.getItems();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(items == null){
            return null;
        }else{
            return items;
        }
    }
}
