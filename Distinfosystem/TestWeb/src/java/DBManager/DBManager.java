/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakob
 */
public interface DBManager {
    public void getData();
    public Boolean tryLogin(String username,String password) throws SQLException;
    public ArrayList<Item> getItems() throws SQLException;
}
