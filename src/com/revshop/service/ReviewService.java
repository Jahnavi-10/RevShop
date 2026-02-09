package com.revshop.service;

import java.util.List;

import com.revshop.dao.ReviewsDAO;
import com.revshop.model.Reviews;

public class ReviewService {
	
	private static ReviewsDAO reviewDao = new ReviewsDAO();

    // ADD REVIEW (Buyer action)
    public boolean addReview(int userId, int productId, int rating, String comment) {

        // Business validations
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5!");
            return false;
        }

        if (comment == null || comment.trim().isEmpty()) {
            System.out.println("Review comment cannot be empty!");
            return false;
        }

        Reviews review = new Reviews(productId, userId, rating, comment);
        return reviewDao.addReview(review);
    }

    // VIEW REVIEWS FOR A PRODUCT
    public static List<Reviews> getProductReviews(int productId) {
        return reviewDao.getReviewsByProduct(productId);
    }

    // GET AVERAGE RATING
    public static double getAverageRating(int productId) {
        return reviewDao.getAverageRating(productId);
    }
}

