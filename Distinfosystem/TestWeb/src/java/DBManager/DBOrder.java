/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManager;

import BO.Item;
import BO.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DBOrder {

    public String sendOrder(Item[] items, Connection c, String username) throws SQLException {
        PreparedStatement selectStatement = null;
        PreparedStatement deleteFromItemStockStatement = null;
        PreparedStatement insertOrderStatement = null;
        PreparedStatement insertOrderItemsStatement = null;

        
        //TODO: retreive userId using userame
        
        
        String insertOrderItemsString = "INSERT INTO OrderItems(orderId,itemId) VALUES(?,?)";
        String selectString = "SELECT * FROM ItemStock WHERE itemId=?";
        String deleteFromItemStockString = "DELETE FROM ItemStock WHERE id=?";
        String insertOrderString = "INSERT INTO Orders(username) VALUES(?)";

        ResultSet rs = null;
        int orderId;

        //insert new order with username, auto increment orderId
        //retreive last order id
        try {
            c.setAutoCommit(false);

            //INSERT NEW ORDER AND GET THE ID
            insertOrderStatement = c.prepareStatement(insertOrderString, Statement.RETURN_GENERATED_KEYS);
            insertOrderStatement.setString(1, username);
            insertOrderStatement.executeUpdate();

            rs = insertOrderStatement.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                return "AN ERROR OCCURED (ERROR: 1)";
            }
            //LOOP THROUGH ITEMS IN ORDER AND REMOVE THEM FROM ITEMSTOCK AND ADD THEM TO ORDERITEMS
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    selectStatement = c.prepareStatement(selectString);
                    selectStatement.setInt(1, items[i].getId());
                    rs = selectStatement.executeQuery();

                    Boolean success = false;
                    while (rs.next()) {
                        //remove it from ItemStock
                        //insert it to OrderItems
                        deleteFromItemStockStatement = c.prepareStatement(deleteFromItemStockString);
                        deleteFromItemStockStatement.setInt(1, (int) rs.getObject("id"));
                        int affectedRows = deleteFromItemStockStatement.executeUpdate();
                        if (affectedRows == 0) {
                        } else {
                            insertOrderItemsStatement = c.prepareStatement(insertOrderItemsString);
                            insertOrderItemsStatement.setInt(1, orderId);
                            insertOrderItemsStatement.setInt(2, items[i].getId());
                            insertOrderItemsStatement.executeUpdate();
                            success = true;
                            break;
                        }
                    }
                    if (!success) {
                        return "AN ERROR OCCURED :( (ERROR: 2)";
                    }
                }

            }

            c.commit();
        } catch (SQLException ex) {
            c.rollback();
            return ex.toString();
        } finally {
            if (selectStatement != null) {
                selectStatement.close();
            }

            if (deleteFromItemStockStatement != null) {
                deleteFromItemStockStatement.close();
            }

            if (insertOrderStatement != null) {
                insertOrderStatement.close();
            }

            if (insertOrderItemsStatement != null) {
                insertOrderItemsStatement.close();
            }

            if (c != null) {
                c.close();
            }

            if (rs != null) {
                rs.close();
            }
        }

        return "Thank you for your order!";

    }
    
    
    
    public ArrayList<Order> getAllOrders(Connection c) throws SQLException{
        PreparedStatement getAllOrdersStatement = null;
        PreparedStatement getOrderItemsStatement = null;
        
        String getOrderItemsQuery = "SELECT * FROM OrderItems WHERE orderId=?";
        String getAllOrdersQuery = "SELECT * FROM Orders;";
        
        getAllOrdersStatement = c.prepareStatement(getAllOrdersQuery);
        
        ResultSet rs = getAllOrdersStatement.executeQuery();
        
        
        ArrayList<Order> orders = new ArrayList<>();
        
        while(rs.next()){
            String username = (String)rs.getObject("username");
            int orderId = (int)rs.getObject("id");
            
            getOrderItemsStatement = c.prepareStatement(getOrderItemsQuery);
            getOrderItemsStatement.setInt(1, orderId);
            ResultSet rs2 = getOrderItemsStatement.executeQuery();
            ArrayList<Item> items = new ArrayList<>();
            
            while(rs2.next()){
                int itemId = (int)rs2.getObject("itemId");
                DBItem dbItem = new DBItem();
                Item i = dbItem.getItemById(itemId, c);
                items.add(i);
            }
            
            Order order = new Order(username,orderId,items);
            orders.add(order);
        }
        
        return orders;
    }

}
