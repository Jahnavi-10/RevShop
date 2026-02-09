package com.revshop.ui;

import java.util.List;
import java.util.Scanner;

import com.revshop.model.CartItem;
import com.revshop.model.ProductItem;
import com.revshop.model.Reviews;
import com.revshop.service.CartUsageService;
import com.revshop.service.ProductService;
import com.revshop.service.ReviewService;
import com.revshop.service.WishlistService;

public class BuyerUIMenu {

	private static Scanner sc = new Scanner(System.in);
	private static ProductService productService = new ProductService();
	private static CartUsageService cartService = new CartUsageService();
	private static WishlistService wishlistService = new WishlistService();


    public static void showMenu(int userId) 
    {
    	while (true) 
    	        {
    	        	System.out.println("\n=== BUYER MENU ===");
    	        	System.out.println("1. View All Products");
    	        	System.out.println("2. View Products by Category");
    	        	System.out.println("3. Search Products");
    	        	System.out.println("4. Add Product to Cart");
    	        	System.out.println("5.Show Product reviews");
    	        	System.out.println("6.Add Product reviews");
    	        	System.out.println("7. View Cart");
    	        	System.out.println("8. Update Cart Quantity");
    	        	System.out.println("9. Remove Item from Cart");
    	        	System.out.println("10. Add to Wishlist");
    	        	System.out.println("11. View Wishlist");
    	        	System.out.println("12.Remove from wishlist");
                    System.out.println("13. Logout");
    	        	
    	            System.out.print("Choose option: ");
    	            int choice = Integer.parseInt(sc.nextLine());

    	            switch (choice) 
    	            {
    	            
    	                case 1:
    	                    viewAllProducts();
    	                    break;
    	                case 2:
    	                    viewProductsByCategory();
    	                    break;
    	                case 3:
    	                    searchProducts();
    	                    break;
    	                case 4:
    	                    addToCart(userId);
    	                    break;
    	                case 5:
    	                	viewProductReviews();
    	                    break;
    	                case 6:
    	                	addReview(userId);
    	                	break;
    	                case 7:
    	                    viewCart(userId);
    	                    break;
    	                case 8:
    	                    updateCartQuantity();
    	                    break;
    	                case 9:
    	                    removeCartItem();
    	                    break;
    	                case 10:
    	                    addToWishlist(userId);
    	                    break;
                        case 11:
                            viewWishlist(userId);
    	                    break;
                        case 12:
                            System.out.print("Enter Product ID to remove from wishlist: ");
                            int removeId = Integer.parseInt(sc.nextLine());
                            wishlistService.removeFromWishlist(userId, removeId);
                            System.out.println("Product removed from wishlist!");
                            break;

    	                case 13:
    	                    return;
    	                default:
    	                    System.out.println("Invalid choice!");
    	            }
    	        }
    	    }
    //to view all products along with its contents
    private static void viewAllProducts() {

        List<ProductItem> products = productService.viewAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products available!");
            return;
        }

