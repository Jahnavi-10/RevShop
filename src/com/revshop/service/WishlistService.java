package com.revshop.service;

import java.util.List;

import com.revshop.dao.WishlistDAO;
import com.revshop.model.ProductItem;

public class WishlistService {

	    private WishlistDAO dao = new WishlistDAO();

	    public void add(int userId, int productId) {
	        dao.addToWishlist(userId, productId);
	    }

	    public List<ProductItem> view(int userId) {
	        return dao.getWishlist(userId);
	    }

	    public void removeFromWishlist(int userId, int productId) {
	        dao.removeFromWishlist(userId, productId);
	    }
	}
