package com.revshop.service;

import java.util.List;

import com.revshop.dao.CartDAO;
import com.revshop.model.CartItem;

public class CartUsageService {

	private CartDAO cDao = new CartDAO();
	
	 public boolean addToCart(int userId, int productId, int quantity) {

	        if (quantity <= 0) {
	            System.out.println("Quantity must be greater than zero!");
	            return false;
	        }

	        CartItem item = new CartItem(userId, productId, quantity);
	        return cDao.addToCart(item);
	    }
	 
	 public List<CartItem> viewCart(int userId)
	 {
	        return cDao.getCartByUser(userId);
	    }
	 
	 public boolean updateQuantity(int cartId, int newQuantity) {

	        if (newQuantity <= 0) {
	            System.out.println("Quantity must be greater than zero!");
	            return false;
	        }

	        return cDao.updateQuantity(cartId, newQuantity);
	    }
	 
	 public boolean removeItem(int cartId) {
	        return cDao.removeFromCart(cartId);
	    }

}

