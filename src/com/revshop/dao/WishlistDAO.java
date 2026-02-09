package com.revshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revshop.db.DBConnection;
import com.revshop.model.ProductItem;

public class WishlistDAO {
	
	    public void addToWishlist(int userId, int productId) {

	        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";

	        try {
	        	Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1, userId);
	            ps.setInt(2, productId);
	            ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public List<ProductItem> getWishlist(int userId) {
	            List<ProductItem> wishlist = new ArrayList<>();

	            String sql = "SELECT p.product_id, p.name, p.price, p.stock_quantity " +"FROM wishlist w " +
	            "JOIN products p ON w.product_id = p.product_id " + "WHERE w.user_id = ?";

	            try (Connection con = DBConnection.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {

	                ps.setInt(1, userId);
	                ResultSet rs = ps.executeQuery();

	                while (rs.next()) {
	                    ProductItem product = new ProductItem();
	                    product.setProductId(rs.getInt("product_id"));
	                    product.setProductName(rs.getString("name"));
	                    product.setPrice(rs.getDouble("price"));
	                    product.setStockQuantity(rs.getInt("stock_quantity"));

	                    wishlist.add(product);
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            return wishlist;
	    }

	    	 public boolean removeFromWishlist(int userId, int productId) {

	    	        String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";

	    	        try (Connection con = DBConnection.getConnection();
	    	             PreparedStatement ps = con.prepareStatement(sql)) {

	    	            ps.setInt(1, userId);
	    	            ps.setInt(2, productId);
	    	            return ps.executeUpdate() > 0;

	    	        } catch (Exception e) {
	    	            e.printStackTrace();
	    	        }
	    	        return false;
	    	    }
	    	}
