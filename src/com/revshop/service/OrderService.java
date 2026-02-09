package com.revshop.service;

import java.util.ArrayList;
import java.util.List;

import com.revshop.dao.CartDAO;
import com.revshop.dao.OrdersDAO;
import com.revshop.dao.ProductDAO;
import com.revshop.model.CartItem;
import com.revshop.model.OrderManagement;
import com.revshop.model.OrderedItems;
import com.revshop.model.ProductItem;
import com.revshop.service.NotificationService;;

public class OrderService {
	private CartDAO cartDao = new CartDAO();
    private OrdersDAO orderDao = new OrdersDAO();
    private ProductDAO productDao = new ProductDAO();
   private NotificationService notificationService=new NotificationService();
    
    //PLACE ORDER
    public boolean placeOrder(int userId) {

        //  Fetch cart items
        List<CartItem> cartItems = cartDao.getCartByUser(userId);

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Cannot place order!");
            return false;
        }
        double totalAmount = 0;
        List<OrderedItems> orderItems = new ArrayList<>();
     // Convert cart items - order items
        for (CartItem cart : cartItems)
        { 
        	// Get product details
        	ProductItem product = productDao.getProductById(cart.getProductId());
            if (product == null) {
                System.out.println("Product not found for ID: " + cart.getProductId());
                return false;
            }
            double itemTotal = product.getPrice() * cart.getQuantity();
            totalAmount += itemTotal;

         // order_id will be set after order creation
            OrderedItems oi = new OrderedItems(0,product.getProductId(),cart.getQuantity(),product.getPrice());
          
            orderItems.add(oi);
        }
     //  Create order
        OrderManagement order = new OrderManagement(userId, totalAmount, "PLACED");
        int orderId = orderDao.createOrder(order);
        
        if (orderId <= 0) {
            System.out.println("Failed to create order!");
            return false;
        }

        // Set order_id in order items
        for (OrderedItems item : orderItems) {
            item.setOrderId(orderId);
        }

        //  Insert order items
        orderDao.addOrderItems(orderItems);

        // Clear cart
        orderDao.clearCart(userId);

        System.out.println("Order placed successfully! Order ID: " + orderId);
        notificationService.notifyUser(userId,"Your order #" + orderId + " has been placed successfully");
        return true;
    }

}

