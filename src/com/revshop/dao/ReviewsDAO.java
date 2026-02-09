package com.revshop.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.List;

	import com.revshop.model.Reviews;
	import com.revshop.db.DBConnection;

	public class ReviewsDAO {
		
		public boolean addReview(Reviews review) {

	        String sql = "INSERT INTO reviews (product_id, user_id, rating, review_comment) VALUES (?, ?, ?, ?)";
	        try (Connection con = DBConnection.getConnection();
	                PreparedStatement ps = con.prepareStatement(sql)) {

	               ps.setInt(1, review.getProductId());
	               ps.setInt(2, review.getUserId());
	               ps.setInt(3, review.getRating());
	               ps.setString(4, review.getReviewComment());

	               return ps.executeUpdate() > 0;

	           } catch (Exception Ex) {
	               System.out.println("Review already exists or invalid rating!");
	               return false;
	           }
	       }
		// VIEW REVIEWS FOR A PRODUCT
	    public List<Reviews> getReviewsByProduct(int productId) {

	        List<Reviews> reviews = new ArrayList<>();

	        String sql = "SELECT * FROM reviewsWHERE product_id = ? ORDER BY review_id DESC";
	        
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, productId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Reviews rv = new Reviews();
	                rv.setReviewId(rs.getInt("review_id"));
	                rv.setProductId(rs.getInt("product_id"));
	                rv.setUserId(rs.getInt("user_id"));
	                rv.setRating(rs.getInt("rating"));
	                rv.setReviewComment(rs.getString("review_comment"));
	                rv.setCreatedAt(rs.getTimestamp("created_at"));

	                reviews.add(rv);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return reviews;
	    }

	    // GET AVERAGE RATING
	    public double getAverageRating(int productId) {

	        String sql = "SELECT AVG(rating) FROM reviews WHERE product_id = ?";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, productId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return rs.getDouble(1);
	            }

	        } catch (Exception Ex)
	        {
	            Ex.printStackTrace();
	        }

	        return 0.0;
	    }
}

