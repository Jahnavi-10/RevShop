package com.revshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.revshop.db.DBConnection;
import com.revshop.model.OrderManagement;
import com.revshop.model.OrderedItems;

public class OrdersDAO {
	

	//  CREATE ORDER 
    public int createOrder(OrderManagement order) {

        String sql = "INSERT INTO orders (user_id, total_amount, order_status)VALUES (?, ?, ?)";
        
    try (Connection con = DBConnection.getConnection();
            PreparedStatement ps =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
    {

           ps.setInt(1, order.getUserId());
           ps.setDouble(2, order.getTotalAmount());
           ps.setString(3, order.getOrderStatus());

           ps.executeUpdate();

           ResultSet rs = ps.getGeneratedKeys();
           if (rs.next()) {
               return rs.getInt(1); // order_id
           }

       } 
    catch (Exception e) {
           e.printStackTrace();
       }
       return -1;
   }
    // ADD ORDER ITEMS 
    public void addOrderItems(List<OrderedItems> items) {

        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price)VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {

            for (OrderedItems item : items) {
                ps.setInt(1, item.getOrderId());
                ps.setInt(2, item.getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 //  CLEAR CART AFTER CHECKOUT 
    public void clearCart(int userId) {

        String sql = "DELETE FROM cart WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