        System.out.println("\n--- ALL PRODUCTS ---");
        for (ProductItem p : products) {
            System.out.println("---------------------------------");
            System.out.println("Product ID: " + p.getProductId());
            System.out.println("Name: " + p.getProductName());
            System.out.println("Price: " + p.getPrice());
            System.out.println("MRP: " + p.getMrp());
            System.out.println("Stock: " + p.getStockQuantity());
        }
    }
    //View products by category
    private static void viewProductsByCategory() {

        System.out.print("Enter Category ID: ");
        int categoryId = Integer.parseInt(sc.nextLine());

        List<ProductItem> products =
                productService.viewProductsByCategory(categoryId);

        if (products.isEmpty()) {
            System.out.println("No products found for this category!");
            return;
        }

        for (ProductItem p : products) {
            System.out.println("---------------------------------");
            System.out.println("Product ID: " + p.getProductId());
            System.out.println("Name: " + p.getProductName());
            System.out.println("Price: " + p.getPrice());
            System.out.println("MRP: " + p.getMrp());
            System.out.println("Stock: " + p.getStockQuantity());
        }
    }
    
    //Search products
    private static void searchProducts() {

        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine();

        List<ProductItem> products =productService.searchProducts(keyword);

        if (products.isEmpty()) {
            System.out.println("No matching products found!");
            return;
        }

        for (ProductItem p : products) {
            System.out.println("---------------------------------");
            System.out.println("Product ID: " + p.getProductId());
            System.out.println("Name: " + p.getProductName());
            System.out.println("Price: " + p.getPrice());
            System.out.println("MRP: " + p.getMrp());
            System.out.println("Stock: " + p.getStockQuantity());
        }
    }

    //Method to add items into the cart for buyers
    private static void addToCart(int userId) {

        System.out.print("Enter Product ID: ");
        int productId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        boolean success = cartService.addToCart(userId, productId, quantity);

        if (success)
            System.out.println("Product added to cart!");
        else
            System.out.println("Failed to add product to cart!");
    }
    
    //To view reviews of the product
    private static void viewProductReviews()
    {
    	 System.out.print("Enter Product ID: ");
    	    int productId = Integer.parseInt(sc.nextLine());

    	    double avgRating = ReviewService.getAverageRating(productId);
    	    System.out.println("Average Rating: " + avgRating);

    	    List<Reviews> reviews = ReviewService.getProductReviews(productId);

    	    if (reviews.isEmpty()) {
    	        System.out.println("No reviews yet for this product.");
    	        return;
    	    }

    	    System.out.println("\n--- REVIEWS ---");
    	    for (Reviews r : reviews) {
    	        System.out.println("----------------------");
    	        System.out.println("Rating: " + r.getRating());
    	        System.out.println("Comment: " + r.getReviewComment());
    	    }
    }
    //To add reviews to the products
    private static void addReview(int userId) {

        System.out.print("Enter Product ID: ");
        int productId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Rating (1-5): ");
        int rating = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Review Comment: ");
        String comment = sc.nextLine();
        
        ReviewService revser= new ReviewService();
        boolean success = revser.addReview(userId, productId, rating, comment);

        if (success) {
            System.out.println("Review added successfully!");
        } else {
            System.out.println("Failed to add review!");
        }
    }

    //Method to view items in the cart
    private static void viewCart(int userId) {

        List<CartItem> cartItems = cartService.viewCart(userId);

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.println("\n--- YOUR CART ---");
        for (CartItem item : cartItems) {
            System.out.println("Cart ID: " + item.getCartId()+ " | Product ID: " + item.getProductId()+ " | Quantity: " + item.getQuantity());
        }
    }
    //Updating cart quantity
    private static void updateCartQuantity()
    {
    	System.out.print("Enter Cart ID: ");
        int cartId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter New Quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());

        boolean success = cartService.updateQuantity(cartId, quantity);

        if (success)
            System.out.println("Quantity updated!");
        else
            System.out.println("Failed to update quantity!");
    }
    //To remove item from the cart
    private static void removeCartItem()
    {

        System.out.print("Enter Cart ID: ");
        int cartId = Integer.parseInt(sc.nextLine());

        boolean success = cartService.removeItem(cartId);

        if (success)
            System.out.println("Item removed from cart!");
        else
            System.out.println("Failed to remove item!");
    }
    private static void addToWishlist(int userId) 
    {
    	System.out.print("Enter Product ID to add to wishlist: ");
        int productId = Integer.parseInt(sc.nextLine());
        wishlistService.add(userId, productId);
        System.out.println("Product added to wishlist!");
    }
     private static void viewWishlist(int userId)
     {
    	 List<ProductItem> wishlist = wishlistService.view(userId);

         if (wishlist.isEmpty()) 
             System.out.println("Your wishlist is empty.");
         else {
             System.out.println("=== YOUR WISHLIST ===");
             for (ProductItem p : wishlist) 
                 System.out.println("ID: " + p.getProductId() +" | Name: " + p.getProductName() +" | Price: Rs." + p.getPrice());
         }
     }
  }
