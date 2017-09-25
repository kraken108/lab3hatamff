/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import java.sql.SQLException;

/**
 *
 * @author Jakob
 */
public interface DBManager {
    public void getData();
    public Boolean tryLogin(String username,String password) throws SQLException;
}
