package com.revshop.ui;

import java.util.List;
import java.util.Scanner;

import com.revshop.model.ProductItem;
import com.revshop.model.Reviews;
import com.revshop.service.ProductService;
import com.revshop.service.ReviewService;

public class SellerUIMenu {

	private static Scanner sc = new Scanner(System.in);
	 private static ProductService productService = new ProductService();

   public static void showMenu(int sellerId) 
   {
   	        while (true)
   	        {
   	            System.out.println("\n=== SELLER MENU ===");
   	            System.out.println("1. Add Product");
   	            System.out.println("2. View My Products");
   	            System.out.println("3.View product Reviews");
   	            System.out.println("4. Logout");

   	            System.out.print("Choose option: ");
   	            int choice = Integer.parseInt(sc.nextLine());

   	            switch (choice) 
   	            {
   	                case 1:
   	                    addProduct(sellerId);
   	                    break;
   	                case 2:
   	                    viewProducts(sellerId);
   	                    break;
   	                case 3:
   	                	viewSellerProductReviews(sellerId);
   	                	break;
   	                case 4:
   	                    return;
   	                default:
   	                    System.out.println("Invalid choice!");
   	            }
   	        }
   	  }
   
   //Adding products 
   private static void addProduct(int sellerId) {

       System.out.print("Category ID: ");
       int categoryId = Integer.parseInt(sc.nextLine());

       System.out.print("Product Name: ");
       String name = sc.nextLine();

       System.out.print("Description: ");
       String desc = sc.nextLine();

       System.out.print("Price: ");
       double price = Double.parseDouble(sc.nextLine());

       System.out.print("MRP: ");
       double mrp = Double.parseDouble(sc.nextLine());

       System.out.print("Stock Quantity: ");
       int stock = Integer.parseInt(sc.nextLine());

       System.out.print("Low Stock Threshold: ");
       int threshold = Integer.parseInt(sc.nextLine());

       ProductItem product = new ProductItem(sellerId,categoryId,name,desc,price,mrp,stock,threshold);

       boolean success = productService.addProduct(product);

       if (success)
           System.out.println("Product added successfully!");
       else
           System.out.println("Failed to add product!");
   }

   
   private static void viewProducts(int sellerId) {

       List<ProductItem> products = productService.viewSellerProducts(sellerId);

       if (products.isEmpty()) {
           System.out.println("No products found!");
           return;
       }

       for (ProductItem p : products) {
           System.out.println("---------------------------------");
           System.out.println("ID: " + p.getProductId());
           System.out.println("Name: " + p.getProductName());
           System.out.println("Price: " + p.getPrice());
           System.out.println("MRP: " + p.getMrp());
           System.out.println("Stock: " + p.getStockQuantity());
       }

       productService.checkLowStock(products);
   }
   
   //To view product Reviews
   private static void viewSellerProductReviews(int sellerId) 
   {

       List<ProductItem> products = productService.viewSellerProducts(sellerId);

       if (products.isEmpty()) {
           System.out.println("You have no products yet.");
           return;
       }

       System.out.println("\n--- YOUR PRODUCT REVIEWS ---");

       for (ProductItem p : products) 
       {
           System.out.println("\nProduct: " + p.getProductName());
           System.out.println("Product ID: " + p.getProductId());

           double avgRating = ReviewService.getAverageRating(p.getProductId());
           System.out.println("Average Rating: " + avgRating);

           List<Reviews> reviews =
                   ReviewService.getProductReviews(p.getProductId());

           if (reviews.isEmpty()) 
           {
               System.out.println("No reviews yet.");
               continue;
           }

           for (Reviews r : reviews) 
           {
               System.out.println("--------------------");
               System.out.println("Rating: " + r.getRating());
               System.out.println("Comment: " + r.getReviewComment());
           }
       }
   }
}
   	    

