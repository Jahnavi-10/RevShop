package com.revshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revshop.db.DBConnection;
import com.revshop.model.CartItem;

public class CartDAO {
	public boolean addToCart(CartItem item)
	{

		//Inserting data into the cart table
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
          
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getUserId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public List<CartItem> getCartByUser(int userId) {

        List<CartItem> cartItems = new ArrayList<>();
        //Reading data from the db

        String sql = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                CartItem item = new CartItem();
                item.setCartId(rs.getInt("cart_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));

                cartItems.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
	
	public boolean updateQuantity(int cartId, int quantity) 
	{

        String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean removeFromCart(int cartId)
	{

        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
